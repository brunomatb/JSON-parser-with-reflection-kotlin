package com.batista.json.visitor

import com.batista.json.jsonValues.*
/**
 * Visitante para operações em valores JSON do tipo número.
 */
class JsonNumberVisitor: JsonVisiter {
    val listNumbers = ArrayList<String>()

    override fun visit(value: JsonNumber) {

    }
    /**
     * Visita um objeto JSON.
     * Verifica se a chave é "numero" e se o valor é do tipo JsonNumber.
     * Caso seja verdadeiro, adiciona o valor à lista listNumbers.
     * De seguda, continua a visitar os restantes valores do objeto JSON.
     *
     * @param obj o objeto JSON a ser visitado.
     */
    override fun visit(value: JsonObject) {
        value.objMap.entries.forEach { (k, v) ->
            if (k == "numero"  && v is JsonNumber) {
                listNumbers.add(v.value.toString())
            }
            v.accept(this)
        }
    }

    override fun visit(value: JsonBoolean) {

    }

    override fun visit(value: JsonNull) {

    }
    /**
     * Visita um valor JSON do tipo array.
     * Continua a visitar cada valor presente no array.
     *
     * @param arrayValues o valor JSON do tipo array a ser visitado.
     */
    override fun visit(value: JsonArray) {
        value.values.forEach { it.accept(this)}

    }

    override fun visit(value: JsonString) {

    }


}