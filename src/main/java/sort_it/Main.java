package sort_it;

import org.apache.commons.cli.ParseException;

import java.io.PrintStream;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) throws ParseException {
      Params p = CLIParser.parse(args);
      merge(
              p.inputReaderFactory().apply(System.in),
              p.inputReaderFactory().apply(System.in),
              p.comparator(),
              System.out
      );

    }

    static <T> void merge(InputReader<T> inA, InputReader<T> inB, Comparator<T> comparator, PrintStream out) {
        while (inA.hasNext() && inB.hasNext()) {
            out.println(getVal(inA, inB, comparator));
        }

        if (!inA.hasNext()) {
            while (inB.hasNext()) {
                out.println(inB.getNext());
            }
        }
        if (!inB.hasNext()) {
            while (inA.hasNext()) {
                out.println(inA.getNext());
            }
        }
    }

    private static <T> T getVal(InputReader<T> inA, InputReader<T> inB, Comparator<T> comparator) {
        if (comparator.compare(inA.peek(), inB.peek()) < 0) {
            return inA.getNext();
        } else {
            return inB.getNext();
        }
    }
}
