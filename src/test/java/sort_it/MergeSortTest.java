package sort_it;

import org.apache.commons.cli.ParseException;
import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static sort_it.CLIParser.parse;
import static sort_it.MergeSort.merge;

public class MergeSortTest {

    final static String[] INTEGER_INPUT_ASCENDING_ORDER_ARGS = {"-i", "smth", "smth"};
    final static String[] INTEGER_INPUT_DESCENDING_ORDER_ARGS = {"-i", "-d", "smth", "smth"};
    final static String[] STRING_INPUT_ASCENDING_ORDER_ARGS = {"-s", "smth", "smth"};
    final static String[] STRING_INPUT_DESCENDING_ORDER_ARGS = {"-s", "-d", "smth", "smth"};

    static Params INTEGER_INPUT_ASCENDING_ORDER_PARAMS = null;
    static Params INTEGER_INPUT_DESCENDING_ORDER_PARAMS = null;
    static Params STRING_INPUT_ASCENDING_ORDER_PARAMS = null;
    static Params STRING_INPUT_DESCENDING_ORDER_PARAMS = null;


    static {
        try {
            INTEGER_INPUT_ASCENDING_ORDER_PARAMS = parse(INTEGER_INPUT_ASCENDING_ORDER_ARGS);
            INTEGER_INPUT_DESCENDING_ORDER_PARAMS = parse(INTEGER_INPUT_DESCENDING_ORDER_ARGS);
            STRING_INPUT_ASCENDING_ORDER_PARAMS = parse(STRING_INPUT_ASCENDING_ORDER_ARGS);
            STRING_INPUT_DESCENDING_ORDER_PARAMS = parse(STRING_INPUT_DESCENDING_ORDER_ARGS);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void merge_IntegerInputAscendingOrderedGiven_ShouldBeWorkRight() {
        String a = "1\n2\n3";
        String b = "4\n5";

        String expected = "1\n2\n3\n4\n5\n";

        InputStream streamA = new ByteArrayInputStream(a.getBytes());
        InputStream streamB = new ByteArrayInputStream(b.getBytes());

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream outputStream = new PrintStream(baos);


        merge(
                INTEGER_INPUT_ASCENDING_ORDER_PARAMS.inputReaderFactory().apply(streamA),
                INTEGER_INPUT_ASCENDING_ORDER_PARAMS.inputReaderFactory().apply(streamB),
                outputStream,
                INTEGER_INPUT_ASCENDING_ORDER_PARAMS.comparator()
        );
        String actual = baos.toString();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void merge_StringInputDescendingOrderedGiven_ShouldBeWorkRight() {
        String a = "z\nh\na";
        String b = "f\ne\nd\nc\nb";

        String expected = "z\nh\nf\ne\nd\nc\nb\na\n";

        InputStream streamA = new ByteArrayInputStream(a.getBytes());
        InputStream streamB = new ByteArrayInputStream(b.getBytes());

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream outputStream = new PrintStream(baos);


        merge(
                STRING_INPUT_DESCENDING_ORDER_PARAMS.inputReaderFactory().apply(streamA),
                STRING_INPUT_DESCENDING_ORDER_PARAMS.inputReaderFactory().apply(streamB),
                outputStream,
                STRING_INPUT_DESCENDING_ORDER_PARAMS.comparator()
        );
        String actual = baos.toString();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void merge_IntegerInputWrongOrderedGiven_ShouldBeWorkRight() {
        String a = "5\n6\n3";
        String b = "4\n5\n2";

        String expected = "4\n5\n5\n6\n";

        InputStream streamA = new ByteArrayInputStream(a.getBytes());
        InputStream streamB = new ByteArrayInputStream(b.getBytes());

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream outputStream = new PrintStream(baos);


        merge(
                INTEGER_INPUT_ASCENDING_ORDER_PARAMS.inputReaderFactory().apply(streamA),
                INTEGER_INPUT_ASCENDING_ORDER_PARAMS.inputReaderFactory().apply(streamB),
                outputStream,
                INTEGER_INPUT_ASCENDING_ORDER_PARAMS.comparator()
        );
        String actual = baos.toString();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void merge_StringInputWrongOrderedGiven_ShouldBeWorkRight() {
        String a = "a\nd\ny\na\ne\nt";
        String b = "c\nx\nb";

        String expected = "a\nc\nd\nx\ny\n";

        InputStream streamA = new ByteArrayInputStream(a.getBytes());
        InputStream streamB = new ByteArrayInputStream(b.getBytes());

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream outputStream = new PrintStream(baos);


        merge(
                STRING_INPUT_ASCENDING_ORDER_PARAMS.inputReaderFactory().apply(streamA),
                STRING_INPUT_ASCENDING_ORDER_PARAMS.inputReaderFactory().apply(streamB),
                outputStream,
                STRING_INPUT_ASCENDING_ORDER_PARAMS.comparator()
        );
        String actual = baos.toString();
        Assert.assertEquals(expected, actual);
    }
}
