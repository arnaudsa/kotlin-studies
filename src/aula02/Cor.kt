package aula02

enum class Cor (private val vermelho:Int, private val verde:Int, private val azul:Int) {
    VERMELHO(255,0,0),
    LARANJA(255,165,0),
    AMARELO(255,255,0),
    VERDE(0,255,0),
    AZUL(0,0,255);

    fun rgb() = ((vermelho * 256 + verde) * 256 + azul)
}