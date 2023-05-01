package com.batista.visiter

import com.batista.models.*
import models.*

class JsonPropsVisiter : JsonVisiter {
    private var strListVisit = ArrayList<String>()
    private var objListVisit = ArrayList<JsonObject>()

    fun numberVisiter(obj: JsonObject): String {
        obj.objMap.entries.forEach { (k, v) ->
            if (k == "numero" && v is JsonNumber) {
                strListVisit.add(v.toJsonString())
            }
            v.accept(this)
        }
        return strListVisit.joinToString()
    }

    fun numberNameVisiter(obj: JsonObject): String {
        if (obj.objMap.containsKey("numero") && obj.objMap.containsKey("nome")) {
            objListVisit.add(obj)
        }
        obj.objMap.values.forEach { it.accept(this) }
        return objListVisit.joinToString() { it.toJsonString() }
    }

    fun structureValidate(obj: JsonObject): Boolean {
        var validate = false
        obj.objMap.forEach { (k, v) ->
            if (v is JsonArray && k == "inscritos") {
                val estrutura = v.values.firstOrNull() as JsonObject
                validate = validateNumber(v.values) && validateObjts(v.values, estrutura)
            }
            v.accept(this)
        }
        return validate
    }

    private fun validateObjts(v: List<JsonValue>, objComparator: JsonObject): Boolean {
        var validate = false
        v.forEach { el ->
            validate = el is JsonObject && el.objMap.keys == objComparator.objMap.keys
        }
        return validate
    }

    private fun validateNumber(v: List<JsonValue>): Boolean {
        var validate = true
        v.forEach { el ->
            if (el is JsonObject) {
                el.objMap.entries.forEach { (k, v) ->
                    if (k == "numero" && v is JsonNumber && v.value !is Int) {
                        validate = false
                    }
                }
            }
        }
        return validate
    }

    override fun visit(obj: JsonObject) {
        numberVisiter(obj)
        numberNameVisiter(obj)
        structureValidate(obj)
    }

    override fun visit(value: JsonArray) {
        value.values.forEach { it.accept(this) }
    }

    override fun visit(value: JsonNumber) {

    }

    override fun visit(value: JsonString) {

    }

    override fun visit(value: JsonBoolean) {

    }

    override fun visit(value: JsonNull) {

    }
}