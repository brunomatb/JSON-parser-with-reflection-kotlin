package com.batista.pa
/*
Class para representar uma string
 */
data class JsonString(val value: String) : JsonValue{
    override fun toJsonString(): String {
        //faz o \" faz o escape de duplas aspas
        return  "\"$value\""
    }
}