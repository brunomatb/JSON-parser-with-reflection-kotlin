package com.batista.json.visitor

import com.batista.json.jsonValues.*

/**
 * Interface para visitantes de valores JSON.
 */
sealed interface JsonVisiter{
    fun visit(value: JsonObject)
    fun visit(value: JsonArray)
    fun visit(value: JsonNumber)
    fun visit(value: JsonString)
    fun visit(value: JsonBoolean)
    fun visit(value: JsonNull)


}