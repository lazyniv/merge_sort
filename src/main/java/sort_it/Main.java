package sort_it;

import org.apache.commons.cli.ParseException;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws ParseException, IOException {

        Params p = CLIParser.parse(args);

        MergeSort.mergeFiles(p);
    }
}