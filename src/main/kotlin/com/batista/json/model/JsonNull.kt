package com.batista.json.model

import com.batista.json.visitor.JsonValueVisiter
import javax.lang.model.type.NullType

class JsonNull(val value: NullType?): JsonValue {
    override fun toJsonString(): String {
        return value.toString()
    }
    override fun access(visitor: JsonValueVisiter){
        visitor.visit(this)
    }
}