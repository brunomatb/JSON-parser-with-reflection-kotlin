package com.batista.json.model

import com.batista.json.visitor.JsonValueVisiter

/*
Class para representar uma string
 */
data class JsonString(val value: String) : JsonValue {
    override fun toJsonString(): String {
        //faz o \" faz o escape de duplas aspas
        return  "\"$value\""
    }
    override fun access(visitor: JsonValueVisiter){
        visitor.visit(this)
    }
}