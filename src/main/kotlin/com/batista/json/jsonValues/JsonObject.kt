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
    private val observers: MutableList<JsonObserver> = mutableListOf()
    override fun toJsonString(): String {
        return """{${objMap.entries.joinToString() { """"${it.key}":${it.value.toJsonString()}""" }}}"""
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
        for (observer in observers) {
            observer.onJsonChanged()
        }
    }
    fun addProperty(name: String, value: JsonValue) {
        objMap[name] = value
        notifyObservers()
    }

    fun removeLastProperty() {
        if (objMap.isNotEmpty()) {
            val lastKey = objMap.keys.last()
            objMap.remove(lastKey)
            notifyObservers()
        }
    }

}