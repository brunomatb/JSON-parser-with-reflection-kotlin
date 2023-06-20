package com.batista.json.jsonValues

import com.batista.json.Observer.JsonObservable
import com.batista.json.Observer.JsonObserver

import com.batista.json.visitor.JsonVisiter
/**
 * Representa um valor JSON do tipo string.
 *
 * @param objMap valor de um Mapa mutavél.
 */
class JsonObject(val objMap: MutableMap<String, JsonValue>): JsonValue, JsonObservable {
     val observers: MutableList<JsonObserver> = mutableListOf()
    override fun toJsonString(): String {
        val indentSize = 4
        val indentChar = ' '
        var jsonString = "{\n"
        var isFirst = true
        for ((key, value) in objMap) {
            if (!isFirst) {
                jsonString += ",\n"
            }
            jsonString += indentChar.toString().repeat(indentSize)
            jsonString += "\"$key\": ${value.toJsonString()}"

            isFirst = false
        }
        jsonString += "\n"
        jsonString += indentChar.toString().repeat(indentSize - 2)
        jsonString += "}"
        return jsonString
    }

    override fun accept(visitor: JsonVisiter) {
        visitor.visit(this)
        objMap.values.forEach { it.accept(visitor) }
    }

    override fun addObserver(observer: JsonObserver) {
        observers.add(observer)
    }

    override fun removeObserver(observer: JsonObserver) {
        observers.remove(observer)
    }

    override fun notifyObservers() {
        for (observer in observers) {
            observer.onJsonChanged()
        }
    }

}