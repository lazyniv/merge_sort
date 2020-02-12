package sort_it;

import org.apache.commons.cli.*;

import java.util.Comparator;
import java.util.List;

public class CLIParser {

    final static String INPUT_TYPE_STRING_OPTION = "s";
    final static String INPUT_TYPE_INTEGER_OPTION = "i";
    final static String SORT_ORDER_ASCENDING_OPTION = "a";
    final static String SORT_ORDER_DESCENDING_OPTION = "d";
    final static String HELP_OPTION = "h";

    public static Params parse(String[] args) throws ParseException {
        CommandLineParser parser = new DefaultParser();
        Options options = prepareOptions();
        CommandLine cmd = parser.parse(options, args);

        Params result;

        if(cmd.hasOption(INPUT_TYPE_INTEGER_OPTION)) {
            result = Params.integerInputParams(getComparator(cmd));
        } else if(cmd.hasOption(INPUT_TYPE_STRING_OPTION)) {
            result = Params.lineInputParams(getComparator(cmd));
        } else {
            throw new ParseException("Missing option: -i or -s");
        }


        List<String> files = cmd.getArgList();
        if(files.size() < 2) {
            throw new ParseException("Missing arguments: [OUTPUT_FILE] and [INPUT_FILE]...");
        }
        result.setOutputFile(files.get(0));
        result.setInputFiles(files.subList(1, files.size()));

        return result;
    }

    public static void printHelp() {
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("java -jar build/libs/merge_sort-1.0.jar [OPTION]... [OUTPUT_FILE]  [INPUT_FILE]...", prepareOptions());
    }

    private static <T extends Comparable<T>> Comparator<T> getComparator(CommandLine cmd) {
        if(cmd.hasOption(SORT_ORDER_DESCENDING_OPTION)) {
            return Comparator.reverseOrder();
        } else {
            return Comparator.naturalOrder();
        }

    }

    private static Options prepareOptions() {
        Options options = new Options();

        Option stringSortOption = Option.builder(INPUT_TYPE_STRING_OPTION)
                .desc("string sort mode")
                .build();

        Option intSortOption = Option.builder(INPUT_TYPE_INTEGER_OPTION)
                .desc("int sort mode")
                .build();

        Option ascendingSortOption = Option.builder(SORT_ORDER_ASCENDING_OPTION)
                .desc("ascending sort mode")
                .build();

        Option descendingSortOption = Option.builder(SORT_ORDER_DESCENDING_OPTION)
                .desc("descending sort mode")
                .build();

        Option helpOption = Option.builder(HELP_OPTION)
                .desc("show this message")
                .build();

        options.addOption(stringSortOption)
                .addOption(intSortOption)
                .addOption(ascendingSortOption)
                .addOption(descendingSortOption);

        return options;

    }
}
