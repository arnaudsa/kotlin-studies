package aulas

import java.util.TreeMap

fun main(args: Array<String>) {

    val binaryRepresentation = TreeMap<Char, String>()
    for (c in 'A'..'F'){
        val binary = Integer.toBinaryString(c.code)
        binaryRepresentation[c] = binary
    }

    for ((letter, value ) in binaryRepresentation){
        println("$letter - $value")
    }
}