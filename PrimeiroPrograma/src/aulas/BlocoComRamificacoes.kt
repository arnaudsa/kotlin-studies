package aulas

interface ExpressaoA

class Num(val valor: Int): ExpressaoA
class Som(val esquerdo: ExpressaoA, val direito: ExpressaoA): ExpressaoA

fun avaliacao(expressao: ExpressaoA) :Int =
    when(expressao){
        is Num -> { // Estamos utilizando um bloco, é a última linha desse bloco é o resultado
            print("Expressão com número $expressao.valor")
            expressao.valor
        }
        is Som -> avaliacao(expressao.direito) + avaliacao(expressao.esquerdo)
        else -> throw IllegalArgumentException ("Expressão desconhecida")
    }