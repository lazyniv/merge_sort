package sort_it;

import org.apache.commons.cli.ParseException;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static sort_it.CLIParser.parse;

public class CLIParserTest {

    @Test
    public void parse_SimpleArgsGiven_ShouldBeWorkRight() throws ParseException {
        String[] args = {"-i", "-d", "out.txt", "in1.txt", "in2.txt"};
        List<String> actual = parse(args);
        List<String> expected = List.of("i", "d", "out.txt", "in1.txt", "in2.txt");
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void parse_ArgsWithoutSortModeGiven_ShouldBeDescendingMode() throws ParseException {
        String[] args = {"-i", "out.txt", "in1.txt", "in2.txt"};
        List<String> actual = parse(args);
        List<String> expected = List.of("i", "a", "out.txt", "in1.txt", "in2.txt");
        Assert.assertEquals(actual, expected);
    }

    @Test(expected = ParseException.class)
    public void parse_ArgsWithoutTypeOptionGiven_ShouldThrowsParseException() throws ParseException {
        String[] args = {"-d", "out.txt", "in1.txt", "in2.txt"};
        List<String> actual = parse(args);
    }


}
