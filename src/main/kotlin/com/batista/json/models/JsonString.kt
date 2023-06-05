package com.batista.json.models

import com.batista.json.Observer.JsonObservable
import com.batista.json.Observer.JsonObserver
import com.batista.json.visitor.JsonVisiter
/**
 * Representa um valor JSON do tipo string.
 *
 * @param value valor da string.
 */
data class JsonString(var value:String): JsonValue, JsonObservable {
    private val observers: MutableList<JsonObserver> = mutableListOf()
    override fun toJsonString(): String {
        return """"${value}""""
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
    /**
     * Atualiza o valor da string e notifica os observadores.
     *
     * @param newValue novo valor da string.
     */
    fun updateValue(newValue: String) {
        value = newValue
        notifyObservers()
    }

}