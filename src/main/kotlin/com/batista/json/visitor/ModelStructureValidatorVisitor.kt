package com.batista.json.visitor

import com.batista.json.jsonValues.*

class ModelStructureValidatorVisitor:JsonVisiter {
    var modelIsValid:Boolean =  true
    var numberIntsIsValid:Boolean = true
    override fun visit(value: JsonNumber) {

    }

    override fun visit(value: JsonObject) {
        value.objMap.forEach{ (k,v) ->
           when(k){
               "numero" -> (if (v is JsonNumber && v.value !is Int){
                   modelIsValid = false
                   numberIntsIsValid = false
               })
               "inscritos" -> if(v is JsonArray){
                   val objEstrutura = v.values.firstOrNull() as? JsonObject
                   v.values.forEach { elem -> if(elem!is JsonObject || elem.objMap.keys != objEstrutura?.objMap!!.keys){
                       modelIsValid = false
                     }
                   }
               }
           }
       }
    }

    override fun visit(value: JsonBoolean) {

    }

    override fun visit(value: JsonNull) {

    }

    override fun visit(value: JsonArray) {
        value.values.forEach { it.accept(this)}
    }

    override fun visit(value: JsonString) {

    }
}