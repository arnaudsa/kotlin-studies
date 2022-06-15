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


##### Podemos adicionar uma nova funcionalidade a uma classe existente sem utilizar herança

```kotlin
// Estamos adicionando uma nova função a classe List
fun <E> List<E>.secondOrNull(): E? = if(this.size >= 2) this.get(1) else null

fun main(args: Array<String>) {
    val list = listOf("João", "Maria", "Pedro")
    print(list.secondOrNull())
}
```

##### Kotlin também aceita parametros nomeados e parametros com valor padrão

```kotlin
fun potencia(base: Int = 2, expoente: Int = 1): Int {
    return Math.pow(base.toDouble(), expoente.toDouble()).toInt()
}

fun main(args: Array<String>) {
    println(potencia(2, 3))
    println(potencia(10))
    println(potencia(base = 10))
    println(potencia(expoente = 8))
}
```

##### Função Infix
```kotlin
class Produto(val nome: String, val preco: Double)

infix fun Produto.maisCaroQue(produto: Produto): Boolean = this.preco > produto.preco

fun main(args: Array<String>) {
    val p1 = Produto("Ipad", 2349.00)
    val p2 = Produto(preco = 3.49, nome = "Borracha")
    println(p1 maisCaroQue p2)
    println(p2.maisCaroQue(p1))
}
```

##### Com a utilização do Destructuring nossas funções conseguem retornar múltiplos valores

```kotlin
import java.util.*

data class Horario(val hora: Int, val minuto: Int, val segundo: Int)

fun agora(): Horario {
    val agora = Calendar.getInstance()

    with(agora) {
        return Horario(get(Calendar.HOUR), get(Calendar.MINUTE), get(Calendar.SECOND))
    }
}

fun main(args: Array<String>) {
    val (h, m, s) = agora()
    println("$h:$m:$s")
}
```

##### Quando temos funções que não retornam valor em Kotlin podemos omitir esse argumento ou explicitamente utilizar o Unit

```kotlin
fun imprimeMaior1(a: Int, b: Int) {
    println(Math.max(a, b))
}

fun imprimeMaior2(a: Int, b: Int): Unit {
    println(Math.max(a, b))
}

fun imprimeMaior3(a: Int, b: Int): Unit {
    println(Math.max(a, b))
    return
}

fun imprimeMaior4(a: Int, b: Int): Unit {
    println(Math.max(a, b))
    return Unit
}

fun imprimeMaior5(a: Int, b: Int) {
    println(Math.max(a, b))
    return Unit
}
```

##### Passando uma função como parametro

```kotlin
class Operacoes {
    fun somar(a: Int, b: Int): Int {
        return a + b
    }
}

fun somar(a: Int, b: Int): Int {
    return a + b
}

fun calc(a: Int, b: Int, funcao: (Int, Int) -> Int) : Int {
    return funcao(a, b)
}

fun main(args: Array<String>) {
    println(calc(2, 3, Operacoes()::somar))
    println(calc(2, 3, ::somar))
}
```

##### Passando funções como parametros exemplo 2

```kotlin
fun <E> filtrar(lista: List<E>, filtro: (E) -> Boolean): List<E> {
    val listaFiltrada = ArrayList<E>()
    for(e in lista) {
        if(filtro(e)) {
            listaFiltrada.add(e)
        }
    }
    return listaFiltrada
}

fun comTresLetras(nome: String): Boolean {
    return nome.length == 3
}

fun main(args: Array<String>) {
    val nomes = listOf("Ana", "Pedro", "Bia", "Gui", "Rebeca")
    println(filtrar(nomes, ::comTresLetras))
}
```


##### Função Inline

```kotlin
inline fun transacao(funcao: () -> Unit) {
    println("abrindo transação...")
    try {
        funcao()
    } finally {
        println("fechando transação")
    }
}

fun main(args: Array<String>) {
    transacao {
        println("Executando SQL 1...")
        println("Executando SQL 2...")
        println("Executando SQL 3...")
    }
}
```

##### Função Inline exemplo 2

