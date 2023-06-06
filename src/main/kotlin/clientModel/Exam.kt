package Models

import com.batista.json.anotations.JsonExclude
import com.batista.json.anotations.JsonProperty


data class Exam(
    @JsonProperty("Curso")
    val course: String,
    @JsonExclude
    val ects: Double,
    @JsonExclude
    val examDate: String?,
    val student: List<Student>
)