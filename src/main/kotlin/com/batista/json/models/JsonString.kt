package com.batista.json.models

import com.batista.json.visitor.JsonVisiter

data class JsonString(val value:String): JsonValue {
    override fun toJsonString(): String {
        return """"${value}""""
    }

    override fun accept(visitor: JsonVisiter) {
        visitor.visit(this)
    }

}