```kotlin
inline fun <T> executarComLog(nomeFuncao: String, funcao: () -> T): T {
    println("Entrando no método $nomeFuncao...")
    try {
        return funcao()
    } finally {
        println("Método $nomeFuncao finalizado...")
    }
}

fun somar2(a: Int, b: Int): Int {
    return a + b
}

fun main(args: Array<String>) {
    val resultado = executarComLog("somar") {
        somar2(4, 5)
    }

    println(resultado)
}
```

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
    sentimento()
}

fun soma(a: Int, b: Int): Int {
    return a + b
}

fun sentimento(){
    val humor = false
    println("Hoje estou ${if(humor) "feliz" else "chateado" }")
}
```
--------------------------------------

### Classes
<p>Temos um exemplo da criação de uma classe e criação de uma instância dessa classe.</p>

```kotlin
class Pessoa1(var nome: String) // Construtor com atributo variavel
class Pessoa2(val nome: String)  // Construtor com atributo constante
class Pessoa3(nomeInicial: String) { // Construtor com atributo disponível somente na inicialização
    val nome: String = nomeInicial
}

fun main(args: Array<String>) {
    val p1 = Pessoa1(nome = "João")
    p1.nome = "Guilherme"
    println("${p1.nome} sabe programar!")

    val p2 = Pessoa2("Maria")
    val p3 = Pessoa3(nomeInicial = "Pedro")
    println("${p2.nome} e ${p3.nome} são legais!")
}
```

##### Classe Membro
```kotlin
class Calculadora {
    private var resultado: Int = 0

    fun somar(vararg valores: Int): Calculadora {
        valores.forEach { resultado += it }
        return this
    }

    fun multiplicar(valor: Int): Calculadora {
        resultado *= valor
        return this
    }

    fun limpar(): Calculadora {
        resultado = 0
        return this
    }

    fun print(): Calculadora {
        println(resultado)
        return this
    }

    fun obterResultado(): Int {
        return resultado
    }
}

fun main(args: Array<String>) {
    val calculadora = Calculadora()
    calculadora.somar(1, 2, 3).multiplicar(3).print()
    calculadora.somar(7, 10).print().limpar()

    println(calculadora.obterResultado())
}
```

##### Classes vs Data Class

```kotlin
class Geladeira(val marca: String, val litros: Int)
data class Televisao(val marca: String, val polegadas: Int)

fun main(args: Array<String>) {
    val g1 = Geladeira("Brastemp", 320)
    val g2 = Geladeira("Brastemp", 320)

    println(g1 == g2) // equals

    val tv1 = Televisao("Sansung", 32)
    val tv2 = Televisao("Sansung", 32)

    println(tv1 == tv2) // equals
    println(tv1 === tv2)
    println(tv1.toString())
    println(tv1.copy())
    println(tv1.copy(polegadas = 42))

    // Destructuring em data class
    val (marca, pol) = tv1
    println("$marca $pol'")
}
```

### Construtor

Exemplo 1
```kotlin
class Filme {
    val nome: String
    val anoLancamento: Int
    val genero: String

    constructor(nome: String, anoLancamento: Int, genero: String) {
        this.nome = nome
        this.anoLancamento = anoLancamento
        this.genero = genero
    }
}

fun main(args: Array<String>) {
    val filme = Filme("O Poderoso Chefão", 1972, "Drama")
    println("O ${filme.genero} ${filme.nome} foi lançado em ${filme.anoLancamento}.")
}
```

Exemplo 2
```kotlin
class Filme2(val nome: String, val anoLancamento: Int, val genero: String)

fun main(args: Array<String>) {
    val filme = Filme2("Monstros S.A", 2001, "Comédia")
    println("A ${filme.genero} ${filme.nome} foi lançada em ${filme.anoLancamento}.")
}
```

Exemplo 3 com bloco Init

```kotlin
class Filme3(nome: String, anoLancamento: Int, genero: String) {
    val nome: String
    val anoLancamento: Int
    val genero: String

    init {
        this.nome = nome
        this.anoLancamento = anoLancamento
        this.genero = genero
    }
}

