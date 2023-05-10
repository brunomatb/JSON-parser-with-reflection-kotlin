package com.batista.json.models

import com.batista.json.visitor.JsonVisiter

class JsonNull: JsonValue {
    override fun toJsonString(): String {
        return "null"
    }

    override fun accept(visitor: JsonVisiter) {
        visitor.visit(this)
    }

}