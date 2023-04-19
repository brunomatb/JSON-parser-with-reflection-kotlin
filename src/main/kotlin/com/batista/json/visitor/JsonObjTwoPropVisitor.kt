package com.batista.json.visitor

import com.batista.json.model.*

data class JsonObjTwoPropVisitor(val prop1:String, val prop2:String): JsonValueVisiter {
    val listProperty = ArrayList<String>()

    override fun visit(numberValue: JsonNumber) {

    }
    override fun visit(obj: JsonObject) {
            if (obj.properties.contains(prop1) && obj.properties.contains(prop2)) {
                if (listProperty.contains(obj.toJsonString())) {

                }else{
                    listProperty.add(obj.toJsonString())
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