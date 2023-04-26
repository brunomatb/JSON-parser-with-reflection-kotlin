package com.batista.json.model

import com.batista.json.visitor.JsonValueVisiter

data class JsonObject(val properties: Map<JsonString, JsonValue>) : JsonValue {
    override fun toJsonString(): String {
        return "${properties.entries.joinToString (separator = ",\n", prefix = "\n{\n", postfix = "\n}"){"${it.key.toJsonString()}:${it.value.toJsonString()}"}}"
    }
    override fun access(visitor: JsonValueVisiter){
        visitor.visit(this)
    }

}