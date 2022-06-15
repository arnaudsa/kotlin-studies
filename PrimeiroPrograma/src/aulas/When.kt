package aulas

fun obterMnemonica(cor: Cor) =
    when(cor){
        Cor.VERMELHO -> "Ver"
        Cor.LARANJA -> "Alguém"
        Cor.AMARELO -> "Viver"
        Cor.VERDE -> "Além é"
        Cor.AZUL -> "Legal"
    }

fun obterTemperatura(cor: Cor): String {
    return when(cor){
        Cor.VERMELHO, Cor.LARANJA, Cor.AMARELO -> "Quente"
        Cor.VERDE -> "Neutro"
        Cor.AZUL -> "Frio"
    }
}

fun main(args: Array<String>) {
    println(obterMnemonica(Cor.AZUL))
    println(obterTemperatura(Cor.VERMELHO))
}

fun nota(){
    val nota = 5
    when(nota){
        10,9 -> println("Fantástico")
        8,7 -> println("Parabéns")
        6,5,4 -> println("Tem como recuperar")
        in 0..3 -> println("Te vejo no próximo semestre")
        else -> println("Nota inválida")

    }
}