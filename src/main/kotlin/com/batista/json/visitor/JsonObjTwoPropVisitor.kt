package com.batista.json.visitor

import com.batista.json.models.*


data class JsonObjTwoPropVisitor(val prop1:String, val prop2:String): JsonVisiter {
    val listProperty = ArrayList<JsonObject>()

    override fun visit(numberValue: JsonNumber) {

    }
    override fun visit(obj: JsonObject) {
            if (obj.objMap.contains(prop1) && obj.objMap.contains(prop2)) {
                if (listProperty.contains(obj)) {

                }else{
                    listProperty.add(obj)
                }
        }
            obj.objMap.entries.forEach { (k, v) ->

                v.accept(this)
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