package com.batista.models

import com.batista.visiter.JsonVisiter

class JsonNull: JsonValue {
    override fun toJsonString(): String {
        return "null"
    }

    override fun accept(visitor: JsonVisiter) {
        visitor.visit(this)
    }

}