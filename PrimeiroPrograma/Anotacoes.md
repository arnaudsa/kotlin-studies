### Funções

<p>If é uma expressão e não uma declaração, podemos utilizar o proprio para retornar algo</p>

**Corpo de bloco** - Quando temos uma função escrita entre as chaves
```kotlin
fun max(a: Int, b: Int): Int {
    return if (a > b) a else b
}
```

**Corpo de expressão** - Quando uma função consiste apenas de uma expressão
````kotlin
fun max1(a: Int, b: Int) = if (a > b) a else b

fun max1(a: Int, b: Int): Int = if (a > b) a else b
````
--------------------------------------

### Variáveis
<p>Representa um valor ou expressão na memória é declarada com a palavra chave (var ou val) seguida do nome, o tipo da variável pode ser especificado ou não depois do nome</p>

##### Var VS Val
- Val (de valor) representa uma referência imutável, ou seja, seu estado não pode ser modificado após sua criação
- Var (de variável) representa uma referência mutável, ou seja, seu estado pode ser modificado após sua criação


```kotlin 
val nome = "Douglas" // Inferência de tipo
var nome:String = "Douglas" // Declaração de tipo

var nome:String
nome = "Douglas"
```
--------------------------------------

### String Templates
<p>Criado para facilitar a contatenação de Strings, pode ser utilizado como expressão também, utilizamos o <b>$</b> antes do nome da variável, para ignorar o template utilizamos o <b>\$</b></p>

```kotlin
fun main(args: Array<String>) {
    val nome = "Ana Luiza"
    println("Seu nome é $nome")
    println("Seu nome é \$nome")
    println("O total da soma é ${soma(7, 7 )}")
}

fun soma(a: Int, b: Int): Int {
return a + b
}
```
--------------------------------------

### Classes
<p>Temos um exemplo da criação de uma classe e criação de uma instância dessa classe.</p>

```kotlin
class Pessoa(
    val nome: String,
    val idade: Int
)

fun main(args: Array<String>) {
    val pessoa:Pessoa = Pessoa("Arnaud", 39)
    println("Pessoa: ${pessoa.nome} com idade: ${pessoa.idade}")
}
```

##### Assesores Customizados

```kotlin
class Retangulo(val altura: Int, val largura: Int) {
    
    // Utilizando corpo de bloco
    val isQuadrado: Boolean
        get() {
            return altura == largura
        }
    
    // Utilizando corpo de expressão
    val isQuadrado get() = altura == largura

    // Criando uma função
    fun isQuadrado():Boolean{
        return altura == largura
    }

}
```
--------------------------------------
### Enums
<p>Caso precisemos criar uma função dentro de uma enum será necessário a utilização do <b>ponto e vírgula(;)</b></p>

```kotlin
enum class Cor (private val vermelho:Int, private val verde:Int, private val azul:Int) {
    VERMELHO(255,0,0),
    LARANJA(255,165,0),
    AMARELO(255,255,0),
    VERDE(0,255,0),
    AZUL(0,0,255);

    fun rgb() = ((vermelho * 256 + verde) * 256 + azul)
}
```
--------------------------------------

### When
```kotlin
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
```
--------------------------------------

### Smart Casts
<p>No Kotlin quando fazemos a verificação de um type object não se faz necessário o cast explicito, pois o compilador já fez o trabalho de Cast</p>

```kotlin
interface Expressao

class Numero(val valor: Int): Expressao
class Soma(val esquerdo: Expressao, val direito: Expressao): Expressao

fun avaliacao(expressao: Expressao): Int{
    if(expressao is Numero){
        return expressao.valor
    }

    if (expressao is Soma){
        return avaliacao(expressao.esquerdo) + avaliacao(expressao.direito)
    }

    throw IllegalArgumentException ("Expressão desconhecida")
}

fun main(args: Array<String>) {
    val resultado = avaliacao(Soma( Soma(Numero(1), Numero(2)), Numero(4)))
    println(resultado)
}
```

--------------------------------------
### Bloco como ramificações if e when
<p>Ambos if e when pode ter blocos como ramificações, a última expressão do bloco é o resultado</p>

```kotlin
interface ExpressaoA

class Num(val valor: Int): ExpressaoA
class Som(val esquerdo: ExpressaoA, val direito: ExpressaoA): ExpressaoA

fun avaliacao(expressao: ExpressaoA) :Int =
    when(expressao){
        is Num -> { // Estamos utilizando um bloco, é a última linha desse bloco é o resultado
            print("Expressão com número ${expressao.valor}")
            expressao.valor
        }
        is Som -> avaliacao(expressao.direito) + avaliacao(expressao.esquerdo)
        else -> throw IllegalArgumentException ("Expressão desconhecida")
    }
```
--------------------------------------

### Estrutura de Repetição
<p>As estrutura de repetição While e Do While é igual ao Java, porém o for utiliza o conceito de range.</p>

```kotlin
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
}
```

##### Repetição em um map

```kotlin
    val binaryRepresentation = TreeMap<Char, String>()
    for (c in 'A'..'F'){
        val binary = Integer.toBinaryString(c.code)
        binaryRepresentation[c] = binary
    }

    for ((letter, value ) in binaryRepresentation){
        println("$letter - $value")
    }
```

--------------------------------------

### Operador In

```kotlin
fun main(args: Array<String>) {
    println("7 é letra: ${isLetter('7')}")
    println("B é letra: ${isLetter('B')}")
    println("5 não é digito: ${isLetter('5')}")
}

fun isLetter(c: Char) = c in 'a'..'z' || c in 'A'..'Z'

fun isNotDigit(c: Char) = c !in '0' .. '9'
```
