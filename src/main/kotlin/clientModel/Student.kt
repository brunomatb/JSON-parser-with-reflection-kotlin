package Models

import com.batista.json.anotations.JsonForceString


data class Student(
    val number: Int,
    val name: String,
    @JsonForceString
    val international: Boolean
)