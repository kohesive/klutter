package uy.klutter.core.collections


/**
 * Batch a sequence into a sequence of lists of max N size
 */
fun <T> Sequence<T>.batch(n: Int): Sequence<List<T>> {
    return BatchingSequence(this.asIterable(), n)
}

/**
 * Batch a sequence into a sequence of lists of max N size
 */
fun <T> Iterable<T>.batch(n: Int): Sequence<List<T>> {
    return BatchingSequence(this, n)
}

/**
 * Batch a sequence into a sequence of lists of max N size, and execute a lambda for each group
 */
fun <T> Sequence<T>.batch(n: Int, forEachDo: (List<T>)->Unit) {
    BatchingSequence(this.asIterable(), n).forEach { group -> forEachDo(group) }
}

/**
 * Batch a sequence into a sequence of lists of max N size, and execute a lambda for each group
 */
fun <T> Iterable<T>.batch(n: Int, forEachDo: (List<T>)->Unit) {
    BatchingSequence(this, n).forEach { group -> forEachDo(group) }
}

private class BatchingSequence<T>(val source: Iterable<T>, val batchSize: Int) : Sequence<List<T>> {
    override fun iterator(): Iterator<List<T>> = object : AbstractIterator<List<T>>() {
        private val iterate = if (batchSize > 0) source.iterator() else emptyList<T>().iterator()
        override fun computeNext() {
            if (iterate.hasNext()) setNext(iterate.asSequence().take(batchSize).toList())
            else done()
        }
    }
}


/**
 * A purely Lazy batch must have the source consumed to progress, but does not need to materialize a list per iteration
 * So, for purely lazy we only allow basically forEach when completely lazy
 */
fun <T> Sequence<T>.lazyBatch(n: Int, forEachDo: (Sequence<T>)->Unit) {
    LazyBatchingSequence(this.asIterable(), n).forEach { group ->
        forEachDo(group)
    }
}

/**
 * A purely Lazy batch must have the source consumed to progress, but does not need to materialize a list per iteration
 * So, for purely lazy we only allow basically forEach when completely lazy
 */
fun <T> Iterable<T>.lazyBatch(n: Int, forEachDo: (Sequence<T>)->Unit) {
    LazyBatchingSequence(this, n).forEach { group ->
        forEachDo(group)
    }
}

private class LazyBatchingSequence<T>(val stream: Iterable<T>, val groupSize: Int) : Sequence<Sequence<T>> {
    override fun iterator(): Iterator<Sequence<T>> = object : AbstractIterator<Sequence<T>>() {
        private val iterate = if (groupSize > 0) stream.iterator() else emptyList<T>().iterator()
        private var previousBatch: LimitIteratorByCountStream<T>? = null
        override fun computeNext() {
            previousBatch?.consumeToLimit()
            if (iterate.hasNext()) {
                val newBatch = LimitIteratorByCountStream(iterate, groupSize)
                setNext(newBatch)
                previousBatch = newBatch
            } else {
                done()
            }
        }
    }

    class LimitIteratorByCountStream<T>(val iterator: Iterator<T>, val limit: Int) : Sequence<T> {
        private var count = 0

        private val iteratorRef = java.util.concurrent.atomic.AtomicReference(object : AbstractIterator<T>() {
            override fun computeNext() {
                if (!iterator.hasNext() || count >= limit) {
                    done()
                } else {
                    count++
                    setNext(iterator.next())
                }
            }
        })


        override fun iterator(): Iterator<T> {
            return iteratorRef.getAndSet(null) ?: throw IllegalStateException("This sequence can be consumed only once.")
        }

        fun consumeToLimit() {
            while (iterator.hasNext() && count < limit) {
                count++
                iterator.next()
            }
        }
    }
}
