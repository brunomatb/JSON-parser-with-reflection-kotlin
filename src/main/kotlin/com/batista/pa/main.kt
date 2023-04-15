package com.batista.pa

fun main(){

    var aluno1 = JsonObject(mapOf(JsonString("numero") to JsonNumber(101101),
        JsonString("nome") to JsonString("Dave Farley"),
        JsonString("internacional") to JsonBolean(true)))
    var aluno2 = JsonObject(mapOf(JsonString("numero") to JsonNumber(101102),
        JsonString("nome") to JsonString("Martin Fowler"),
        JsonString("internacional") to JsonBolean(true)))
    var aluno3 = JsonObject(mapOf(JsonString("numero") to JsonNumber(26503),
        JsonString("nome") to JsonString("André Santos"),
        JsonString("internacional") to JsonBolean(true)))
    var curso = JsonObject(mapOf(JsonString("uc") to JsonString("PA"),
        JsonString("data-exame") to JsonNumber(6.0),
        JsonString("ects") to JsonNull(null),
        JsonString("inscritos") to JsonArray(listOf(aluno1, aluno2, aluno3))))

    var objeto1 = JsonObject(mapOf(JsonString("telefone") to JsonArray(listOf(JsonNumber(93412124), JsonNumber(94123665)))))
    print(objeto1.toJsonString())
}