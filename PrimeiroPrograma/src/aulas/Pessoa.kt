package aulas

class Pessoa(
    val nome: String,
    val idade: Int
)

fun main(args: Array<String>) {
    val pessoa1:Pessoa = Pessoa("Arnaud", 39)
    val pessoa2:Pessoa = Pessoa("Lilia", 44)
    val pessoa3:Pessoa = Pessoa("Ana Luiza", 5)

    println("Pessoa 1: ${pessoa1.nome} com idade: ${pessoa1.idade}")
    println("Pessoa 2: ${pessoa2.nome} com idade: ${pessoa2.idade}")
    println("Pessoa 3: ${pessoa3.nome} com idade: ${pessoa3.idade}")
}