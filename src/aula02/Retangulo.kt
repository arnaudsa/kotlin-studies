package aula02

class Retangulo(val altura: Int, val largura: Int) {
    // Podemos criar um acessor customizado dessa forma
    val isQuadrado get() = altura == largura

    fun isQuadrado2():Boolean{
        return altura == largura
    }
}