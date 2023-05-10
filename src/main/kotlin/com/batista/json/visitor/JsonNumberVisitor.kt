package com.batista.json.visitor

import com.batista.json.models.*

class JsonNumberVisitor: JsonVisiter {
    val listNumbers = ArrayList<JsonNumber>()
    override fun visit(numberValue: JsonNumber) {

    }

    override fun visit(obj: JsonObject) {
        obj.objMap.entries.forEach { (key, value) ->
            if (key == "numero"  && value is JsonNumber) {
                listNumbers.add(value)
            }
            value.accept(this)
        }
    }

    override fun visit(bolValue: JsonBoolean) {

    }

    override fun visit(nullValue: JsonNull) {

    }

    override fun visit(arrayValues: JsonArray) {
        arrayValues.values.forEach { it.accept(this)}

    }

    override fun visit(stringValue: JsonString) {

    }


}