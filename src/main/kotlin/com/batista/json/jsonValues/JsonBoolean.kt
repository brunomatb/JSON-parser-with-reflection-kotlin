package com.batista.json.jsonValues

import com.batista.json.Observer.JsonObservable
import com.batista.json.Observer.JsonObserver
import com.batista.json.visitor.JsonVisiter

/**
 * Representa um valor JSON do tipo booleano.
 *
 * @param bolValue valor booleano.
 */
class JsonBoolean(val bolValue:Boolean): JsonValue, JsonObservable {
    private val observers: MutableList<JsonObserver> = mutableListOf()
    override fun toJsonString(): String {
        return bolValue.toString()
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

    override fun notifyObservers() {
        observers.forEach { it.onJsonChanged() }
    }
}