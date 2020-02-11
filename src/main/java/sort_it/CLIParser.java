package sort_it;

import org.apache.commons.cli.*;

import java.util.Comparator;

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
        if(cmd.hasOption(OPTION_INPUT_TYPE_STRING)) {
            result = Params.integerInputParams(getComparator(cmd));
        } else if(cmd.hasOption(OPTION_INPUT_TYPE_INTEGER)) {
            result = Params.integerLinesParams(getComparator(cmd));
        } else {
            throw new ParseException("Should be one of this options: -i or -s");
        }

    /*
        if(cmd.hasOption(OPTION_SORT_ORDER_DESCENDING)) {

            argsList.add(OPTION_SORT_ORDER_DESCENDING);
        } else {
            argsList.add(OPTION_SORT_ORDER_ASCENDING);
        }

        argsList.addAll(cmd.getArgList());

        return argsList;
    */
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
