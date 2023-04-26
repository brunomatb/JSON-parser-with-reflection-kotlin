package com.batista.json.main

import Models.Exam
import Models.Student
import com.batista.json.converter.JsonReflection
import com.batista.json.converter.JsonReflection_test
data class Person(val name: String, val age: Int)
fun main(){
    /*
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

    val students = listOf(
        Student(101101, "Dave Farley", true),
        Student(101102, "Martin Fowler", true),
        Student(26503, "André Santos", false)
    )


    val jsonReflection3 = JsonReflection_test()


    val exam = Exam("PA", 6.0, null, students)
    val person = Person("John Doe", 30)
    val jsonReflection2 = JsonReflection()
    val jsonValue = jsonReflection2.toJsonValue(person)
    println(jsonValue.toJsonString())
    val jsonReflection = JsonReflection()
    val examJson = jsonReflection.toJsonValue(exam)

    println(jsonValue.toJsonString())




}