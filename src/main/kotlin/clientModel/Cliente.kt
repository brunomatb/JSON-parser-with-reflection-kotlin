package clientModel

import com.batista.json.anotations.JsonExclude
import com.batista.json.anotations.JsonForceString
import com.batista.json.anotations.JsonProperty

data class Cliente(
    @com.batista.json.anotations.JsonExclude
    val nome:String,
    @com.batista.json.anotations.JsonProperty("novaIdade")
    val idade:Int,
    @com.batista.json.anotations.JsonForceString
    val altura:Double,
    val numeros:List<Int>
    )
