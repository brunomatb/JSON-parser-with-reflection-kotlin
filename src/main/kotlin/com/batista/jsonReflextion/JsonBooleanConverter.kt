package com.batista.jsonReflextion

import com.batista.models.JsonBoolean
import com.batista.models.JsonNull
import com.batista.models.JsonObject
import com.batista.models.JsonValue

class JsonBooleanConverter: JsonValueConverter {
    override fun convertObjectValue(obj: Any?): JsonValue {
        if(obj == null) {
            return JsonNull()
        }
        return JsonBoolean(obj as Boolean)
    }

}