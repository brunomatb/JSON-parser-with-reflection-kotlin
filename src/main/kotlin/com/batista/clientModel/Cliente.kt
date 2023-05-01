package com.batista.clientModel

import com.batista.anotations.JsonExclude
import com.batista.anotations.JsonForceString
import com.batista.anotations.JsonProperty

data class Cliente(
    @JsonExclude
    val nome:String,
    @JsonProperty("novaIdade")
    val idade:Int,
    @JsonForceString
    val altura:Double
    )
