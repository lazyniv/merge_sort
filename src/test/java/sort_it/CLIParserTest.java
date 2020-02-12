package sort_it;

import org.apache.commons.cli.ParseException;
import org.junit.Assert;
import org.junit.Test;

import java.io.*;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

import static sort_it.CLIParser.parse;

import static sort_it.MergeSort.merge;

public class CLIParserTest {

    @Test
    public void parse_SimpleArgsGiven_ShouldBeWorkRight() throws ParseException, IOException {
        String[] args = {"-i", "out.txt", "in1.txt", "in2.txt", "in3.txt"};

        Params p = parse(args);

        String expectedOutputFile = "out.txt";
        List<String> expectedInputFiles = List.of("in1.txt", "in2.txt", "in3.txt");
       /* Function<InputStream, InputReader<Integer>> expectedInputReaderFactory = in -> new Params.PeekableScanner<Integer>(in) {
             @Override
             public Integer getNextInner() {
                 return scanner.nextInt();
             }

             @Override
             boolean hasNextInner() {
                 return scanner.hasNextInt();
             }
        };*/
        Comparator<Integer> expectedComparator = Integer::compareTo;

        Assert.assertEquals(p.getOutputFile(), expectedOutputFile);
        Assert.assertEquals(p.getInputFiles(), expectedInputFiles);
        //Assert.assertEquals(p.inputReaderFactory(), expectedInputReaderFactory);
       // Assert.assertEquals(p.comparator(), expectedComparator);
        Assert.assertSame(p.comparator(), expectedComparator);

        /*String a = "1\n2\n3";
        String b = "4\n5";

        String expected = "1\n2\n3\n4\n5\n";*

        InputStream streamA = new ByteArrayInputStream(a.getBytes());
        InputStream streamB = new ByteArrayInputStream(b.getBytes());

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream outputStream = new PrintStream(baos);


        merge(p.inputReaderFactory().apply(streamA), p.inputReaderFactory().apply(streamB), outputStream, p.comparator());*/


      /*  String actual = baos.toString();
        Assert.assertEquals(actual, expected);*/
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
