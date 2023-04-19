package com.batista.json.visitor

import com.batista.json.model.*

data class JsonObjTwoPropVisitor(val prop1:String, val prop2:String): JsonValueVisiter {
    val listProperty = ArrayList<JsonObject>()

    override fun visit(numberValue: JsonNumber) {

    }
    override fun visit(obj: JsonObject) {
            if (obj.properties.contains(JsonString(prop1)) && obj.properties.contains(JsonString(prop2))) {
                if (listProperty.contains(obj)) {

                }else{
                    listProperty.add(obj)
                }
        }
            obj.properties.entries.forEach { (k, v) ->

                v.access(this)
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