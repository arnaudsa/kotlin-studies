package aulas

fun main(args: Array<String>) {
    println("7 é letra: ${isLetter('7')}")
    println("B é letra: ${isLetter('B')}")
    println("5 não é digito: ${isLetter('5')}")
}

fun isLetter(c: Char) = c in 'a'..'z' || c in 'A'..'Z'

fun isNotDigit(c: Char) = c !in '0' .. '9'