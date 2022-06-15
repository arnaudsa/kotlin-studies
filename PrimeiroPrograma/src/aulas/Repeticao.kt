package aulas

fun fizzBuzz(numero: Int) = when {
    numero % 15 == 0 -> "FizzBuzz "
    numero % 3 == 0 -> "Fizz "
    numero % 5 == 0 -> "Buzz "
    else -> "$numero"
}

fun main(args: Array<String>) {
    for (numero in 1..100){
        println(fizzBuzz(numero))
    }

    for (i in 10 downTo 1){
        println("i = $i")
    }

    for (numero in 0..100 step 5){
        println(numero)
    }

    val familia = arrayListOf("Arnaud", "Lilia", "Gabriel", "Matheus", "Ana Luiza")
    for ((indice, p) in familia.withIndex()){
        println("${indice+1} - $p")
    }

}