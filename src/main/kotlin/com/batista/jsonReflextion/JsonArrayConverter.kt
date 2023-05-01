package com.batista.jsonReflextion

import com.batista.models.JsonArray
import com.batista.models.JsonNull
import com.batista.models.JsonValue

class JsonArrayConverter: JsonValueConverter {
    override fun convertObjectValue(obj: Any?): JsonValue {
        if(obj == null) {
            return JsonNull()
        }
        val cJson = DataClassConverter()
            return JsonArray((obj as Collection<*>).map { cJson.toJsonValue(it) })
        }
}