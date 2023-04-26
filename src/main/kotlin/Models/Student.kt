package Models

import com.batista.json.anotation.JsonProperty
import com.batista.json.anotation.jsonForceString

data class Student(
    val number: Int,
    val name: String,
    @jsonForceString
    val international: Boolean
)