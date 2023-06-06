
# Biblioteca JSON em Kotlin
Esta é uma biblioteca Programada em Kotlin que permite a conversão de objetos em um JSON e manipulação de propriedades, visitantes, reflexão/anotações e observadores. Foi projetada para facilitar o uso com um JSON em Kotlin.
# Recursos Principais:
- Conversão de objetos em JSON usando a reflexão.
- Manipulação de estruturas de dados JSON.
- Suporte para anotações personalizadas para o comportamento da conversão.
# Classes Principais
- `com.batista.json.jsonReflextion.Reflection`: Classe responsável por converter objetos em JSON usando reflexão.
- `com.batista.json.jsonValues.JsonObject`: Representa um JSON do tipo objeto.
- `com.batista.json.jsonValues.JsonString`: Representa um JSON do tipo string.

## Instalação

Para utilizar esta biblioteca em seu projeto Kotlin, siga os passos abaixo:

1. Repositório `build.gradle`:

   ```groovy
   repositories {
       maven { url 'https://qualquerCoisa.com' }
   }


# Adicionar a dependência da biblioteca ao ficheiro build.gradle:
dependencies {
    implementation 'com.github.seu-usuario:minha-biblioteca-json-kotlin:versao'
}
# Uso Simples
```import com.batista.json.jsonReflextion.Reflection
import com.batista.json.jsonValues.JsonObject
import com.batista.json.jsonValues.JsonString

fun main() {
    // Criar um objeto com propriedades
    data class Student(val name: String, val age: Int)

    val student = Student("Dave Farley", 25)

    // Converte o objeto em um valor JSON
    val reflection = Reflection()
    val jsonValue = reflection.toJsonValue(student)

    // Exibe o valor JSON
    println(jsonValue.toJsonString()) // Resultado: {"name":"Dave Farley","age":25}

    // Atualiza o valor de uma propriedade
    if (jsonValue is JsonObject) {
        val nameProperty = jsonValue.objMap["name"]
        if (nameProperty is JsonString) {
            nameProperty.updateValue("Martin Fowler")
        }
    }

    // Exibe o valor JSON atualizado
    println(jsonValue.toJsonString()) // Resultado: {"name":"Martin Fowler","age":25}
}
```
# Utilização do visitors
Exemplo de como usar um visitor para imprimir os  JSON:
```
import com.batista.json.visitor.JsonExemple

fun main() {
    val jsonValue: JsonValue = 
    val jsonExemple = JsonExemple()
    jsonValue.accept(jsonExemple)
    val jsonString: String = jsonExemple.getJsonString()
    println(jsonString)
}
```
# Utilização da Reflexão e Anotações
Exemplo de uso de anotações para definir nomes de propriedades personalizados:

```
import com.batista.json.anotations.JsonProperty

data class Student(
    @JsonProperty("name") 
    val nome: String,
    @JsonProperty("age") 
    val idade: Int
)

fun main() {
    val student = Student("Dave Farley", 30)
    val reflection = Reflection()
    val jsonValue: JsonValue = reflection.toJsonValue(student)
    val jsonString: String = jsonValue.toJsonString()
    println(jsonString)
}
```
# Uso de Observadores
Exemplo de como usar observadores
```
import com.batista.json.Observer.JsonObservable
import com.batista.json.Observer.JsonObserver

class MyObserver : JsonObserver {
    override fun onJsonChanged() {
        println("Alteração detectada no valor JSON.")
    }
}

fun main() {
    val jsonValue: JsonValue = // Valor JSON a ser observado
    val observer = MyObserver()
    jsonValue.addObserver(observer)

    // Faça alterações no valor JSON

    // O observador será notificado
}

```


