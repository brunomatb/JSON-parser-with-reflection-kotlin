package com.batista.json.jsonReflextion

import com.batista.json.models.JsonBoolean
import com.batista.json.models.JsonNull
import com.batista.json.models.JsonObject
import com.batista.json.models.JsonValue

class JsonBooleanConverter: JsonValueConverter {
    override fun convertObjectValue(obj: Any?): JsonValue {
        if(obj == null) {
            return JsonNull()
        }
        return JsonBoolean(obj as Boolean)
    }

}