package com.batista.json.converter

import com.batista.json.anotation.JsonExclude
import com.batista.json.anotation.JsonProperty
import com.batista.json.anotation.jsonForceString
import com.batista.json.model.*
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.memberProperties

class JsonReflection_test {

    fun toJsonValue(obj: Any?): JsonValue {
        if(obj== null){
            throw IllegalArgumentException("Objeto nulo não é suportado")
        }
        val kClass = obj::class
        if (kClass.isData) {
            val properties = kClass.memberProperties
                .filterNot { it.findAnnotation<JsonExclude>() != null }
                .associateBy({ prop ->
                    prop.findAnnotation<JsonProperty>()?.name?.takeIf { it.isNotBlank() } ?: prop.name
                }, { prop ->
                    val value = prop.getter.call(obj)
                    if (prop.findAnnotation<jsonForceString>() != null && value is Number) {
                        JsonString(value.toString())
                    } else {
                        toJsonValue(value)
                    }
                })
            return JsonObject(properties.mapKeys { (key) -> JsonString(key) })
        } else {
            throw IllegalArgumentException("Tipo não suportado: ${obj::class.simpleName}")
        }
    }

}