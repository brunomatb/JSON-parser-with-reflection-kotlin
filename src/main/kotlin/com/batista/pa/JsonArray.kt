package com.batista.pa

data class JsonArray(val values: List<JsonValue>) : JsonValue{
    override fun toJsonString(): String {
        return "${values.joinToString (separator = ",", prefix = "[", postfix = "]"){it.toJsonString()}}"
    }

}