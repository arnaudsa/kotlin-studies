package aulas

fun main(args: Array<String>) {
    println("7 é letra: ${isLetter('7')}")
    println("B é letra: ${isLetter('B')}")
    println("5 não é digito: ${isLetter('5')}")
}

fun isLetter(c: Char) = c in 'a'..'z' || c in 'A'..'Z'

fun isNotDigit(c: Char) = c !in '0' .. '9'

fun notas(nota:Int){
    if (nota in 9..10){
        println("Fantástico")
    }else if (nota in 7..8){
        println("Parabéns")
    }else if (nota in 4..6){
        println("Tem como recuperar")
    }else if (nota in 0..3){
        println("Te vejo no próximo semestre")
    }else{
        println("Nota inválida")
    }
}