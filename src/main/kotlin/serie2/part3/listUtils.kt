package serie2.part3

class Node<T> (
    var value: T = Any() as T,
    var next: Node<T>? = null,
    var previous: Node<T>? = null) {
}

fun splitEvensAndOdds(list: Node<Int>) {
    var current = list.next                 // Começa no primeiro elemento real da lista
    val sentinel = list                     // 'sentinel' é um nó especial que marca o fim/início da lista
    var evenTail: Node<Int> = sentinel      // Marca a última posição onde um número par foi inserido

    while (current != sentinel) {
        val next = current!!.next           // Guarda referência para o próximo nó
        if (current.value % 2 == 0) {       // Se o valor for par
            // Remove o nó da sua posição atual
            current.previous!!.next = current.next
            current.next!!.previous = current.previous

            // Insere o nó após evenTail (por exemplo, logo após o último número par)
            current.next = evenTail.next
            current.previous = evenTail
            evenTail.next!!.previous = current
            evenTail.next = current

            evenTail = current              // Atualiza o ponteiro do fim da sublista de pares
        }
        current = next                      // Avança para o próximo nó
    }
}


fun <T> intersection(list1: Node<T>, list2: Node<T>, cmp: Comparator<T>): Node<T>? {
    var it1: Node<T>? = list1.next         // Iterador da lista 1
    var it2: Node<T>? = list2.next         // Iterador da lista 2
    val sentinel1 = list1
    val sentinel2 = list2

    var head: Node<T>? = null              // Cabeça da nova lista de interseção
    var tail: Node<T>? = null              // Cauda da nova lista

    while (it1 != sentinel1 && it2 != sentinel2) {
        val compare = cmp.compare(it1!!.value, it2!!.value)
        when {
            compare < 0 -> it1 = it1.next   // Avança em list1 se o valor for menor
            compare > 0 -> it2 = it2.next   // Avança em list2 se o valor for maior
            else -> {
                val commonValue = it1.value
                val next1 = it1.next
                val next2 = it2!!.next

                // Remove it1 da list1
                it1.previous!!.next = it1.next
                it1.next!!.previous = it1.previous

                // Remove it2 da list2
                it2.previous!!.next = it2.next
                it2.next!!.previous = it2.previous

                // Prepara o nó it1 para ser adicionado à nova lista
                it1.next = null
                it1.previous = tail

                if (tail == null) {
                    head = it1              // Primeira inserção
                } else {
                    tail.next = it1         // Liga o novo nó ao fim da nova lista
                }
                tail = it1                  // Atualiza o fim da nova lista

                // Avança ambos os iteradores, ignorando duplicados
                it1 = next1
                it2 = next2

                while (it1 != sentinel1 && cmp.compare(it1!!.value, commonValue) == 0) {
                    it1 = it1.next
                }
                while (it2 != sentinel2 && cmp.compare(it2!!.value, commonValue) == 0) {
                    it2 = it2.next
                }
            }
        }
    }

    return head                             // Retorna a cabeça da nova lista com os elementos comuns
}
