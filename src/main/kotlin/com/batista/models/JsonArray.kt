package com.batista.models

import com.batista.visiter.JsonVisiter

data class JsonArray(var values:List<JsonValue> = listOf()): JsonValue {
    override fun toJsonString(): String {
        return values.joinToString(prefix = "[", postfix = "]"){it.toJsonString()}
    }

    override fun accept(visitor: JsonVisiter) {
        visitor.visit(this)
    }

}