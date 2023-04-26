package com.batista.json.model

import com.batista.json.visitor.JsonNumberVisitor
import com.batista.json.visitor.JsonObjTwoPropVisitor
import com.batista.json.visitor.ModelStructureValidatorVisitor
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class JsonTest {
    private val aluno1 = JsonObject(mapOf(JsonString("numero") to JsonNumber(101101),
        JsonString("nome") to JsonString("Dave Farley"),
        JsonString("internacional") to JsonBolean(true)))
    private val aluno2 = JsonObject(mapOf(JsonString("numero") to JsonNumber(101102),
        JsonString("nome") to JsonString("Martin Fowler"),
        JsonString("internacional") to JsonBolean(true)))
    private val aluno3 = JsonObject(mapOf(JsonString("numero") to JsonNumber(26503),
        JsonString("nome") to JsonString("André Santos"),
        JsonString("internacional") to JsonBolean(true)))
    private val jsonArray = JsonArray(listOf(aluno1, aluno2, aluno3))
    private val jsonObjet = JsonObject(mapOf(JsonString("uc") to JsonString("PA"),
        JsonString("ects") to JsonNumber(6.0),
        JsonString("data-exame") to JsonNull(null),
        JsonString("inscritos") to jsonArray))


    @Test
    fun testNumberVisitor() {
        val numeroVisitor = JsonNumberVisitor()
        jsonObjet.access(numeroVisitor)

        val expectedNumeros = arrayListOf<JsonNumber>(JsonNumber(101101), JsonNumber(101102), JsonNumber(26503))
        assertEquals(expectedNumeros, numeroVisitor.listNumbers)
    }
    @Test
    fun `testJsonObjTwoPropVisitor`() {
        val jsonObjTwoPropVisitor = JsonObjTwoPropVisitor("numero","nome")
        jsonObjet.access(jsonObjTwoPropVisitor)

        val expectedObjetos = jsonArray.values.filterIsInstance<JsonObject>()
        assertEquals(expectedObjetos, jsonObjTwoPropVisitor.listProperty)
    }

    @Test
    fun `test to ModelStructureValidatorVisitor`() {
        val estruturaValidacaoVisitor = ModelStructureValidatorVisitor()
        jsonObjet.access(estruturaValidacaoVisitor)

        assertTrue(estruturaValidacaoVisitor.numberIntsIsValid)
        assertTrue(estruturaValidacaoVisitor.modelIsValid)
    }




}