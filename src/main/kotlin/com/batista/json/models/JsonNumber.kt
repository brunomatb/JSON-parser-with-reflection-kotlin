package com.batista.json.models

import com.batista.json.visitor.JsonVisiter

class JsonNumber(val value:Number): JsonValue {
    override fun toJsonString(): String {
        return value.toString()
    }

    override fun accept(visitor: JsonVisiter) {
        visitor.visit(this)
    }

}