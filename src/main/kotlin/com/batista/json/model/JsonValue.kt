package com.batista.json.model

import com.batista.json.visitor.JsonValueVisiter

interface JsonValue {
        fun toJsonString():String
        fun access(visitor: JsonValueVisiter)
}