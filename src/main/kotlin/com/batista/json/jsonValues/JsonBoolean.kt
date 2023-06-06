package com.batista.json.jsonValues

import com.batista.json.visitor.JsonVisiter

/**
 * Representa um valor JSON do tipo booleano.
 *
 * @param bolValue valor booleano.
 */
class JsonBoolean(val bolValue:Boolean): JsonValue {
    override fun toJsonString(): String {
        return bolValue.toString()
    }

    override fun accept(visitor: JsonVisiter) {
        visitor.visit(this)
    }
}