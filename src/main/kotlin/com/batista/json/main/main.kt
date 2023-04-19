package com.batista.json.main

import com.batista.json.model.*
import kotlin.reflect.full.memberProperties

fun main(){

    var aluno1 = JsonObject(mapOf(JsonString("numero") to JsonNumber(101101),
                 JsonString("nome") to JsonString("Dave Farley"),
                 JsonString("internacional") to JsonBolean(true)))
    var aluno2 = JsonObject(mapOf(JsonString("numero") to JsonNumber(101102),
                 JsonString("nome") to JsonString("Martin Fowler"),
                 JsonString("internacional") to JsonBolean(true)))
    var aluno3 = JsonObject(mapOf(JsonString("numero") to JsonNumber(265),
                 JsonString("nome") to JsonString("André Santos"),
                 JsonString("internacional") to JsonBolean(true)))
    var curso = JsonObject(mapOf(JsonString("uc") to JsonString("PA"),
                JsonString("ects") to JsonNumber(6.0),
                JsonString("data-exame") to JsonNull(null),
                JsonString("inscritos") to JsonArray(listOf(aluno1, aluno2, aluno3))
    ))
/*
        val numeroVisitor = JsonNumberVisitor()
        val visitarPropriedade = JsonObjTwoPropVisitor("numero", "nome")
        curso.access(visitarPropriedade)
        curso.access(numeroVisitor)
        println("Valores da propriedade 'numero': ${numeroVisitor.listNumbers}")
        println("Objectos a pesquisar: ${visitarPropriedade.listProperty}")
        val modelValidator = ModelStructureValidatorVisitor()
        curso.access(modelValidator)
        println("Validação dos objetos contidos no arrayJson para o objeto 'inscritos': ${modelValidator.modelIsValid}")
        */


    var obj = curso::class
    print(obj.memberProperties)



}