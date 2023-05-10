package com.batista.json.jsonReflextion

import com.batista.json.models.JsonNull
import com.batista.json.models.JsonString
import com.batista.json.models.JsonValue

class JsonStringConverter: JsonValueConverter {
    override fun convertObjectValue(obj: Any?): JsonValue {
        if(obj == null) {
            return JsonNull()
        }
        return JsonString(obj as String)
    }
}