
import java.io.File
import kotlin.jvm.internal.Reflection
import kotlin.reflect.KClass
import org.reflections.Reflections

fun main() {
    val packageName = "com.batista.json.jsonReflextion"
    val reflections = Reflections(packageName)
    val classes = reflections.getSubTypesOf(Any::class.java)

    println("Classes no pacote '$packageName':")
    for (clazz in classes) {
        println(clazz.name)
    }
}





