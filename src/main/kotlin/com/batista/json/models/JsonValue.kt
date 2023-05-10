package com.batista.json.models

import com.batista.json.visitor.JsonVisiter

interface JsonValue {
        fun toJsonString():String
        fun accept(visitor: JsonVisiter)
}