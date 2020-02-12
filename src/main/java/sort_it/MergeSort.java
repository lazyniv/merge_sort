package sort_it;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.Queue;
import java.util.function.Function;

class MergeSort {

    static <T> void mergeFiles(Params p) throws IOException {
        Queue<String> filesQueue = new ArrayDeque<>(p.getInputFiles());

        Function<InputStream, InputReader<T>> inputReaderFactory = p.inputReaderFactory();
        Comparator<T> comparator = p.comparator();

        Path tempDirectory = Files.createTempDirectory("temp");

        while(filesQueue.size() >= 2) {
            String fileA = filesQueue.poll();
            String fileB = filesQueue.poll();

            Path tempFile = Files.createTempFile(tempDirectory, "temp", ".txt");

            try(FileInputStream fisA = new FileInputStream(new File(fileA));
                FileInputStream fisB = new FileInputStream(new File(fileB));
                PrintStream out = new PrintStream(tempFile.toFile())
            ) {
                InputReader<T> inA = inputReaderFactory.apply(fisA);
                InputReader<T> inB = inputReaderFactory.apply(fisB);
                merge(inA, inB, out, comparator);
            }

            filesQueue.offer(tempFile.toString());
        }

        Path resultFile = Paths.get(filesQueue.poll());

        Files.move(resultFile, Paths.get(p.getOutputFile()), StandardCopyOption.REPLACE_EXISTING);

    }

    public static <T> void merge(InputReader<T> inA, InputReader<T> inB, PrintStream out, Comparator<T> comparator) {

        while(inA.hasNext() && inB.hasNext()) {
            if(comparator.compare(inA.peekNext(), inB.peekNext()) < 0) {
                if (isLocalOrdered(inA, comparator)) {
                    out.println(inA.getNext());
                } else {
                    putUntilOrdered(inB, out, comparator);
                    return;
                }
            } else {
                if (isLocalOrdered(inB, comparator)) {
                    out.println(inB.getNext());
                } else {
                    putUntilOrdered(inA, out, comparator);
                    return;
                }
            }
        }

        if (inA.hasNext()) {
            putUntilOrdered(inA, out, comparator);
        }

        if (inB.hasNext()) {
            putUntilOrdered(inB, out, comparator);
        }
    }

    private static <T> void putUntilOrdered(InputReader<T> in, PrintStream out, Comparator<T> comparator) {
        while(in.hasNext() && isLocalOrdered(in, comparator)) {
            out.println(in.getNext());
        }
    }

    private static <T> boolean isLocalOrdered(InputReader<T> in, Comparator<T> comparator) {
        return comparator.compare(in.peekCurrent(), in.peekNext()) <= 0;
    }
}
