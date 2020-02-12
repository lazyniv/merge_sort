package sort_it;

import org.apache.commons.cli.ParseException;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
           Params p = CLIParser.parse(args);
           MergeSort.mergeFiles(p);
        } catch (ParseException e) {
            System.err.println(e.getMessage());
            CLIParser.printHelp();
            System.exit(1);
        } catch (IOException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }
}