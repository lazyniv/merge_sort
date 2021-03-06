package sort_it;

import java.io.InputStream;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;

abstract class Params {

    private List<String> inputFiles;
    private String outputFile;

    public void setInputFiles(List<String> inputFiles) {
        this.inputFiles = inputFiles;
    }

    public void setOutputFile(String outputFile) {
        this.outputFile = outputFile;
    }

    public List<String> getInputFiles() {
        return inputFiles;
    }

    public String getOutputFile() {
        return outputFile;
    }

    public abstract <T> Function<InputStream, InputReader<T>> inputReaderFactory();
    public abstract <T> Comparator<T> comparator();

    private static <T> Params newParams(Function<InputStream, InputReader<T>> inputReaderFactory, Comparator<T> comparator) {
        return new Params() {

            @Override
            public Function<InputStream, InputReader<T>> inputReaderFactory() {
                return inputReaderFactory;
            }

            @Override
            public Comparator<T> comparator() {
                return comparator;
            }

        };
    }

    public static Params integerInputParams(Comparator<Integer> comparator) {
        return newParams(Params::integerReader, comparator);
    }

    public static Params lineInputParams(Comparator<String> comparator) {
        return newParams(Params::lineReader, comparator);
    }

    public abstract static class PeekableScanner<T> implements InputReader<T> {
        protected Scanner scanner;

        private T next;
        private T current;

        PeekableScanner(InputStream source) {
            scanner = new Scanner(source);
            next = hasNextInner() ? getNextInner() : null;
            current = next;
        }

        @Override
        public T peekNext() {
            return next;
        }

        @Override
        public boolean hasNext() {
            return next != null;
        }

        @Override
        public T getNext() {
            current = next;
            next = hasNextInner() ? getNextInner() : null;
            return current;
        }

        @Override
        public T peekCurrent() {
            return current;
        }

        public abstract boolean hasNextInner();
        public abstract T getNextInner();
    }

    private static InputReader<Integer> integerReader(InputStream source) {
        return new PeekableScanner<Integer>(source) {

            @Override
            public Integer getNextInner() {
                return scanner.nextInt();
            }

            @Override
            public boolean hasNextInner() {
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
            public boolean hasNextInner() {
                return scanner.hasNextLine();
            }

        };
    }
}
