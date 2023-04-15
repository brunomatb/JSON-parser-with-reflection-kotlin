package com.batista.pa

import javax.lang.model.type.NullType

class JsonNull(val value: NullType?): JsonValue {
    override fun toJsonString(): String {
        return value.toString()
    }
}