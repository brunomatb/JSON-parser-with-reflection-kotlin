package com.batista.json.models

import com.batista.json.Observer.JsonObservable
import com.batista.json.Observer.JsonObserver
import javax.swing.DefaultListModel



class JsonModel : JsonObservable {
    private var observers: MutableList<JsonObserver> = mutableListOf()
    private val model: DefaultListModel<JsonValue>

    init {
        observers = ArrayList()
        model = DefaultListModel()
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

    fun addWidget(widget: JsonValue) {
        model.addElement(widget)
        notifyObservers()
    }

    fun deleteAllWidgets() {
        model.clear()
        notifyObservers()
    }

    fun getModel(): DefaultListModel<JsonValue> {
        return model
    }

    fun getWidgets(): DefaultListModel<JsonValue> {
        return model
    }
}