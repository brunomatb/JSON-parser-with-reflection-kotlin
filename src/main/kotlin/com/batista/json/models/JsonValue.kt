package com.batista.json.models

import com.batista.json.Observer.JsonObserver
import com.batista.json.visitor.JsonVisiter
/**
 * Interface que para representar um valor de JSON.
 */
interface JsonValue {
        fun toJsonString():String
        fun accept(visitor: JsonVisiter)

}