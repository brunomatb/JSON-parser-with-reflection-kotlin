package com.batista.json.jsonReflextion

import com.batista.json.models.JsonValue

interface JsonValueConverter {
    fun convertObjectValue(obj:Any?): JsonValue

}