import com.batista.jsonReflextion.DataClassConverter
import com.batista.models.JsonArray
import com.batista.models.JsonNumber
import com.batista.models.JsonObject
import com.batista.models.JsonString
import com.batista.clientModel.Cliente
import com.batista.visiter.JsonPropsVisiter

fun main(){
    val obj1 = JsonObject(mapOf("numero" to JsonNumber(101101), "nome" to JsonString("Dave Farley")))
    val obj2 = JsonObject(mapOf("numero" to JsonNumber(101122), "nome" to JsonString("Luis Farley")))
    val obj = JsonObject(mapOf("numero" to JsonNumber(2121222), "inscritos" to JsonArray(listOf(obj1, obj2))))
    val prop = JsonPropsVisiter()

    val teste = prop.structureValidate(obj)

    var lista: MutableList<String> = mutableListOf()
    lista.add("luis")
    lista.add("bruno")
    val novo = lista.filterNot { it == "luis" }
    print(novo)

    println(teste)
    val ref = DataClassConverter()
    val cliente = Cliente("Bruno", 17, 1.80)
    println(ref.toJsonValue(cliente).toJsonString())
}