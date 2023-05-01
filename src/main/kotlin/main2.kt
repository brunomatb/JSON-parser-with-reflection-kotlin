fun main(){
    val map = mapOf(
        "a" to 1,
        "b" to 2,
        "c" to 3
    )
    val list = listOf("a", "b", "c")
    val map2 = list.map { it to it.toUpperCase() }.toMap()
    print(map2)
}