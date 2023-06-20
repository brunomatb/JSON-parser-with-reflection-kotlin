package com.batista.json.jsonValues


import com.batista.json.Observer.JsonObservable
import com.batista.json.Observer.JsonObserver

import com.batista.json.visitor.JsonVisiter

/**
 * Representa um valor JSON do tipo array.
 *
 * @param values lista de valores do array.
 */
data class JsonArray(var values:MutableList<JsonValue>): JsonValue, JsonObservable {
    private val observers: MutableList<JsonObserver> = mutableListOf()

    override fun toJsonString(): String {
        val indentSize = 4
        val indentChar = ' '

        var jsonString = "[\n"

        var isFirst = true
        for (value in values) {
            if (!isFirst) {
                jsonString += ",\n"
            }
            jsonString += indentChar.toString().repeat(indentSize)
            jsonString += value.toJsonString()

            isFirst = false
        }

        jsonString += "\n"
        jsonString += indentChar.toString().repeat(indentSize - 2)
        jsonString += "]"

        return jsonString
    }
    /**
     * Visita um valor JSON do tipo array.
     * Continua a visitar cada valor presente no array.
     *
     * @param arrayValues o valor JSON do tipo array a ser visitado.
     */
    override fun accept(visitor: JsonVisiter) {
        visitor.visit(this)
        values.forEach { it.accept(visitor)}
    }

    override fun addObserver(observer: JsonObserver) {
        observers.add(observer)
    }
    override fun removeObserver(observer: JsonObserver) {
        observers.remove(observer)
    }

    override fun notifyObservers() {
        for (observer in observers){
            observer.onJsonChanged()
        }
    }

}