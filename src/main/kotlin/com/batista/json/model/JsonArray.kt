package com.batista.json.model

import com.batista.json.visitor.JsonValueVisiter

data class JsonArray(val values: List<JsonValue> = listOf()) : JsonValue {
    override fun toJsonString(): String {
        return "${values.joinToString (separator = ",", prefix = "[", postfix = "]"){it.toJsonString()}}"
    }
    override fun access(visitor: JsonValueVisiter){
        visitor.visit(this)
    }

}