import java.util.Comparator;

import components.map.Map;
import components.map.Map1L;
import components.set.Set;
import components.set.Set1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.sortingmachine.SortingMachine;
import components.sortingmachine.SortingMachine1L;

/**
 * Reads the words from an input file and outputs the top 100 occurrence words
 * and bigger the font size if the count is bigger.
 *
 * @author Sungwoon Park and Tony Liou
 * 
 * *************************************************************************
 * ALSO CHANGE ALL COMMENTS AND CONTRACTS
 * 
 *
 */
public final class TagCloudGenerator {

    /**
     * Global int difference variable
     */
    private static final int DIFFERENCE = 37;
    /**
     * Global int minimum variable
     */
    private static final int MINIMUM = 11;

    /**
     * Compare {@code String}s in alphabetical order.
     */
    private static class StringAlpha
            implements Comparator<Map.Pair<String, Integer>> {
        @Override
        public int compare(Map.Pair<String, Integer> o1,
                Map.Pair<String, Integer> o2) {
            if (o1.key().compareTo(o2.key()) == 0) {
                return o1.value().compareTo(o2.value());
            } else {
                return o1.key().compareToIgnoreCase(o2.key());
            }
        }
    }

    /**
     * Compare {@code String}s in decreasing order.
     */
    private static class IntegerLT
            implements Comparator<Map.Pair<String, Integer>> {
        @Override
        public int compare(Map.Pair<String, Integer> o1,
                Map.Pair<String, Integer> o2) {
            if (o1.value().compareTo(o2.value()) == 0) {
                return o2.key().compareTo(o1.key());
            }
            //use the value (the number) from the map to compare
            return o2.value().compareTo(o1.value());
        }
    }

    /**
     * Generates the set of characters in the given {@code String} into the *
     * given {@code Set}.
     *
     * @param str
     *            the given {@code String}
     * @param charSet
     *            the {@code Set} to be replaced
     * @replaces charSet
     * @ensures charSet = entries(str)
     */
    private static void generateElements(String str, Set<Character> charSet) {
        // iterate through every character in the string
        for (int i = 0; i < str.length(); i++) {
            if (!charSet.contains(str.charAt(i))) {
                char tempChar = str.charAt(i);
                charSet.add(tempChar);
            }
        }
    }

    /**
     * Returns the first "word" (maximal length string of characters not in
     * {@code separators}) or "separator string" (maximal length string of
     * characters in {@code separators}) in the given {@code text} starting at
     * the given {@code position}.
     *
     * @param text
     *            the {@code String} from which to get the word or separator
     *            string
     * @param position
     *            the starting index
     * @param separators
     *            the {@code Set} of separator characters
     * @return the first word or separator string found in {@code text} starting
     *         at index {@code position}
     * @requires 0 <= position < |text|
     * @ensures <pre>
     * nextWordOrSeparator =
     *   text[position, position + |nextWordOrSeparator|)  and
     * if entries(text[position, position + 1)) intersection separators = {}
     * then
     *   entries(nextWordOrSeparator) intersection separators = {}  and
     *   (position + |nextWordOrSeparator| = |text|  or
     *    entries(text[position, position + |nextWordOrSeparator| + 1))
     *      intersection separators /= {})
     * else
     *   entries(nextWordOrSeparator) is subset of separators  and
     *   (position + |nextWordOrSeparator| = |text|  or
     *    entries(text[position, position + |nextWordOrSeparator| + 1))
     *      is not subset of separators)
     * </pre>
     */
    private static String nextWordOrSeparator(String text, int position,
            Set<Character> separators) {
        // initialize the return String
        String returnString = "";

        // kth index of the text String
        int k = position;
        /*
         * Iterate while: First, k is smaller than the length of the text String
         * Second, character at the index of position and k of the text String
         * are both the objects in the Set separators or both are not the
         * objects in the separators Set
         */
        while (k < text.length()
                && separators.contains(text.charAt(position)) == separators
                        .contains(text.charAt(k))) {
            returnString += text.charAt(k);
            // increment the k(index)
            k++;
        }
        return returnString;
    }

    /**
     * Stores all the word in the Queue separatedWord.
     *
     * @param inputFile
     *            name of the input file
     * @param separatedWord
     *            the queue
     * @requires File named inputFile exists but is not open. Queue
     *           separatedWord to be empty
     * @ensures every word in the file to be in the Queue separatedWord
     */
    private static void generateMap(String inputFile,
            Map<String, Integer> map) {
        SimpleReader input = new SimpleReader1L(inputFile);

        // sepString is all the possible separators
        String sepString = " .,!@#$%^&*()-_+=?[]{}/<>\'\";:~`\\|\t";

        Set<Character> separators = new Set1L<>();

        // stores all the separators at the Set separators
        generateElements(sepString, separators);

        while (!input.atEOS()) {
            // wordsWithSep: one line from the input file
            String wordsWithSep = input.nextLine().toLowerCase();
            int lengthOfLine = wordsWithSep.length();
            String value = "";
            int pos = 0;
            while (pos < lengthOfLine) {
                value = nextWordOrSeparator(wordsWithSep, pos, separators);
                // stores the value to the queue if it is not a separator
                if (!separators.contains(value.charAt(0))) {

                    // if the word was already stored to the Map
                    if (map.hasKey(value)) {
                        int numUsed = map.value(value);
                        // add one to the value
                        int updated = numUsed + 1;
                        map.replaceValue(value, updated);
                    } else {
                        // if the word was not stored to the Map
                        map.add(value, 1);
                    }
                }
                pos += value.length();

            }
        }
        input.close();
    }

