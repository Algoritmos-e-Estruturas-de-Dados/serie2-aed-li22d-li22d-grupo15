package serie2.problema

import kotlin.time.Duration.Companion.ZERO
import kotlin.time.measureTime

fun main() {
    // Lista de conjuntos de ficheiros para testar
    val testSets = listOf(
        Triple("F1.co", "F1r.co", "output.co"),
        Triple("F2.co", "F2r.co", "output2.co"),
        Triple("F3.co", "F3r.co", "output3.co"),
        Triple("F4.co", "F4r.co", "output4.co"),
        Triple("F7x.co", "F8x.co", "output5.co"),
        Triple("F4r.co", "F6r.co", "output6.co")
    )

    val repetitions = 5

    for ((file1, file2, output) in testSets) {
        println("==== Teste de Performance para $file1 e $file2 ($repetitions execuções) ====\n")
        runPerformanceTests(file1, file2, output, repetitions)
        println("\n--------------------------------------\n")
    }
}

fun runPerformanceTests(file1: String, file2: String, output: String, repetitions: Int) {
    // IMPLEMENTAÇÃO 1
    var load1 = ZERO
    var union1 = ZERO
    var inter1 = ZERO
    var diff1 = ZERO
    var total1 = ZERO

    repeat(repetitions) {
        val imp1 = Implementacao1()
        total1 += measureTime {
            load1 += measureTime { imp1.runCommand("load $file1 $file2") }
            union1 += measureTime { imp1.runCommand("union $output") }
            inter1 += measureTime { imp1.runCommand("intersection $output") }
            diff1 += measureTime { imp1.runCommand("difference $output") }
        }
    }

    println("---- Implementação 1 ----")
    println("Tempo médio de load: ${load1 / repetitions}")
    println("Tempo médio de union: ${union1 / repetitions}")
    println("Tempo médio de intersection: ${inter1 / repetitions}")
    println("Tempo médio de difference: ${diff1 / repetitions}")
    println("⏱ Tempo total médio: ${total1 / repetitions}\n")

    // IMPLEMENTAÇÃO 2
    var load2 = ZERO
    var union2 = ZERO
    var inter2 = ZERO
    var diff2 = ZERO
    var total2 = ZERO

    repeat(repetitions) {
        val imp2 = Implementacao2()
        total2 += measureTime {
            load2 += measureTime { imp2.runCommand("load $file1 $file2") }
            union2 += measureTime { imp2.runCommand("union $output") }
            inter2 += measureTime { imp2.runCommand("intersection $output") }
            diff2 += measureTime { imp2.runCommand("difference $output") }
        }
    }

    println("---- Implementação 2 ----")
    println("Tempo médio de load: ${load2 / repetitions}")
    println("Tempo médio de union: ${union2 / repetitions}")
    println("Tempo médio de intersection: ${inter2 / repetitions}")
    println("Tempo médio de difference: ${diff2 / repetitions}")
    println("⏱ Tempo total médio: ${total2 / repetitions}")
}