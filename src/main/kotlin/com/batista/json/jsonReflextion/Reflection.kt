package com.batista.json.jsonReflextion

import com.batista.json.jsonValues.*
import kotlin.reflect.KProperty1
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.memberProperties
/*
classe responsável por converter objetos em valores JSON utilizando a a reflexão
*/

class Reflection {

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
                // Caso o objeto não se enquadre nos casos anteriores, é feita a reflexão do objeto para converter as propriedades em valores JSON
                val kClass = obj::class
                if (kClass.isData) {
                    val props = kClass.memberProperties
                        .filterNot { it.findAnnotation<com.batista.json.anotations.JsonExclude>() !=null }
                        .map { p->
                            val key = getTreatJsonProperty(p)
                            val value = getTreatJsonForceString(p, obj)
                            key to value
                        }.toMap() as MutableMap
                    return JsonObject(props)
                } else {
                    throw IllegalArgumentException("Tipo não suportado: ${obj::class.simpleName}")
                }
            }
        }
    }
    // Obtém o nome da propriedade, efetua tratamento da anotação JsonProperty, se existir
    private fun getTreatJsonProperty(props: KProperty1<out Any, *>): String {
        val annotation = props.findAnnotation<com.batista.json.anotations.JsonProperty>()
        if(annotation!= null){
            return annotation.value
        }else{
            return props.name
        }
    }
    // Obtém o valor da propriedade tratando a anotação JsonForceString, se existir
    private fun getTreatJsonForceString(props: KProperty1<out Any, *>, obj: Any?): JsonValue {
        // obtem o valor da propriedade do objeto que está a ser chamado
        val value = props.getter.call(obj)
        val annotation = props.findAnnotation<com.batista.json.anotations.JsonForceString>()
        if(annotation!=null && value is Number){
            return JsonString(value.toString())
        }else{
            return toJsonValue(value)
        }
    }
}