    /**
     * Sorts the Map into a decreasing order of count with user defined number
     * of count in alphabetical order.
     *
     * @param count
     *            the SortingMachine
     * @param alphabet
     *            the SortingMachine
     * @param num
     *            the integer
     * @param wordAndCount
     *            the map
     * @requires Map is not empty
     * @ensures Modifies the Map into a decreasing order for the value of count
     * @return Sorted map in a decreasing order
     */
    private static int[] sortMap(
            SortingMachine<Map.Pair<String, Integer>> count,
            SortingMachine<Map.Pair<String, Integer>> alphabet, int num,
            Map<String, Integer> wordAndCount) {

        while (wordAndCount.size() != 0) {
            count.add(wordAndCount.removeAny());
        }
        count.changeToExtractionMode();

        int length = count.size();
        int maxCount = 0;
        int minCount = 0;
        for (int i = 0; i < length && i < num; i++) {
            Map.Pair<String, Integer> pair = count.removeFirst();
            if (i == 0) {
                maxCount = pair.value();
            }
            if (i == num - 1) {
                minCount = pair.value();
            }
            alphabet.add(pair);
        }

        // stores the max count and min count into the array
        int[] maxMinArray = new int[2];
        maxMinArray[0] = maxCount;
        maxMinArray[1] = minCount;

//        alphabet.changeToExtractionMode();
//        while (alphabet.size() != 0) {
//            Map.Pair<String, Integer> pair = alphabet.removeFirst();
//            wordAndCount.add(pair.key(), pair.value());
//        }

        return maxMinArray;
    }

    /**
     * Generates the html file.
     *
     * @param output
     *            the output stream to write the html codes
     * @param words
     *            the queue
     * @param map
     *            the map
     * @param maxMin
     *            the array
     * @param num
     *            the integer
     * @param inputFile
     *            user input file
     * @requires Queue words, Map map, and maxMin is not empty
     * @ensures generates the correct html formated file to the user inputed
     *          output file
     */
    private static void printPage(SimpleWriter output,
            SortingMachine<Map.Pair<String, Integer>> alphabet, int[] maxMin,
            int num, String inputFile) {

        output.println("<html>");
        output.println("<head>");
        output.println("<title>Project9</title>");
        output.println(
                "<link href=\"http://web.cse.ohio-state.edu/software/2231"
                        + "/web-sw2/assignments/projects/tag-cloud-generator/data"
                        + "/tagcloud.css\" rel=\"stylesheet\" type=\"text/css\">\r\n"
                        + "<link href=\"tagcloud.css\" rel=\"stylesheet\" type="
                        + "\"text/css\">");
        output.println("</head>");
        output.println("<body>");
        // includes the name of the input file at the header
        output.println("<h2> Top" + num + "words in " + inputFile + "</h2>");
        output.println("<hr>");
        output.println("<div class=\"cdiv\">");
        output.println("<p class =" + '"' + "cbox" + '"' + ">");

        alphabet.changeToExtractionMode();
        // iterate user input num times
        while (alphabet.size() != 0) {
            Map.Pair<String, Integer> pair = alphabet.removeFirst();
            int maxCount = maxMin[0];
            int minCount = maxMin[1];

            int currentCount = pair.value();
            int fontSize = 0;
            if (maxCount == minCount) {
                fontSize = 20;
            } else {
                fontSize = ((currentCount - minCount) * DIFFERENCE)
                        / (maxCount - minCount) + MINIMUM;
            }
            output.println("<span style=\"cursor:default\" class=\"f" + fontSize
                    + "\" title=\"count: " + pair.value() + "\">" + pair.key()
                    + "</span>");
        }
        output.println("</p>");
        output.println("</div>");
        output.println("</body>");
        output.println("</html>");
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();

        // prompts the user for the input file
        out.print("Enter the name of an input file: ");
        String inputFile = in.nextLine();

        if (inputFile.isEmpty() || inputFile.length() <= 4
                || (!inputFile
                        .substring(inputFile.length() - 4, inputFile.length())
                        .equals(".txt"))) {
            out.print("Invaild input file name");
        } else {
            // prompts the user for the output folder name
            out.print("Input the name of an output file: ");
            String outputFile = in.nextLine();

            // separatedWord: Queue used to store words from the input file
            //  Queue<String> separatedWord = new Queue1L<>();
            /*
             * wordAndCount: Map used to store words and the occurrence from the
             * input file
             */
            Map<String, Integer> wordAndCount = new Map1L<>();

            // stores all the words in Queue
            //generateQueue(inputFile, separatedWord);

            // makes the words in the Queue in lexicographical order
            // lexicographicalOrder(separatedWord);

            // stores the words and its count to the Map
            generateMap(inputFile, wordAndCount);

            Comparator<Map.Pair<String, Integer>> ci1 = new IntegerLT();
            SortingMachine<Map.Pair<String, Integer>> count = new SortingMachine1L<Map.Pair<String, Integer>>(
                    ci1);
            Comparator<Map.Pair<String, Integer>> ci2 = new StringAlpha();
            SortingMachine<Map.Pair<String, Integer>> alphabet = new SortingMachine1L<Map.Pair<String, Integer>>(
                    ci2);

            // number of the total words
            int numWords = wordAndCount.size();
            // prompt the user for the number of words
            out.println("Enter the number of words: ");
            int num = in.nextInteger();

            while (num > numWords) {
                out.println("Re-enter a smaller number of words: ");
                num = in.nextInteger();
            }
            /*
             * sorts the Map into a decreasing order of count and top 100 in
             * alphabetical order
             */
            int[] maxMin = sortMap(count, alphabet, num, wordAndCount);

            SimpleWriter output = new SimpleWriter1L(outputFile);
            // writes an html file with the correct format
            printPage(output, alphabet, maxMin, num, inputFile);
        }

        in.close();
        out.close();
    }
}
