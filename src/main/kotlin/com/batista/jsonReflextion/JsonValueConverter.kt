package com.batista.jsonReflextion

import com.batista.models.JsonNull
import com.batista.models.JsonValue

interface JsonValueConverter {
    fun convertObjectValue(obj:Any?): JsonValue

}