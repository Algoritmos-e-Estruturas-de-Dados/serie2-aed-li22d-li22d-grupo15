package serie2.part1_2
import kotlin.random.Random

//As folhas num heap armazenado em array estão nos últimos níveis da árvore.
/*Para um heap de tamanho heapSize, os nós internos (não-folhas) vão de 0 até heapSize/2 - 1. Assim, as folhas estão
nos índices de heapSize/2 até heapSize - 1.*/

fun minimum(maxHeap: Array<Int>, heapSize: Int): Int {
    var min = maxHeap[heapSize / 2]  // Começamos pelo primeiro nó folha
    for (i in heapSize / 2 until heapSize) {
        if (maxHeap[i] < min) {
            min = maxHeap[i]
        }
    }
    return min
}
