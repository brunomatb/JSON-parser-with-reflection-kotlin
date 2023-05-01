package com.batista.models

import com.batista.visiter.JsonVisiter

class JsonObject(val objMap : Map<String, JsonValue>): JsonValue {
    override fun toJsonString(): String {
        return """{${objMap.entries.joinToString() { """"${it.key}":${it.value.toJsonString()}""" }}}"""
    }

    override fun accept(visitor: JsonVisiter) {
        visitor.visit(this)
    }

}