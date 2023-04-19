package com.batista.json.visitor

import com.batista.json.model.*

class JsonNumberVisitor: JsonValueVisiter {
    val listNumbers = ArrayList<String>()
    override fun visit(numberValue: JsonNumber) {

    }

    override fun visit(obj: JsonObject) {
        obj.properties.entries.forEach { (key, value) ->
            if (key == "numero"  && value is JsonNumber) {
                listNumbers.add(value.toJsonString())
            }
            value.access(this)
        }
    }

    override fun visit(bolValue: JsonBolean) {

    }

    override fun visit(nullValue: JsonNull) {

    }

    override fun visit(arrayValues: JsonArray) {
        arrayValues.values.forEach { it.access(this)}

    }

    override fun visit(stringValue: JsonString) {

    }


}