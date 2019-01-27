import javax.print.DocFlavor;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;


/**
 * Parsing all command line args and managing correct file path.
 * */
public class ParseInput {

    private int tupleLength;
    private String firstFile;
    private String secondFile;
    private String synFile;

    public String getFirstFile() {
        return this.firstFile;
    }
    public String getSecondFile() {
        return this.secondFile;
    }
    public String getSynFile() {
        return this.synFile;
    }
    public int getTupleLength() {
        return this.tupleLength;
    }

    public static class OptionParser<T> implements ParameterParser {

        private final String option;

        public OptionParser(String option, Function<String, ? extends T> parser) {
            if (parser == null) {
                throw new IllegalArgumentException();
            }
            this.option = "-" + option;
            this.parser = parser;
        }

        public OptionParser(String option, Function<String, ? extends T> parser, T defaultValue) {
            this(option, parser);
            this.value = defaultValue;
        }

        private final Function<String, ? extends T> parser;

        private T value;

        @Override
        public int parse(String[] args, int index) {
            if (args.length < index + 2 || !option.equals(args[index])) {
                return index;
            } else {
                value = parser.apply(args[index + 1]);
                return index + 2;
            }
        }

        public T getValue() {
            return value;
        }

    }

    public static interface ParameterParser {


        /**
         * Tries parsing the parameter and returns the new index after the
         * operation.
         *
         * @param args the parameter list
         * @param index the index of the first String to use.
         * @return the index of the next String after parsing the parameter or the index,
         * if the parameter wasn't parsable with this ParameterParser.
         */
        public int parse(String[] args, int index);
    }

    /**
     * @param args - input args from command line
     */
    public ParseInput(String[] args) {

        String currentPath = System.getProperty("user.dir");
        OptionParser<String> firstFile = new OptionParser<>("f1", Function.identity(), "file1.txt");
        OptionParser<String> secondFile = new OptionParser<>("f2", Function.identity(), "file2.txt");
        OptionParser<String> synFile = new OptionParser<>("s", Function.identity(), "syns.txt");
        OptionParser<Integer> nGram = new OptionParser<>("n", Integer::valueOf, 5);

        List<ParameterParser> parameters = Arrays.asList(
                firstFile,
                secondFile,
                synFile,
                nGram
        );

        Iterator<ParameterParser> iterator = parameters.iterator();

        for (int i = 0; i < args.length; ) {
            if (!iterator.hasNext()) {
                throw new IllegalArgumentException("could not parse option at index " + i + ": " + args[i]);
            }
            i = iterator.next().parse(args, i);
        }

        if (firstFile.getValue() == null || secondFile.getValue() == null || synFile.getValue() == null) {
            throw new IllegalArgumentException("[ERROR] : Invalid Params. [CORRECT FORMAT] Usage: java -jar <name>.jar <input1_filename> <input2_filename> <synonym_filename> <N_value>");
        }

        this.firstFile = Paths.get(currentPath.toString(), firstFile.getValue()).toString();
        this.secondFile = Paths.get(currentPath.toString(), secondFile.getValue()).toString();
        this.synFile = Paths.get(currentPath.toString(), synFile.getValue()).toString();
        this.tupleLength = nGram.getValue();
    }
}
