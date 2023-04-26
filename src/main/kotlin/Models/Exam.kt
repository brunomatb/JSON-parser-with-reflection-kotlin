package Models

import com.batista.json.anotation.JsonExclude
import com.batista.json.anotation.JsonProperty

data class Exam(
    @JsonProperty(name = "uc")
    val course: String,
    @JsonExclude
    val ects: Double,
    @JsonExclude
    val examDate: String?,
    val registeredStudents: List<Student>
)