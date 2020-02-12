package sort_it;

import org.apache.commons.cli.*;

import java.util.Comparator;
import java.util.List;

public class CLIParser {

    final static String OPTION_INPUT_TYPE_STRING = "s";
    final static String OPTION_INPUT_TYPE_INTEGER = "i";
    final static String OPTION_SORT_ORDER_ASCENDING = "a";
    final static String OPTION_SORT_ORDER_DESCENDING = "d";

    public static Params parse(String[] args) throws ParseException {
        CommandLineParser parser = new DefaultParser();
        Options options = prepareOptions();
        CommandLine cmd = parser.parse(options, args);

        Params result;

        if(cmd.hasOption(OPTION_INPUT_TYPE_INTEGER)) {
            result = Params.integerInputParams(getComparator(cmd));
        } else if(cmd.hasOption(OPTION_INPUT_TYPE_STRING)) {
            result = Params.lineInputParams(getComparator(cmd));
        } else {
            throw new ParseException("Should be one of this options: -i or -s");
        }

        List<String> files = cmd.getArgList();
        result.setOutputFile(files.get(0));
        result.setInputFiles(files.subList(1, files.size()));

        return result;
    }

    private static <T extends Comparable<T>> Comparator<T> getComparator(CommandLine cmd) {
        if(cmd.hasOption(OPTION_SORT_ORDER_DESCENDING)) {
            return Comparator.reverseOrder();

        } else {
            return Comparator.naturalOrder();
        }

    }

    private static Options prepareOptions() {
        Options options = new Options();

        Option stringSortOption = Option.builder(OPTION_INPUT_TYPE_STRING)
                .desc("string sort mode")
                .build();

        Option intSortOption = Option.builder(OPTION_INPUT_TYPE_INTEGER)
                .desc("int sort mode")
                .build();

        Option ascendingSortOption = Option.builder(OPTION_SORT_ORDER_ASCENDING)
                .desc("ascending sort mode")
                .build();

        Option descendingSortOption = Option.builder(OPTION_SORT_ORDER_DESCENDING)
                .desc("descending sort mode")
                .build();

        options.addOption(stringSortOption)
                .addOption(intSortOption)
                .addOption(ascendingSortOption)
                .addOption(descendingSortOption);

        return options;

    }
}