fun main(args: Array<String>) {
    val filme = Filme3("Os Incríveis", 2004, "Ação")
    println("${filme.nome} foi lançado em ${filme.anoLancamento}.")
}
```

##### Variável Statica
Exemplo 1
```kotlin
class ItemDePedido(val nome: String, val preco: Double) {
    companion object {
        fun create(nome: String, preco: Double) = ItemDePedido(nome, preco)
        @JvmStatic var desconto: Double = 0.0
    }

    fun precoComDesconto(): Double {
        return preco - preco * desconto
    }
}

fun main(args: Array<String>) {
    val item1 = ItemDePedido.create("TV 50 Polegadas", 2989.90)
    val item2 = ItemDePedido("Liquidificador", 200.0)
    ItemDePedido.desconto = 0.10
    println(item1.precoComDesconto())
    println(item2.precoComDesconto())
}
```
Exemplo 2

```kotlin
var desconto: Double = 0.0

class ItemDePedido(val nome: String, val preco: Double) {
    companion object {
        fun create(nome: String, preco: Double) = ItemDePedido(nome, preco)
        //@JvmStatic var desconto: Double = 0.0
    }

    fun precoComDesconto(): Double {
        return preco - preco * desconto
    }
}

fun main(args: Array<String>) {
    val item1 = ItemDePedido.create("TV 50 Polegadas", 2989.90)
    val item2 = ItemDePedido("Liquidificador", 200.0)
    desconto = 0.10

    println(item1.precoComDesconto())
    println(item2.precoComDesconto())
}
```

##### Assesores Customizados

Exemplo 1
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

Exemplo 2
```kotlin
class Cliente2 {
    constructor(nome: String) {
        this.nome = nome
    }

    var nome: String
        get() = "Meu nome é ${field}"
        set(value) {
            field = value.takeIf { value.isNotEmpty() } ?: "Anônimo"
        }
}

fun main(args: Array<String>) {
    val c1 = Cliente2("")
    println(c1.nome)

    val c2 = Cliente2("Pedro")
    c2.nome = "Ana"
    println(c2.nome)
}
```

Exemplo 3

```kotlin
class Produto(var nome: String, var preco: Double, var desconto: Double, var ativo: Boolean) {
    val inativo: Boolean get() = !ativo
    val precoComDesconto: Double get() = preco * (1 - desconto)
}

