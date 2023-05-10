package com.batista.json.models

import com.batista.json.visitor.JsonVisiter


class JsonBoolean(val bolValue:Boolean): JsonValue {
    override fun toJsonString(): String {
        return bolValue.toString()
    }

    override fun accept(visitor: JsonVisiter) {
        visitor.visit(this)
    }
}