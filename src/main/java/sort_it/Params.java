package sort_it;

import java.io.InputStream;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;

abstract class Params {
    List<String> inputFiles;
    String outputFile;

    abstract <T> Function<InputStream, InputReader<T>> inputReaderFactory();
    abstract <T> Comparator<T> comparator();

    private static <T> Params newParams(Function<InputStream, InputReader<T>> inputReaderFactory, Comparator<T> comparator) {
        return new Params() {

            @Override
            Function<InputStream, InputReader<T>> inputReaderFactory() {
                return inputReaderFactory;
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

    static Params lineInputParams(Comparator<String> comparator) {
        return newParams(Params::lineReader, comparator);
    }

    abstract static class PeekableScanner<T> implements InputReader<T> {
      Scanner scanner;
      T next;

      PeekableScanner(InputStream source) {
          scanner = new Scanner(source);
          next = hasNextInner() ? getNextInner() : null;
      }

      @Override
      public T peek() {
          return next;
      }

      @Override
      public boolean hasNext() {
          return next != null;
      }

      @Override
      public T getNext() {
          T current = next;
          next = hasNextInner() ? getNextInner() : null;
          return current;
      }

      abstract boolean hasNextInner();
      abstract T getNextInner();
    }

    private static InputReader<Integer> integerReader(InputStream source) {
        return new PeekableScanner<Integer>(source) {
            @Override
            public Integer getNextInner() {
                return scanner.nextInt();
            }

            @Override
            boolean hasNextInner() {
                return scanner.hasNextInt();
            }
        };
    }

    private static InputReader<String> lineReader(InputStream source) {
        return new PeekableScanner<String>(source) {
            @Override
            public String getNextInner() {
                return scanner.nextLine();
            }

            @Override
            boolean hasNextInner() {
                return scanner.hasNextLine();
            }
        };
    }
}
