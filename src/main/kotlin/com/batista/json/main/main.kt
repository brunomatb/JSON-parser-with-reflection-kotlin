package com.batista.json.main

import com.batista.json.model.*
import com.batista.json.visitor.JsonNumberVisitor
import com.batista.json.visitor.JsonObjTwoPropVisitor
import com.batista.json.visitor.ModelStructureValidatorVisitor

fun main(){

    var aluno1 = JsonObject(mapOf("numero" to JsonNumber(101101),
        "nome" to JsonString("Dave Farley"),
        "internacional" to JsonBolean(true)))
    var aluno2 = JsonObject(mapOf("numero" to JsonNumber(101102),
        "nome" to JsonString("Martin Fowler"),
        "internacional" to JsonBolean(true)))
    var aluno3 = JsonObject(mapOf("numero" to JsonNumber(265),
        "nome" to JsonString("André Santos"),
        "internacional" to JsonBolean(true)))
    var curso = JsonObject(mapOf("uc" to JsonString("PA"),
        "data-exame" to JsonNumber(6.0),
        "inscritos" to JsonArray(listOf(aluno1, aluno2, aluno3))
    ))

        val numeroVisitor = JsonNumberVisitor()
        val visitarPropriedade = JsonObjTwoPropVisitor("numero", "nome")
        curso.access(visitarPropriedade)
        curso.access(numeroVisitor)
        println("Valores da propriedade 'numero': ${numeroVisitor.listNumbers}")
        println("Objectos a pesquisar: ${visitarPropriedade.listProperty}")
        val modelValidator = ModelStructureValidatorVisitor()
        curso.access(modelValidator)
        println("Validação dos objetos contidos no arrayJson para o objeto 'inscritos': ${modelValidator.modelIsValid}")



}