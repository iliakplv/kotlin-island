fun main(args: Array<String>) {
    println("\ntestNullSafety")
    testNullSafety()
    println("\ntestExtensionFunctions")
    testExtensionFunctions()
    println("\ntestWithStatement")
    testWithStatement()
    println("\ntestSingleton")
    testSingleton()
}


/* ========================= null safety ========================= */
fun testNullSafety() {
    var s: String? = null
    println(s?.length)
    try {
        s!!.length
    } catch (e: KotlinNullPointerException) {
        println("NPE!")
    }
    println(s ?: "(null placeholder)")
    s = "abc"
    if (s != null) {
        println("\"$s\" length is ${s.length}")
    }
}

/* ========================= lambdas ========================= */


/* ========================= data classes ========================= */


/* ========================= properties ========================= */


/* ========================= delegates ========================= */


/* ========================= singleton ========================= */
fun testSingleton() {
    println(Resource.name)
}

object Resource {
    val name = init()
    fun init(): String {
        // object declaration is lazy
        // object expression is executed immediately, where used
        println("Lazy init")
        return "qwerty"
    }
}

/* ========================= extension functions ========================= */
fun testExtensionFunctions() {
    println(" abc ".trimUp())
}

fun String.trimUp(): String {
    return trim().toUpperCase()
}

/* ========================= with statement ========================= */
fun testWithStatement() {
    val m = MyClass()
    with(m) {
        doOne()
        doTwo()
    }
}

class MyClass() {
    fun doOne() {
        println("One")
    }

    fun doTwo() {
        println("Two")
    }
}
