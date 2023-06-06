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
    private val elements: MutableList<JsonValue> = mutableListOf()
    override fun toJsonString(): String {
        return values.joinToString(prefix = "[", postfix = "]"){it.toJsonString()}
    }

    override fun accept(visitor: JsonVisiter) {
        visitor.visit(this)
    }

    override fun addObserver(observer: JsonObserver) {
        observers.add(observer)
    }


    override fun removeObserver(observer: JsonObserver) {
        observers.remove(observer)
    }
    fun addElement(element: JsonValue) {
        elements.add(element)
        notifyObservers()
    }

    fun removeElement(element: JsonValue) {
        elements.remove(element)
        notifyObservers()
    }
    override fun notifyObservers() {
        for (observer in observers){
            observer.onJsonChanged()
        }
    }

}