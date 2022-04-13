import java.util.Comparator;

import components.map.Map;
import components.map.Map1L;
import components.queue.Queue;
import components.set.Set;
import components.set.Set1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.sortingmachine.SortingMachine;
import components.sortingmachine.SortingMachine1L;

/**
 * Put a short phrase describing the program here.
 *
 * @author Put your name here
 *
 */
public final class TagCloudGenerator {

    /**
     * Compare {@code String}s in alphabetical order.
     */
    private static class StringLT
            implements Comparator<Map.Pair<String, Integer>> {
        @Override
        public int compare(Map.Pair<String, Integer> o1,
                Map.Pair<String, Integer> o2) {
            //use the value (the number) from the map to compare
            return o1.value().compareTo(o2.value());
        }
    }

    /**
     * Compare {@code String}s in alphabetical order.
     */
    private static class IntegerLT
            implements Comparator<Map.Pair<String, Integer>> {
        @Override
        public int compare(Map.Pair<String, Integer> o1,
                Map.Pair<String, Integer> o2) {
            //use the value (the number) from the map to compare
            return o1.key().compareTo(o2.key());
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
     * Stores all the word in the Queue separatedWord
     *
     * @param inputFile
     *            name of the input file
     * @param separatedWord
     *            the queue
     * @requires File named inputFile exists but is not open. Queue
     *           separatedWord to be empty
     * @ensures every word in the file to be in the Queue separatedWord
     */
    private static void generateQueue(String inputFile,
            Queue<String> separatedWord) {
        SimpleReader input = new SimpleReader1L(inputFile);

        // sepString is all the possible separators
        String sepString = " .,!@#$%^&*()-_+=?[]{}/<>\'\";:~`\\|\t";

        Set<Character> separators = new Set1L<>();

        // stores all the separators at the Set separators
        generateElements(sepString, separators);

        while (!input.atEOS()) {
            // wordsWithSep: one line from the input file
            String wordsWithSep = input.nextLine();
            int lengthOfLine = wordsWithSep.length();
            String value = "";
            int pos = 0;

            while (pos < lengthOfLine) {
                value = nextWordOrSeparator(wordsWithSep, pos, separators);

                // stores the value to the queue if it is not a separator
                if (!separators.contains(value.charAt(0))) {
                    separatedWord.enqueue(value);
                }

                pos += value.length();
            }
        }
    }

    /**
     * Makes the Queue words in the lexicographic order
     *
     * @param words
     *            the queue
     * @requires Queue words to be empty
     * @ensures the String elements in the Queue words is in lexicographic order
     */
    private static void lexicographicalOrder(Queue<String> words) {
        /*
         * Sort lines into non-decreasing lexicographic order
         */
        Comparator<String> cs = new StringLT();
        words.sort(cs);

    }

    /**
     * Stores the word and the corresponding occurrence to the Map
     *
     * @param words
     *            the queue
     * @param map
     *            the word -> the occurrence
     * @requires Map words to be empty
     * @ensures [map contains word -> number of occurrence]
     */
    private static void generateMap(Queue<String> words,
            Map<String, Integer> map) {
        // numberOfWords: # of words in the given input file
        int numberOfWords = words.length();
        // wordUsed: used to figure out if the word is already in the Map or not
        boolean wordUsed = false;

        for (int i = 0; i < numberOfWords; i++) {
            String oneWord = words.dequeue();
            wordUsed = map.hasKey(oneWord);

            // if the word was already stored to the Map
            if (wordUsed) {
                int numUsed = map.value(oneWord);
                // add one to the value
                int updated = numUsed + 1;
                map.replaceValue(oneWord, updated);
            }
            // if the word was not stored to the Map
            else {
                map.add(oneWord, 1);
            }
            // place back the taken out word
            words.enqueue(oneWord);
        }
    }

    /**
     * Generates the html file
     *
     * @param output
     *            the output stream to write the html codes
     * @param words
     *            the queue
     * @param map
     *            the map
     * @param inputFile
     *            user input file
     * @requires Queue words has String elements in it
     * @ensures generates the correct html formated file to the user inputed
     *          output file
     */
    private static void printPage(SimpleWriter output, Queue<String> words,
            Map<String, Integer> map, String inputFile,
            SortingMachine<Map.Pair<String, Integer>> count,
            SortingMachine<Map.Pair<String, Integer>> alpha) {

        output.println("<html>");
        output.println("<head>");
        output.println("<title>Project9</title>");
        output.println("<link href=" + '"' + "doc/tagcloud.css" + '"' + " rel="
                + '"' + "stylesheet" + '"' + " type =" + '"' + "text/css" + '"'
                + ">");

        output.println("</head>");
        output.println("<body>");
        // includes the name of the input file at the header
        output.println("<h2> Top 100 words in " + inputFile + "</h2>");
        output.println("<p class =" + '"' + "cbox" + '"' + ">");

        //print out opur decided font size order and format html
        while (alpha.size() > 0) {
            Map.Pair<String, Integer> pair = alpha.removeFirst();
            int ttemp = pair.value();
            int font = ttemp / 10;
            output.println("<span style=" + '"' + "cursor:default" + '"'
                    + " class=" + '"' + 'f' + font + '"' + " title=" + '"'
                    + "count: " + pair.value() + '"' + ">" + pair.key()
                    + "</span>");
        }

        output.println("</p>");
        output.println("</div>");
        output.println("</body>");
        output.println("</html>");

    }

    private static void sortingMachineProcessor(
            SortingMachine<Map.Pair<String, Integer>> count,
            SortingMachine<Map.Pair<String, Integer>> alpha,
            Map<String, Integer> wordAndCount, SimpleWriter output) {

        //add the extration mode from map
        int length = count.size();
        for (int i = 0; i < length; i++) {
            count.add(wordAndCount.removeAny());
        }

        //now we need to sort the sortitng machine
        count.changeToExtractionMode();

        //add number to alphga data stucture
        alpha.add(count.removeFirst());

        //add the elements to alpha in order
        int length2 = count.size();
        for (int i = 0; i < length2; i++) {
            alpha.add(count.removeFirst());
        }

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

        if (inputFile.substring(inputFile.length() - 5, inputFile.length() - 1)
                .equals(".txt")) {
            out.print("Invaild input file name");
        } else {
            // prompts the user for the output folder name
            out.print("Input the name of an output file: ");
            String outputFile = in.nextLine();

            /*
             * wordAndCount: Map used to store words and the occurrence from the
             * input file
             */
            Map<String, Integer> wordAndCount = new Map1L<>();

            // stores all the words in Queue
            generateQueue(inputFile, separatedWord);

            // makes the words in the Queue in lexicographical order
            lexicographicalOrder(separatedWord);

            // stores top 100 words in the new Map
            generateMap(separatedWord, wordAndCount);

            //Queue<String> separatedWord = new Queue1L<>();

            Comparator<Map.Pair<String, Integer>> ci = new IntegerLT();
            SortingMachine<Map.Pair<String, Integer>> count = new SortingMachine1L<Map.Pair<String, Integer>>(
                    ci);

            Comparator<Map.Pair<String, Integer>> cii = new StringLT();
            SortingMachine<Map.Pair<String, Integer>> alpha = new SortingMachine1L<Map.Pair<String, Integer>>(
                    cii);

            SimpleWriter output = new SimpleWriter1L(outputFile);

            // writes an html file with the correct format
            printPage(output, separatedWord, wordAndCount, inputFile);
        }

        in.close();
        out.close();
    }
}
