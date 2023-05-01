package com.batista.visiter

import com.batista.models.*
import models.*

sealed interface JsonVisiter{
    fun visit(value: JsonObject)
    fun visit(value: JsonArray)
    fun visit(value: JsonNumber)
    fun visit(value: JsonString)
    fun visit(value: JsonBoolean)
    fun visit(value: JsonNull)


}