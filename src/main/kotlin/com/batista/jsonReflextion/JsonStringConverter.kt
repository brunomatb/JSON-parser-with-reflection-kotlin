package com.batista.jsonReflextion

import com.batista.models.JsonNull
import com.batista.models.JsonString
import com.batista.models.JsonValue

class JsonStringConverter: JsonValueConverter {
    override fun convertObjectValue(obj: Any?): JsonValue {
        if(obj == null) {
            return JsonNull()
        }
        return JsonString(obj as String)
    }
}