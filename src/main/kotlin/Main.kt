import clientModel.Cliente
import com.batista.json.jsonValues.JsonArray
import com.batista.json.jsonValues.JsonNumber
import com.batista.json.jsonValues.JsonObject
import com.batista.json.jsonValues.JsonString
import com.batista.json.visitor.ModelStructureValidatorVisitor

fun main(){
    val obj1 = JsonObject(mutableMapOf("numero" to JsonNumber(101101), "nome" to JsonString("Dave Farley")))
    val obj2 = JsonObject(mutableMapOf("numero" to JsonNumber(101122), "nome" to JsonString("Luis Farley")))
    val obj = JsonObject(mutableMapOf("numero" to JsonNumber(2121222), "inscritos" to JsonArray(mutableListOf(obj1,obj2))))

    val teste = ModelStructureValidatorVisitor()
    teste.visit(obj)
    print(teste.modelIsValid)
    val cliente = Cliente("bruno", 35, 170.80, listOf(933323, 2235865))
    val jsonConverter = com.batista.json.jsonReflextion.Reflection()
    jsonConverter.toJsonValue(cliente)

}