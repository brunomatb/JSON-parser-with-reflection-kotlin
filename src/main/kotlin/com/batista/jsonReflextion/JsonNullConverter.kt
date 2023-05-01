package com.batista.jsonReflextion

import com.batista.models.JsonNull
import com.batista.models.JsonValue

class JsonNullConverter: JsonValueConverter {
    override fun convertObjectValue(obj: Any?): JsonValue {
        return JsonNull()
    }
}