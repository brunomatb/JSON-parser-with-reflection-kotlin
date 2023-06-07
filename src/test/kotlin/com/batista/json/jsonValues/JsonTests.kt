package com.batista.json.jsonValues
import Models.Exam
import Models.Student
import com.batista.json.Observer.JsonObserver
import com.batista.json.jsonReflextion.Reflection
import com.batista.json.visitor.JsonNumberVisitor
import com.batista.json.visitor.JsonObjTwoPropVisitor
import com.batista.json.visitor.ModelStructureValidatorVisitor
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
internal class JsonTests{

    private val jsonObject = JsonObject(mutableMapOf(
        "uc" to JsonString("PA"),
        "ects" to JsonNumber(6.0),
        "data-exame" to JsonNull(),
        "inscritos" to JsonArray(mutableListOf(
            JsonObject(mutableMapOf(
                "numero" to JsonNumber(101101),
                "nome" to JsonString("Dave Farley"),
                "internacional" to JsonBoolean(true)
            )),
            JsonObject(mutableMapOf(
                "numero" to JsonNumber(101102),
                "nome" to JsonString("Martin Fowler"),
                "internacional" to JsonBoolean(true)
            )), JsonObject(mutableMapOf(
                "numero" to JsonNumber(26503),
                "nome" to JsonString("André Santos"),
                "internacional" to JsonBoolean(false)
            ))
        ))
    ))
    @Test
    fun `test to JsonObject as JsonString`() {

        val expectedObjetos = """{"uc":"PA", "ects":6.0, "data-exame":null, "inscritos":[{"numero":101101, "nome":"Dave Farley", "internacional":true}, {"numero":101102, "nome":"Martin Fowler", "internacional":true}, {"numero":26503, "nome":"André Santos", "internacional":false}]}"""
        assertEquals(expectedObjetos, jsonObject.toJsonString())
    }

    private class TestJsonObserver : JsonObserver {
        var updatedValue: String? = null

        override fun onJsonChanged() {
            updatedValue = "Valor foi atualizado"
        }
    }
    @Test
    fun testJsonObserver() {
        val observer = TestJsonObserver()
        jsonObject.addObserver(observer)

        // Atualiza o valor de uma propriedade do JsonObject
        jsonObject.objMap["uc"] = JsonString("MPD")
        jsonObject.notifyObservers()
        assertEquals("Valor foi atualizado", observer.updatedValue)
        // remove o observador
        jsonObject.removeObserver(observer)
        // adiciona o observador
        jsonObject.addObserver(observer)
        assertEquals(JsonString("MPD"), jsonObject.objMap["uc"])
        // Adiciona um novo valor ao JsonArray
        (jsonObject.objMap["inscritos"] as JsonArray).values.add(
            JsonObject(mutableMapOf(
                "numero" to JsonNumber(303303),
                "nome" to JsonString("Pedro")
            ))
        )
        jsonObject.notifyObservers()
        assertEquals("Valor foi atualizado", observer.updatedValue)
        jsonObject.notifyObservers()
        // Remove um valor do JsonArray
        (jsonObject.objMap["inscritos"] as JsonArray).values.removeAt(0)
        assertEquals("Valor foi atualizado", observer.updatedValue)
    }
    @Test
    fun textAddObserver(){
        val observer = TestJsonObserver()
        jsonObject.addObserver(observer)

        // Atualiza o valor de uma propriedade do JsonObject
        jsonObject.objMap["uc"] = JsonString("MPD")

        assertEquals(1, jsonObject.observers.size)
    }
    @Test
    fun textRemoveObserver(){
        val observer = TestJsonObserver()
        jsonObject.addObserver(observer)

        // Atualiza o valor de uma propriedade do JsonObject
        jsonObject.objMap["uc"] = JsonString("MPD")
        jsonObject.notifyObservers()
        jsonObject.removeObserver(observer)
        assertEquals(0, jsonObject.observers.size)
    }


    @Test
    fun `test to NumberVisitor`() {
        val numeroVisitor = JsonNumberVisitor()
        jsonObject.accept(numeroVisitor)
        val numeroEsperados = "101101, 101102, 26503"
        numeroVisitor.listNumbers
        assertEquals(numeroEsperados, numeroVisitor.listNumbers.joinToString ())
    }
    @Test
    fun `test to JsonObjTwoPropVisitor`() {
        val jsonObjTwoPropVisitor = JsonObjTwoPropVisitor()
        jsonObject.accept(jsonObjTwoPropVisitor)
        val expectedObjetos = """[{"numero":101101, "nome":"Dave Farley", "internacional":true}, {"numero":101102, "nome":"Martin Fowler", "internacional":true}, {"numero":26503, "nome":"André Santos", "internacional":false}]"""
        assertEquals(expectedObjetos, jsonObjTwoPropVisitor.listProperty.toString())
    }

    @Test
    fun `test to ModelStructureValidatorVisitor`() {
        val estruturaValidacaoVisitor = ModelStructureValidatorVisitor()
        jsonObject.accept(estruturaValidacaoVisitor)
        assertTrue(estruturaValidacaoVisitor.numberIntsIsValid)
        assertTrue(estruturaValidacaoVisitor.modelIsValid)
    }

    @Test
    fun testeToJsonNumber(){
        val jsonNumber = JsonNumber(12)

        assertEquals("12", jsonNumber.toJsonString())
    }

    @Test
    fun testeTobolean(){
        val JsonBoolean = JsonBoolean(true)

        assertEquals("true", JsonBoolean.toJsonString())
    }

    @Test
    fun testeTonull(){
        val Jsonnull = JsonNull()

        assertEquals("null", Jsonnull.toJsonString())
    }

    @Test
    fun testeTostring(){
        val JsonString = JsonString("teste")

        assertEquals(""""teste"""", JsonString.toJsonString())
    }
    @Test
    fun `test to Reflection`(){
        val students = listOf(
            Student(101101, "Dave Farley", true),
            Student(101102, "Martin Fowler", true),
            Student(26503, "André Santos", false)
        )
        val exam = Exam("PA", 6.0, null, students)
        val jsonReflection = Reflection()
        val valorEsperado = """{"Curso":"PA", "student":[{"international":true, "name":"Dave Farley", "number":101101}, {"international":true, "name":"Martin Fowler", "number":101102}, {"international":false, "name":"André Santos", "number":26503}]}"""
        assertEquals(valorEsperado, jsonReflection.toJsonValue(exam).toJsonString())
    }



}