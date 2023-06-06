package com.batista.json.jsonValues

import com.batista.json.visitor.JsonVisiter
/**
 * Interface que para representar um valor de JSON.
 */
interface JsonValue {
        fun toJsonString():String
        fun accept(visitor: JsonVisiter)

}