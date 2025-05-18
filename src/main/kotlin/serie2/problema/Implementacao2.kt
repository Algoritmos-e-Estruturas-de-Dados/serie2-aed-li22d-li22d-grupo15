package serie2.problema

import serie2.part4.HashMap
import java.io.File

data class Point2(val id: String, val x: Int, val y: Int)

class Implementacao2 {
    private val map = HashMap<Point2, Pair<Boolean, Boolean>>()
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
                map.clear()

                val set1 = carregar(parts[1])
                val set2 = carregar(parts[2])

                for (p in set1) {
                    map.put(p, true to false)
                }

                for (p in set2) {
                    val atual = map.get(p)
                    map.put(p, if (atual != null) atual.first to true else false to true)
                }

                loaded = true
            }

            "union" -> {
                if (!loaded || parts.size < 2) {
                    println("Arquivo não carregado ou nome de saída inválido")
                    return
                }

                val result = mutableListOf<Point2>()
                for (entry in map) {
                    result.add(entry.key)
                }
                guardar(parts[1], result)
            }

            "intersection" -> {
                if (!loaded || parts.size < 2) {
                    println("Arquivo não carregado ou nome de saída inválido")
                    return
                }

                val result = mutableListOf<Point2>()
                for (entry in map) {
                    if (entry.value.first && entry.value.second) {
                        result.add(entry.key)
                    }
                }
                guardar(parts[1], result)
            }

            "difference" -> {
                if (!loaded || parts.size < 2) {
                    println("Arquivo não carregado ou nome de saída inválido")
                    return
                }

                val result = mutableListOf<Point2>()
                for (entry in map) {
                    if (entry.value.first && !entry.value.second) {
                        result.add(entry.key)
                    }
                }
                guardar(parts[1], result)
            }

            "exit" -> return

            else -> println("Comando desconhecido: ${parts[0]}")
        }
    }

    private fun carregar(nome: String): Set<Point2> {
        val pontos = mutableSetOf<Point2>()
        File("src/main/kotlin/serie2/problema/Files1/$nome").forEachLine { linha ->
            val partes = linha.trim().split(" ")
            if (partes.size == 4 && partes[0] == "v") {
                pontos += Point2(partes[1], partes[2].toInt(), partes[3].toInt())
            }
        }
        return pontos
    }

    private fun guardar(nome: String, pontos: Collection<Point2>) {
        File(nome).printWriter().use { out ->
            for (p in pontos) {
                out.println("${p.x.toDouble()}, ${p.y.toDouble()}")
            }
        }
    }
}
