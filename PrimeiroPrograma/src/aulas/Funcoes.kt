package aulas


fun main(args: Array<String>) {
    println("O número maior é  "+ max(7,5))
    println("O número maior é  "+ max1(7,5))
}

// Corpo de bloco
fun max(a: Int, b: Int): Int {
    return if (a > b) a else b
}

// Corpo de Expressão
fun max1(a: Int, b: Int): Int = if (a > b) a else b