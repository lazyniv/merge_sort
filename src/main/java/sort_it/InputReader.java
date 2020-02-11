package sort_it;

import java.io.InputStream;
import java.util.Scanner;

interface InputReader<T> {
    T getNext();
    boolean hasNext();
}

