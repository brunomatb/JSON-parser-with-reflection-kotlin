package com.batista.json.visitor

import com.batista.json.model.*

interface JsonValueVisiter {
    fun visit(numberValue: JsonNumber)
    fun visit(obj: JsonObject)
    fun visit(bolValue: JsonBolean)
    fun visit(nullValue: JsonNull)
    fun visit(arrayValues: JsonArray)
    fun visit(stringValue: JsonString)
}