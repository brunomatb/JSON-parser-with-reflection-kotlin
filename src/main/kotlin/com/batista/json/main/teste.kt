package com.batista.json.main

fun main(){
    val listProperty = ArrayList<String>()
    listProperty.addAll(listOf("numero", "nome"))
    var teste = listProperty.joinToString()
    print((listProperty))

    var num:String = "22.0"
    var teste2:Int = num.toInt()
    print(num.toInt() is Int)
}