package sort_it;

interface InputReader<T> {
    T getNext();
    boolean hasNext();
    T peek();
}

