package com.batista.json.visitor

import com.batista.json.jsonValues.*

/**
 * Visitante para objetos JSON para duas propriedades específicas.
 */
class JsonObjTwoPropVisitor(): JsonVisiter {
    val listProperty = ArrayList<String>()

    override fun visit(value: JsonNumber) {

    }
    /**
     * Visita um objeto JSON.
     * Verifica se o objeto contém as duas propriedades especificadas (prop1 e prop2).
     * Se o objeto já estiver na lista listProperty, não faz nada.
     * Caso contrário, adiciona o objeto à lista.
     * Em seguida, continua a visitar os restantes valores do objeto JSON.
     *
     * @param obj o objeto JSON a ser visitado.
     */
    override fun visit(value: JsonObject) {
            if (value.objMap.contains("numero") && value.objMap.contains("nome")) {

                    listProperty.add(value.toJsonString())

        }
               value.objMap.entries.forEach { (k, v) ->

                v.accept(this)
            }

    }

    override fun visit(value: JsonBoolean) {

    }

    override fun visit(value: JsonNull) {

    }

    override fun visit(value: JsonArray) {
        value.values.forEach { it.accept(this)}
    }

    override fun visit(value: JsonString) {

    }
}