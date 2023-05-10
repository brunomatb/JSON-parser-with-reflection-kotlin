package com.batista.json.jsonReflextion

import com.batista.json.models.JsonNull
import com.batista.json.models.JsonNumber
import com.batista.json.models.JsonValue

class JsonNumberConverter: JsonValueConverter {
    override fun convertObjectValue(obj: Any?): JsonValue {
        if(obj == null) {
            return JsonNull()
        }
        return JsonNumber(obj as Number)
    }
}