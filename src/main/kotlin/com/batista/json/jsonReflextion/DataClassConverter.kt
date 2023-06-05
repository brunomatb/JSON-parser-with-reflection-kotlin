package com.batista.json.jsonReflextion

import com.batista.json.anotations.JsonExclude
import com.batista.json.anotations.JsonProperty
import com.batista.json.models.*
import kotlin.reflect.KProperty1
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.memberProperties

class DataClassConverter {


    fun toJsonValue(obj: Any?): JsonValue {
        return when (obj) {
            null -> JsonNull()
            is Number -> JsonNumber(obj)
            is String -> JsonString(obj)
            is Boolean -> JsonBoolean(obj)
            is Enum<*> -> JsonString(obj.name)
            is Collection<*> -> JsonArray(obj.map { toJsonValue(it) }.toMutableList())
            is MutableMap<*, *> -> JsonObject(obj.mapKeys { it.key.toString() }.mapValues { toJsonValue(it.value) }.toMutableMap())
            else -> {
                convertObjectValue(obj)
            }
        }
    }
    fun convertObjectValue(obj: Any?): JsonValue {
        if(obj == null) {
            return JsonNull()
        }
        val kClass = obj::class
        if(kClass.isData){
            val props = kClass.memberProperties
                .filterNot { it.findAnnotation<com.batista.json.anotations.JsonExclude>() !=null }
                .map { p->
                    val key = getTreatJsonProperty(p)
                    val value = getTreatJsonForceString(p, obj)
                    key to value
            }.toMap() as MutableMap
            return JsonObject(props)
        }else{
            throw IllegalArgumentException("Tipo não suportado")
        }
    }


    private fun getTreatJsonProperty(props: KProperty1<out Any, *>): String {
        val annotation = props.findAnnotation<com.batista.json.anotations.JsonProperty>()
        if(annotation!= null){
            return annotation.value
        }else{
            return props.name
        }
    }

    private fun getTreatJsonForceString(props: KProperty1<out Any, *>, obj: Any?): JsonValue {
        val value = props.getter.call(obj)
        val annotation = props.findAnnotation<com.batista.json.anotations.JsonForceString>()
        if(annotation!=null && value is Number){
            return JsonString(value.toString())
        }else{
            return toJsonValue(value)
        }
    }
}