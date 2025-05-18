package serie2.problema

import java.io.File

data class Point(val id: String, val x: Int, val y: Int)

class Implementacao1 {
    private val pointsMap = mutableMapOf<Point, Pair<Boolean, Boolean>>()
    private var loaded = false

    fun run() {
        while (true) {
            print("> ")
            val input = readLine() ?: break
            if (input.trim() == "exit") break
            runCommand(input)
        }
    }

    fun runCommand(comando: String) {
        val parts = comando.trim().split(" ")
        if (parts.isEmpty()) return

        when (parts[0]) {
            "load" -> {
                if (parts.size != 3) {
                    println("Comando 'load' precisa de 2 argumentos")
                    return
                }
                pointsMap.clear()

                val set1 = carregar(parts[1])
                val set2 = carregar(parts[2])

                for (p in set1) {
                    pointsMap[p] = true to false
                }

                for (p in set2) {
                    val atual = pointsMap[p]
                    pointsMap[p] = if (atual != null) atual.first to true else false to true
                }

                loaded = true
            }

            "union" -> {
                if (!loaded || parts.size < 2) {
                    println("Arquivo não carregado ou nome de saída inválido")
                    return
                }

                val result = pointsMap.keys
                guardar(parts[1], result)
            }

            "intersection" -> {
                if (!loaded || parts.size < 2) {
                    println("Arquivo não carregado ou nome de saída inválido")
                    return
                }

                val result = pointsMap.filterValues { it.first && it.second }.keys
                guardar(parts[1], result)
            }

            "difference" -> {
                if (!loaded || parts.size < 2) {
                    println("Arquivo não carregado ou nome de saída inválido")
                    return
                }

                val result = pointsMap.filterValues { it.first && !it.second }.keys
                guardar(parts[1], result)
            }

            "exit" -> return

            else -> println("Comando desconhecido: ${parts[0]}")
        }
    }

    private fun carregar(nome: String): Set<Point> {
        val pontos = mutableSetOf<Point>()
        File("src/main/kotlin/serie2/problema/Files1/$nome").forEachLine { linha ->
            val partes = linha.trim().split(" ")
            if (partes.size == 4 && partes[0] == "v") {
                pontos += Point(partes[1], partes[2].toInt(), partes[3].toInt())
            }
        }
        return pontos
    }

    private fun guardar(nome: String, pontos: Collection<Point>) {
        File(nome).printWriter().use { out ->
            for (p in pontos) {
                out.println("${p.x.toDouble()}, ${p.y.toDouble()}")
            }
        }
    }
}
