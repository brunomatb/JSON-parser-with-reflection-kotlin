package com.batista.json.Observer
/**
 * Interface que define objetos JSON observáveis.
 */
interface JsonObservable  {
    fun addObserver(observer: JsonObserver)
    fun removeObserver(observer:JsonObserver)
    fun notifyObservers()

}
