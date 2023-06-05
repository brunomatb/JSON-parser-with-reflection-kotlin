package com.batista.json.models

import com.batista.json.Observer.JsonObserver
import javax.swing.DefaultListModel

class JsonController(val jsonModel: JsonModel) {
    private val observers: MutableList<JsonObserver> = mutableListOf()

    fun addObserver(observer: JsonObserver) {
        observers.add(observer)
    }

    fun removeObserver(observer: JsonObserver) {
        observers.remove(observer)
    }

    private fun notifyObservers() {
        for (observer in observers) {
            observer.onJsonChanged()
        }
    }

    fun addWidget(widget: JsonValue) {
        jsonModel.addWidget(widget)
        notifyObservers()
    }

    fun deleteAllWidgets() {
        jsonModel.deleteAllWidgets()
        notifyObservers()
    }
    fun getWidgets(): DefaultListModel<JsonValue> {
        return jsonModel.getWidgets()
    }
}