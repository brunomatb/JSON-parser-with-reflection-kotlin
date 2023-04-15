package com.batista.pa
/*
Class para representar um valor boleano
 */
class JsonBolean(val value:Boolean):JsonValue {
    override fun toJsonString(): String {
        return value.toString()
    }
}