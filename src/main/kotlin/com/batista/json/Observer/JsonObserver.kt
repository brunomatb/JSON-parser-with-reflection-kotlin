package com.batista.json.Observer
/**
 * Interface que define os observadores de mudanças em objetos JSON.
 */
interface JsonObserver {
    fun onJsonChanged()
}