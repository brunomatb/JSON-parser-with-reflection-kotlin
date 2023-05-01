package com.batista.jsonReflextion

import com.batista.anotations.JsonExclude
import com.batista.anotations.JsonForceString
import com.batista.anotations.JsonProperty
import com.batista.models.JsonNull
import com.batista.models.JsonObject
import com.batista.models.JsonString
import com.batista.models.JsonValue
import models.*
import kotlin.reflect.KProperty1
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.memberProperties

class DataClassConverter: JsonValueConverter {

    fun toJsonValue(obj:Any?): JsonValue {
        val converter: JsonValueConverter
        converter = when(obj){
            null -> JsonNullConverter()
            is Number -> JsonNumberConverter()
            is String -> JsonStringConverter()
            is Boolean -> JsonBooleanConverter()
            is Collection<*> -> JsonArrayConverter()
            is Map<*,*> -> JsonMapConverter()
            else->{
                DataClassConverter()
            }
        }
        return converter.convertObjectValue(obj)
    }
    override fun convertObjectValue(obj: Any?): JsonValue {
        if(obj == null) {
            return JsonNull()
        }
        val kClass = obj::class
        if(kClass.isData){
            val props = kClass.memberProperties
                .filterNot { it.findAnnotation<JsonExclude>() !=null }
                .map { p->
                    val key = getTreatJsonProperty(p)
                    val value = getTreatJsonForceString(p, obj)
                    key to value
            }.toMap()
            return JsonObject(props)
        }else{
            throw IllegalArgumentException("Tipo não suportado: ${obj::class.simpleName}")
        }

    }


    fun getTreatJsonProperty(props: KProperty1<out Any, *>): String {
        val annotation = props.findAnnotation<JsonProperty>()
        if(annotation!= null){
            return annotation.value
        }else{
            return props.name
        }
    }

    fun getTreatJsonForceString(props: KProperty1<out Any, *>, obj: Any?): JsonValue {
        val value = props.getter.call(obj)
        val annotation = props.findAnnotation<JsonForceString>()
        if(annotation!=null && value is Number){
            return JsonString(value.toString())
        }else{
            return toJsonValue(value)
        }
    }
}