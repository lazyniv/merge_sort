package sort_it;

import org.apache.commons.cli.ParseException;

import java.io.PrintStream;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) throws ParseException {
      Params p = CLIParser.parse(args);
      merge(p.inputReaderFactory().apply(System.in),
              p.inputReaderFactory().apply(System.in),
              p.comparator(),
              System.out
      );

    }

    static <T> void merge(InputReader<T> inA, InputReader<T> inB, Comparator<T> comparator, PrintStream out) {
        T a = inA.getNext();
        T b = inB.getNext();

        while (inA.hasNext() && inB.hasNext()) {
            if (comparator.compare(a, b) <= 0 ) {
                out.println(a);
                a = inA.getNext();
            } else {
                out.println(b);
                b = inB.getNext();
            }
        }

        if (!inA.hasNext()) { while (inB.hasNext()) out.println(inB.getNext()); }
        if (!inB.hasNext()) { while (inA.hasNext()) out.println(inA.getNext()); }
    }
}
