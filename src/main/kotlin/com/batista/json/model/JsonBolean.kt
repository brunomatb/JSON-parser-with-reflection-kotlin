package com.batista.json.model

import com.batista.json.visitor.JsonValueVisiter

/*
Class para representar um valor boleano
 */
class JsonBolean(val value:Boolean): JsonValue {
    override fun toJsonString(): String {
        return value.toString()
    }
    override fun access(visitor: JsonValueVisiter){
        visitor.visit(this)
    }
}