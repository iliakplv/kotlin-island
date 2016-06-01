fun main(args: Array<String>) {

    println("\ntestNullSafety")
    testNullSafety()

    println("\ntestFunctions")
    testFunctions()
    println("\ntestExtensionFunctions")
    testExtensionFunctions()
    println("\ntestHighOrderAndLambdas")
    testHighOrderAndLambdas()

    println("\ntestDataClasses")
    testDataClasses()
    println("\ntestProperties")
    testProperties()
    println("\ntestDelegates")
    testDelegates()

    println("\ntestStandard")
    testStandard()
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

/* ========================= functions ========================= */
fun testFunctions(): Unit {
    // Unit can be omitted
    defaultParams("a", false)
    defaultParams("b")
    defaultParams()
    defaultParams(s = "abc", b = false) // named params
    println(square(4)) // (single expression)
    println(2 add 3) // (infix) 2.add(3) also valid
    printNumbers(0, 1, 2, 3)
}

fun printNumbers(vararg ints: Int) {
    fun printNumber(i: Int) {
        // local function
        print("$i ")
    }
    for (i in ints) {
        printNumber(i)
    }
    println()
}

fun defaultParams(s: String = "", b: Boolean = true) {
    // do something
}

fun square(i: Int) = i * i // single expression function

infix fun Int.add(i: Int) = this + i // infix function (also extension function)

/* ========================= extension functions ========================= */
fun testExtensionFunctions() {
    println(" abc ".trimUp())
}

fun String.trimUp(): String {
    return trim().toUpperCase()
}

/* ========================= high-order and lambdas ========================= */
fun testHighOrderAndLambdas() {
    call("high-order", ::myPrint)
    call("lambda", { s -> println(s) });
    listOf(0, 3, 4, 5, 2, 8, 1).filter { it > 3 }.map { print("$it ") }
    println()
}

fun call(arg: String, function: (s: String) -> Unit) {
    function(arg);
}

fun myPrint(s: String) {
    println(s);
}

/* ========================= data classes ========================= */
fun testDataClasses() {

}

/* ========================= properties ========================= */
fun testProperties() {

}

/* ========================= delegates ========================= */
fun testDelegates() {

}

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

/* ========================= standard ========================= */
fun testStandard() {
    val m = MyClass()
    with(m) {
        doOne()
        doTwo()
    }
    m.apply { doOne() }
    m.let { it.doOne() }
    m.run { doOne() }
    repeat(3) {
        m.doTwo()
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
