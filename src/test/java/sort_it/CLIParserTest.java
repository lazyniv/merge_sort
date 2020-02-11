package sort_it;

import org.apache.commons.cli.ParseException;
import org.junit.Assert;
import org.junit.Test;

import java.io.*;
import java.util.List;

import static sort_it.CLIParser.parse;
import static sort_it.Main.merge;

public class CLIParserTest {

    @Test
    public void parse_SimpleArgsGiven_ShouldBeWorkRight() throws ParseException {
        String[] args = {"-i", "-a", "out.txt", "in1.txt", "in2.txt"};

        String a = "1\n2\n3";
        String b = "4\n5";

        String expected = "1\n2\n3\n4\n5\n";

        InputStream streamA = new ByteArrayInputStream(a.getBytes());
        InputStream streamB = new ByteArrayInputStream(b.getBytes());

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream outputStream = new PrintStream(baos);

        Params p = parse(args);
        merge(
                p.inputReaderFactory().apply(streamA),
                p.inputReaderFactory().apply(streamB),
                p.comparator(),
                outputStream
        );

        String actual = baos.toString();
        Assert.assertEquals(actual, expected);
    }

    /*@Test
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
    }*/


}
