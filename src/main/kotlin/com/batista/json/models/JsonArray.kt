package com.batista.json.models

import com.batista.json.visitor.JsonVisiter


data class JsonArray(var values:List<JsonValue> = listOf()): JsonValue {
    override fun toJsonString(): String {
        return values.joinToString(prefix = "[", postfix = "]"){it.toJsonString()}
    }

    override fun accept(visitor: JsonVisiter) {
        visitor.visit(this)
    }

}