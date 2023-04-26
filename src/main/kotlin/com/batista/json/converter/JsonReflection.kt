package com.batista.json.converter

import com.batista.json.anotation.JsonExclude
import com.batista.json.anotation.JsonProperty
import com.batista.json.anotation.jsonForceString
import com.batista.json.model.*
import kotlin.reflect.KMutableProperty
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.memberProperties

class JsonReflection {

    fun toJsonValue(obj: Any?): JsonValue {
        return when (obj) {
            null -> JsonNull(null)
            is Number -> JsonNumber(obj)
            is String -> JsonString(obj)
            is Boolean -> JsonBolean(obj)
            is Enum<*> -> JsonString(obj.name)
            is Collection<*> -> JsonArray(obj.map { toJsonValue(it) }.toMutableList())
            is Map<*, *> -> JsonObject(obj.mapKeys { JsonString(it.key.toString()) }.mapValues { toJsonValue(it.value) }.toMutableMap())
            else -> {
                val kClass = obj::class
                if (kClass.isData) {
                        val properties = kClass.memberProperties
                        .filterNot { it.findAnnotation<JsonExclude>() != null }
                        .associateBy({ prop ->
                            prop.findAnnotation<JsonProperty>()?.name?.takeIf { it !="" } ?: prop.name
                        }, { prop ->
                            val value = prop.getter.call(obj)
                            if (prop.findAnnotation<jsonForceString>() != null && value is Number) {
                                JsonString(value.toString())
                            } else {
                                toJsonValue(value)
                            }
                        })
                    JsonObject(properties.mapKeys { (key) -> JsonString(key) })
                } else {
                    throw IllegalArgumentException("Tipo não suportado: ${obj::class.simpleName}")
                }
            }
        }
    }

}