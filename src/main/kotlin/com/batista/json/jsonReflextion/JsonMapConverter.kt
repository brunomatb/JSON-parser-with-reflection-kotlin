package com.batista.json.jsonReflextion

import com.batista.json.models.JsonNull
import com.batista.json.models.JsonObject
import com.batista.json.models.JsonValue

class JsonMapConverter: JsonValueConverter {
    override fun convertObjectValue(obj: Any?): JsonValue {
        if(obj == null) {
            return JsonNull()
        }
        val cJson = com.batista.json.jsonReflextion.DataClassConverter()
        return JsonObject((obj as Map<*, *>).mapKeys { it.toString() }.mapValues { cJson.toJsonValue(it.value) }.toMutableMap() )
    }
}