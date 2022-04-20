import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

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
            String wordsWithSep = input.nextLine().toLowerCase();
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
        input.close();
    }

    /**
     * Makes the Queue words in the lexicographic order.
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
     * Stores the word and the corresponding occurrence to the Map.
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
            } else {
                // if the word was not stored to the Map
                map.add(oneWord, 1);
            }
            // place back the taken out word
            words.enqueue(oneWord);
        }
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

        alphabet.changeToExtractionMode();
        while (alphabet.size() != 0) {
            Map.Pair<String, Integer> pair = alphabet.removeFirst();
            wordAndCount.add(pair.key(), pair.value());
        }

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
    private static void printPage(SimpleWriter output, Queue<String> words,
            Map<String, Integer> map, int[] maxMin, int num, String inputFile) {

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

        // iterate user input num times
        while (words.length() != 0) {
            String oneWord = words.dequeue();
            boolean check = map.hasKey(oneWord);

            if (check) {
                int maxCount = maxMin[0];
                int minCount = maxMin[1];
                Map.Pair<String, Integer> pair = map.remove(oneWord);
                int currentCount = pair.value();
                int fontSize = 0;
                if (maxCount == minCount) {
                    fontSize = 20;
                } else {
                    fontSize = ((currentCount - minCount) * DIFFERENCE)
                            / (maxCount - minCount) + MINIMUM;
                }
                output.println("<span style=\"cursor:default\" class=\"f"
                        + fontSize + "\" title=\"count: " + pair.value() + "\">"
                        + pair.key() + "</span>");
                // restore the used word from the Queue
                words.enqueue(oneWord);
            }
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

        PrintWriter fileOutput;
        BufferedReader fileInput;
        BufferedReader in;

        // prompts the user for the input file
        Scanner myObj = new Scanner(System.in); // Create a Scanner object
        System.out.print("Enter the name of an input file: ");
        String input = myObj.nextLine();

        // prompts the user for the output folder name
        System.out.print("Enter the name of an output file: ");
        String outputFile = myObj.nextLine();

        try {
            fileInput = new BufferedReader(new FileReader(input));
            fileOutput = new PrintWriter(
                    new BufferedWriter(new FileWriter(outputFile)));
        } catch (IOException e) {
            System.err.println("Error opening file");
            return;
        }

        try {
            String s = fileInput.readLine();
            while (s != null) {
                fileOutput.println(s);
                s = fileInput.readLine();
            }
        } catch (IOException e) {
            System.err.println("Error reading from file or writing to file");
        }

        Map<String, Integer> wordAndCount = new HashMap<String, Integer>();

        // prompt the user for the number of words
        System.out.println("Enter the number of words: ");
        int num = myObj.nextInt();

        try {
            if (num > wordAndCount.size()) {
                System.out.println("Re-enter a smaller number of words: ");
                num = myObj.nextInt();
            }
        } catch (IOException e) {
            System.out.println("Input a smaller number of words");
        }

        // stores all the words in Queue
        generateQueue(inputFile, separatedWord);

        // makes the words in the Queue in lexicographical order
        lexicographicalOrder(separatedWord);

        // stores the words and its count to the Map
        generateMap(separatedWord, wordAndCount);

        Comparator<Map.Entry<String, Integer>> ci1 = new IntegerLT();
        SortingMachine<Map.Entry<String, Integer>> count = new SortingMachine1L<Map.Entry<String, Integer>>(
                ci1);
        Comparator<<Map.Entry<String, Integer>> ci2 = new StringAlpha();
        SortingMachine<Map.Entry<String, Integer>> alphabet = new SortingMachine1L<Map.Entry<String, Integer>>(
                ci2);

        int[] maxMin = sortMap(count, alphabet, num, wordAndCount);

        SimpleWriter output = new SimpleWriter1L(outputFile);
        // writes an html file with the correct format
        printPage(output, separatedWord, wordAndCount, maxMin, num, inputFile);

        try {
            fileInput.close();
            fileOutput.close();
            in.close();
            myObj.close();
        } catch (IOException e) {
            System.err.println("Error closing file");
        }
    }
}
