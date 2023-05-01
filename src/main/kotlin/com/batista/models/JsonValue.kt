package com.batista.models

import com.batista.visiter.JsonVisiter

interface JsonValue {
        fun toJsonString():String
        fun accept(visitor: JsonVisiter)
}