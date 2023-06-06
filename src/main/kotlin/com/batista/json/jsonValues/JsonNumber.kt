package com.batista.json.jsonValues

import com.batista.json.visitor.JsonVisiter
/**
 * Representa um valor JSON do tipo string.
 *
 * @param value valor de um Number.
 */
class JsonNumber(val value:Number): JsonValue {
    override fun toJsonString(): String {
        return value.toString()
    }

    override fun accept(visitor: JsonVisiter) {
        visitor.visit(this)
    }

}