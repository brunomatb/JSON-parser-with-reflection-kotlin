package com.batista.json.jsonReflextion

import com.batista.json.models.JsonArray
import com.batista.json.models.JsonNull
import com.batista.json.models.JsonValue

class JsonArrayConverter: JsonValueConverter {
    override fun convertObjectValue(obj: Any?): JsonValue {
        if(obj == null) {
            return JsonNull()
        }
        val cJson = com.batista.json.jsonReflextion.DataClassConverter()
            return JsonArray((obj as Collection<*>).map { cJson.toJsonValue(it) })
        }
}