package com.batista.json.main

import kotlin.reflect.full.memberProperties
import com.batista.json.anotation.JsonExclude
import com.batista.json.anotation.JsonProperty
import com.batista.json.anotation.jsonForceString
import com.batista.json.model.*
import kotlin.reflect.KMutableProperty
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.memberProperties
fun main(){


    var kClass = JsonObject::class
    val properties = kClass.memberProperties
    print(properties.toMutableList())
}