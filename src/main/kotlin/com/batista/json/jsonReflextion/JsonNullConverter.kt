package com.batista.json.jsonReflextion

import com.batista.json.models.JsonNull
import com.batista.json.models.JsonValue

class JsonNullConverter: JsonValueConverter {
    override fun convertObjectValue(obj: Any?): JsonValue {
        return JsonNull()
    }
}