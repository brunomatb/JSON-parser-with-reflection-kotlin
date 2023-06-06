
# Exemplo de uso:

```import com.batista.json.jsonReflextion.Reflection
import com.batista.json.jsonValues.JsonObject
import com.batista.json.jsonValues.JsonString

fun main() {
    // Cria um objeto com propriedades
    data class Person(val name: String, val age: Int)

    val person = Person("John Doe", 30)

    // Converte o objeto em um valor JSON
    val reflection = Reflection()
    val jsonValue = reflection.toJsonValue(person)

    // Exibe o valor JSON
    println(jsonValue.toJsonString()) // Resultado: {"name":"John Doe","age":30}

    // Atualiza o valor de uma propriedade
    if (jsonValue is JsonObject) {
        val nameProperty = jsonValue.objMap["name"]
        if (nameProperty is JsonString) {
            nameProperty.updateValue("Jane Smith")
        }
    }

    // Exibe o valor JSON atualizado
    println(jsonValue.toJsonString()) // Resultado: {"name":"Jane Smith","age":30}
}
```


# Recursos Principais:
Conversão de objetos em JSON usando a reflexão.
Manipulação de estruturas de dados JSON.
Suporte para anotações personalizadas para o comportamento da conversão.
# Classes Principais
com.batista.json.jsonReflextion.Reflection: Classe responsável por converter objetos em valores JSON usando reflexão.
com.batista.json.jsonValues.JsonObject: Representa um valor JSON do tipo objeto.
com.batista.json.jsonValues.JsonString: Representa um valor JSON do tipo string.
