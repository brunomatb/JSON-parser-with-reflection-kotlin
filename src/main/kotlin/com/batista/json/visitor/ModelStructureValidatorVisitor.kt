package com.batista.json.visitor

import com.batista.json.models.*

class ModelStructureValidatorVisitor:JsonVisiter {
    var modelIsValid:Boolean =  true
    var numberIntsIsValid:Boolean = true
    override fun visit(numberValue: JsonNumber) {

    }

    override fun visit(obj: JsonObject) {
       obj.objMap.forEach{ (k,v) ->
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
           v.accept(this)
       }
    }

    override fun visit(bolValue: JsonBoolean) {

    }

    override fun visit(nullValue: JsonNull) {

    }

    override fun visit(arrayValues: JsonArray) {
        arrayValues.values.forEach { it.accept(this)}
    }

    override fun visit(stringValue: JsonString) {

    }
}