package com.batista.models

import com.batista.visiter.JsonVisiter

class JsonBoolean(val bolValue:Boolean): JsonValue {
    override fun toJsonString(): String {
        return bolValue.toString()
    }

    override fun accept(visitor: JsonVisiter) {
        visitor.visit(this)
    }
}