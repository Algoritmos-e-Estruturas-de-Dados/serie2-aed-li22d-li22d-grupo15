package serie2.problema

fun main() {
    while (true) {
        println("Selecione a implementação a utilizar:")
        println("1 - Kotlin Standard Library")
        println("2 - HashMap personalizada (serie2.part4.HashMap)")

        val opcao = readLine()?.trim()
        if (opcao == "1") {
            Implementacao1().run()
            break
        } else if (opcao == "2") {
            Implementacao2().run()
            break
        } else {
            println("Opção inválida. Tente novamente.\n")
        }
    }
}