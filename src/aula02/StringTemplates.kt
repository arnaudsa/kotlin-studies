package aula02

fun main(args: Array<String>) {
    val nome = "Ana Luiza"
    println("Seu nome é $nome")
    println("Seu nome é \$nome")
    println("O total da soma é ${soma(7, 7 )}")
}

fun soma(a: Int, b: Int): Int {
    return a + b
}
