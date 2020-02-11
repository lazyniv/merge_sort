package sort_it;

import java.io.InputStream;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;

abstract class Params {
    abstract <T> Function<InputStream, InputReader<T>> inputReaderFactory();
    abstract <T> Comparator<T> comparator();
    List<String> files;

    private static <T> Params newParams(Function<InputStream, InputReader<T>> inmputReaderFactory, Comparator<T> comparator) {
        return new Params() {

            @Override
            Function<InputStream, InputReader<T>> inputReaderFactory() {
                return inmputReaderFactory;
            }

            @Override
            Comparator<T> comparator() {
                return comparator;
            }
        };
    }

    static Params integerInputParams(Comparator<Integer> comparator) {
        return newParams(Params::integerReader, comparator);
    }

    static Params integerLinesParams(Comparator<String> comparator) {
        return newParams(Params::lineReader, comparator);
    }

    private static InputReader<Integer> integerReader(InputStream source) {
        Scanner scanner = new Scanner(source);
        return new InputReader<Integer>() {
            @Override
            public Integer getNext() {
                return scanner.nextInt();
            }

            @Override
            public boolean hasNext() {
                return scanner.hasNext();
            }
        };
    }

    private static InputReader<String> lineReader(InputStream source) {
        Scanner scanner = new Scanner(source);
        return new InputReader<String>() {
            @Override
            public String getNext() {
                return scanner.nextLine();
            }

            @Override
            public boolean hasNext() {
                return scanner.hasNext();
            }
        };
    }
}
