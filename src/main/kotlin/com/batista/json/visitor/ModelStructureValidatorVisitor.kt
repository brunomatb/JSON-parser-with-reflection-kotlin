package com.batista.json.visitor

import com.batista.json.model.*

class ModelStructureValidatorVisitor:JsonValueVisiter {
    var modelIsValid:Boolean =  true
    var numberIntsIsValid:Boolean = true
    override fun visit(numberValue: JsonNumber) {

    }

    override fun visit(obj: JsonObject) {
       obj.properties.forEach{ (k,v) ->
           when(k){
               JsonString("numero") -> (if (v is JsonNumber && v.value !is Int){
                   modelIsValid = false
                   numberIntsIsValid = false
               })
               JsonString("inscritos") -> if(v is JsonArray){
                   val objEstrutura = v.values.firstOrNull() as? JsonObject
                   v.values.forEach { elem -> if(elem!is JsonObject || elem.properties.keys != objEstrutura?.properties!!.keys){
                       modelIsValid = false
                     }
                   }
               }
           }
           v.access(this)
       }
    }

    override fun visit(bolValue: JsonBolean) {

    }

    override fun visit(nullValue: JsonNull) {

    }

    override fun visit(arrayValues: JsonArray) {
        arrayValues.values.forEach { it.access(this)}
    }

    override fun visit(stringValue: JsonString) {

    }
}