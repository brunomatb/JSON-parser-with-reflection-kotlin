package com.batista.jsonReflextion

import com.batista.models.JsonNull
import com.batista.models.JsonNumber
import com.batista.models.JsonString
import com.batista.models.JsonValue

class JsonNumberConverter: JsonValueConverter {
    override fun convertObjectValue(obj: Any?): JsonValue {
        if(obj == null) {
            return JsonNull()
        }
        return JsonNumber(obj as Number)
    }
}