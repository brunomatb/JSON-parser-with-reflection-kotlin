package com.batista.json.model

import com.batista.json.visitor.JsonValueVisiter

data class JsonNumber(val value: Number) : JsonValue {
    override fun toJsonString(): String {
        return value.toString()
    }
    override fun access(visitor: JsonValueVisiter){
        visitor.visit(this)
    }
}