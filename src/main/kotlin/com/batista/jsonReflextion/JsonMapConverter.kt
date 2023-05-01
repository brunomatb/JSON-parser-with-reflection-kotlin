package com.batista.jsonReflextion

import com.batista.models.JsonNull
import com.batista.models.JsonObject
import com.batista.models.JsonValue

class JsonMapConverter: JsonValueConverter {
    override fun convertObjectValue(obj: Any?): JsonValue {
        if(obj == null) {
            return JsonNull()
        }
        val cJson = DataClassConverter()
        return JsonObject((obj as Map<*, *>).mapKeys { it.toString() }.mapValues { cJson.toJsonValue(it.value) }.toMutableMap() )
    }
}