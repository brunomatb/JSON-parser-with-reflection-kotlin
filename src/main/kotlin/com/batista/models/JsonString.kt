package com.batista.models

import com.batista.visiter.JsonVisiter

data class JsonString(val value:String): JsonValue {
    override fun toJsonString(): String {
        return """"${value}""""
    }

    override fun accept(visitor: JsonVisiter) {
        visitor.visit(this)
    }

}