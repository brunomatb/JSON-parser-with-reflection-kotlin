package com.batista.json.jsonValues

import com.batista.json.visitor.JsonVisiter
/**
 * Representa um valor JSON do tipo null.
 */
class JsonNull: JsonValue {
    override fun toJsonString(): String {
        return "null"
    }

    override fun accept(visitor: JsonVisiter) {
        visitor.visit(this)
    }

}