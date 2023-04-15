package com.batista.pa

data class JsonNumber(val value: Number) : JsonValue{
    override fun toJsonString(): String {
        return value.toString()
    }
}