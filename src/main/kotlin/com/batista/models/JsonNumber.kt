package com.batista.models

import com.batista.visiter.JsonVisiter

class JsonNumber(val value:Number): JsonValue {
    override fun toJsonString(): String {
        return value.toString()
    }

    override fun accept(visitor: JsonVisiter) {
        visitor.visit(this)
    }

}