fun main(args: Array<String>) {
    val p1 = Produto("iPad", 2349.90, 0.20, ativo = true)
    println(p1.precoComDesconto)

    val p2 = Produto("Galaxy Note 7", 2699.49, 0.50, ativo = false)
    println("${p2.nome}\n\tDe: R$ ${p2.preco} \n\tPor: R$ ${p2.precoComDesconto}")

    if(p2.inativo) {
        p2.preco = 0.0
        println("Depois de inativo: R$ ${p2.precoComDesconto}")
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

fun main(args: Array<String>) {
    println(obterMnemonica(Cor.AZUL))
    println(obterTemperatura(Cor.VERMELHO))
    nota()
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

##### Cast explicito

```kotlin
fun imprimirConceito(nota: Any) {
    when(nota as? Int) {
        10, 9 -> println("A")
        8, 7 -> println("B")
        6, 5 -> println("C")
        4, 3 -> println("D")
        2, 1, 0 -> println("E")
        else -> println("Nota inválida")
    }
}

fun main(args: Array<String>) {
    val notas = arrayOf(9.6, 3.8, 7.2, 5.5, 4.1)
    for(nota in notas) {
        imprimirConceito(nota.toInt())
    }
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
    
    // Decrescente
    for (i in 10 downTo 1){
        println("i = $i")
    }
    
    // Personalizando o For com step
    for (numero in 0..100 step 5){
        println(numero)
    }
    
    // For com Indice
    val familia = arrayListOf("Arnaud", "Lilia", "Gabriel", "Matheus", "Ana Luiza")
    for ((indice, p) in familia.withIndex()){
        println("${indice+1} - $p")
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

### Operador In ou Range

```kotlin
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
```
--------------------------------------
### Operador de Chamada Segura
<p>Quando precisamos dizer que um atributo pode não conter valor utilizamos o operador <b>(? safe call operator)</b> ao executar o código com o operador seguro não teremos um erro de null pointer exception, o programa simplesmente imprimi null.</p>

```kotlin
fun main(args: Array<String>) {
    var a: Int? = null
    println(a?.dec())
}
```
--------------------------------------
### Operador Elvis
<p>Com o operador Elvis conseguimos definir um valor padrão quando um atributo for passivel de nulo.</p>

```kotlin
fun main(args: Array<String>) {
    val opcional: String? = null
    val obrigatorio:String = opcional ?: "Valor Padrão"
    println(obrigatorio)
    println(a!!.dec()) // Forçando null pointer exception
}
```

--------------------------------------
### Destructuring
<p>Desestruturando estruturas</p>

```kotlin
data class Carro(val marca:String, val modelo:String)

fun main(args: Array<String>) {
    val (marca, modelo) = Carro("Ford", "Fusion")
    println("$marca $modelo")

    val (marido, mulher) = listOf("João", "Maria")
    println("$marido e $mulher")

    val (_, _, terceiroLugar) = listOf("Kimi", "Hamilton", "Alonso")
    println("$terceiroLugar terminou em terceiro lugar.")
}
```
--------------------------------------
### Lambda
<p>Lambda é uma função anonima onde conseguimos atribuir ou armazenar essas funções dentro de variáveis ou passar como parametros para outras funções.</p>

Exemplo 1
```kotlin
val soma = {x: Int, y: Int -> x+ y}
println(soma(4,3))
```

Exemplo 2 - SortedBy
```kotlin
fun main(args: Array<String>) {
    val nomes = arrayListOf("Renata", "Bernardo", "Willian", "Andreia", "Caio")
    val ordenados = nomes.sortedBy { it.reversed() }

    println(ordenados)
}
```

Exemplo 3 - Filter e SortedBy
```kotlin
data class Aluno(val nome: String, val nota: Double)

fun main(args: Array<String>) {
    val alunos = arrayListOf(
            Aluno("Pedro", 7.4),
            Aluno("Artur", 8.0),
            Aluno("Rafael", 9.7),
            Aluno("Ricardo", 5.7)
    )
    
    val aprovados = alunos.filter { it.nota >= 7.0 }.sortedBy { it.nome }

    println(aprovados)
}
```

Exemplo 4 - Map
```kotlin
fun main(args: Array<String>) {
    val alunos = arrayListOf("Pedro", "Tiago", "Jonas")
    alunos.map { it.toUpperCase() }.apply { print(this) }
}
```

Exemplo 5 - Map com reduce
```kotlin
class Produto(val nome: String, val preco: Double)

val materialEscolar = listOf(
        Produto("Fichário escolar", 21.90),
        Produto("Lápis de cor", 11.90),
        Produto("Borracha bicolor", 0.70),
        Produto("Apontador com depósito", 1.80)
)

fun main(args: Array<String>) {
    val totalizar = { total: Double, atual: Double -> total + atual }
    val precoTotal = materialEscolar.map { it.preco }.reduce(totalizar)

    println("O preço médio é R$ ${precoTotal / materialEscolar.size}.")
}
```

Exemplo 6 - TakeIf
```kotlin

fun main(args: Array<String>) {
    println("Digite sua mensagem: ")

    val entrada = readLine()
    val mensagem = entrada.takeIf { it?.trim() != "" } ?: "Sem mensagem"

    println(mensagem)
}
```

Exemplo 7 - Apply e With
```kotlin

class Calculadora2 {
    var resultado = 0

    fun soma(valor1: Int, valor2: Int) {
        resultado += valor1 + valor2
    }

    fun add(valor: Int) {
        resultado += valor
    }
}

fun main(args: Array<String>) {
    val calculadora = Calculadora2()

    calculadora.apply { soma(4, 5) }.apply { add(5) }.apply { println(resultado) }

    calculadora.apply {
        soma(4, 5)
        add(5)
        println(resultado)
    }

    // inline function
    with(calculadora) {
        soma(4, 5)
        add(5)
        println(resultado)
    }
}
```
### Apostila Curso Cod3r
http://files.cod3r.com.br/apostila-kotlin.pdf <br>
https://github.com/cod3rcursos/curso-kotlin

Dokka ferramenta para intepretar os comentários feitos em kdoc