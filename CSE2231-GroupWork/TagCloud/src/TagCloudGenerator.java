import java.util.Comparator;

import components.map.Map;
import components.map.Map.Pair;
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
 * This program will take a user input file and outputs a nicely formated HTML
 * table to tell user the words in alphabetical order and its respective amount
 * that exist in that file. This program is case sensitive.
 *
 * @author Tony Liou and Sungwoon Park
 */
public final class TagCloudGenerator {

    /**
     * Default constructor--private to prevent instantiation.
     */
    private TagCloudGenerator() {
        // no code needed here
    }

    /**
     * Final separators.
     */
    private static final String SEPARATORS = " \\t\\n\\r,-.!?[]'_;:/() ";

    /**
     * Compare {@code String}s in lexicographic order.
     */
    private static class StringLT
            implements Comparator<Map.Pair<String, Integer>> {
        @Override
        public int compare(Map.Pair<String, Integer> o1,
                Map.Pair<String, Integer> o2) {
            //use the value (the number) from the map to compare
            return o1.key().compareToIgnoreCase(o2.key());
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
            return o2.value().compareTo(o1.value());
        }
    }

    /**
     * This method add a whole set of character into a set.
     *
     * @param separators
     *            = entries(SEPARATORS)
     * @updates {@code separators}
     * @return <Set<Character> separators>
     */
    private static Set<Character> seperaters(Set<Character> separators) {
        for (int i = 0; i < SEPARATORS.length(); i++) {
            if (!separators.contains(SEPARATORS.charAt(i))) {
                separators.add(SEPARATORS.charAt(i));
            }
        }
        return separators;
    }

    /**
     * This method will process the word from input file and save each word and
     * its respective word count to a map.
     *
     * @param input
     *            input stream from the file
     * @param wrdCounterMap
     *            a map of the word and its word counts
     * @requires |input| > 0
     * @updates wrdCounterMap
     * @ensures wrdCountMap contains list of words in key and numbers of
     *          respective words in value
     */
    private static void wordProcessor(SimpleReader input,
            Map<String, Integer> wrdCounterMap,
            Map<Pair<String, Integer>, Integer> fontMap) {
        assert input != null : "Violation of: input is not null";
        assert input.isOpen() : "Violation of: input.is_open";

        Set<Character> separators = new Set1L<>();
        seperaters(separators);

        while (!input.atEOS()) {
            String wrd = input.nextLine().toLowerCase();
            int posi = 0;
            //gets rid of empty lines in the file
            while (posi < wrd.length()) {
                String theWord = (nextWord(wrd, posi, separators));
                //  posi += theWord.length() + 1;

                if (!separators.contains(theWord.charAt(0))) {
                    if (!wrdCounterMap.hasKey(theWord)) {
                        wrdCounterMap.add(theWord, 1);
//                        wrdCounterMap.replaceValue(theWord,
//                                (wrdCounterMap.value(theWord) + 1));
                    } else {
                        wrdCounterMap.replaceValue(theWord,
                                wrdCounterMap.value(theWord) + 1);

                    }
                }
                posi += theWord.length();
            }
        }

//        //  Map<Pair<String, Integer>, Integer> fontMap = new Map1L<Pair<String, Integer>, Integer>();
//        Comparator<Map.Pair<String, Integer>> sorter = new IntegerLT();
//        SortingMachine<Map.Pair<String, Integer>> sortCount = new SortingMachine1L<Map.Pair<String, Integer>>(
//                sorter);
//
//        //fonts here if we want
//        int fMax = 37;
//        double maxCount = 0;
//        double minCount = Integer.MAX_VALUE;
//        Map<String, Integer> temp = wrdCounterMap.newInstance();
//        while (wrdCounterMap.size() != 0) {
//            Map.Pair<String, Integer> removed = wrdCounterMap.removeAny();
//            double valueRemoved = removed.value().doubleValue();
//            if (valueRemoved > maxCount) {
//                maxCount = valueRemoved;
//            } else if (valueRemoved < minCount) {
//                minCount = valueRemoved;
//            }
//            sortCount.add(removed);
//            temp.add(removed.key(), removed.value());
//        }
//        wrdCounterMap.transferFrom(temp);
//        sortCount.changeToExtractionMode();
//        while (sortCount.size() > 0) {
//            int fontSize = 0;
//            Pair<String, Integer> removedCount = sortCount.removeFirst();
//            int valueCount = removedCount.value();
//            if (valueCount > minCount) {
//                double font = Math.ceil((fMax * (valueCount - minCount)
//                        / (maxCount - minCount)));
//                fontSize = (int) font;
//                fontSize += 9;
//            }
//            fontMap.add(removedCount, fontSize);
//        }
    }

    /**
     * This method add words from the map to queue then sort the queue in
     * alphabetical order.
     *
     * @param wrdCounterMap
     *            a map of the word and its word count
     * @param wordQ
     *            a queue of the word
     * @requires |wordQ| > 0
     * @ensures {@code wrdCOunterMap} is in order based on {@code wordQ}
     */
//    private static void sorting(Map<String, Integer> wrdCounterMap,
//            Queue<String> wordQ) {
//        for (Pair<String, Integer> pair : wrdCounterMap) {
//            wordQ.enqueue(pair.key());
//        }
//        Comparator<String> inOrder = new StringLT();
//        wordQ.sort(inOrder);
//    }

    /**
     * This method will return the first word in the given definition starting
     * from the given starting position. Got this from glossary project.
     *
     * @param wrd
     *            a string of word
     * @param posi
     *            position of starting index
     * @param separators
     *            stores a set of characters to use as separators
     *
     * @requires 0<= posi<= |wrd|
     * @ensures <pre>
     * nextWordOrSeparator = wrd[posi, posi + |separators|)
     * and if entries(wrd[posi, posi + 1)) intersection separators = {}
     * then entries(nextWord) intersection separators = {} and
     * (posi + |nextWord| = |wrd| or
     * entries(wrd[posi, posi + |nextWord| + 1))
     * intersection separators /= {}) else entries(nextWord)
     * is subset of separators and (posi + |nextWord| =
     * |wrd| or entries(wrd[posi, posi +
     * |nextWord| + 1)) is not subset of separators)
     * </pre>
     * @return String of the next word
     */
    private static String nextWord(String wrd, int posi,
            Set<Character> separators) {
        assert wrd != null : "Violation of: text is not null";
        assert separators != null : "Violation of: separators is not null";
        assert 0 <= posi : "Violation of: 0 <= position";
        assert posi < wrd.length() : "Violation of: position < |definition|";

        int i = posi;
        boolean temp = separators.contains(wrd.charAt(posi));
        while (i < wrd.length() && separators.contains(wrd.charAt(i)) == temp) {
            i++;
        }
        String s = wrd.substring(posi, i);
        return s;
    }

    /**
     * This method will print out the entire HTML file that is required to
     * display every word and its word count nicely for the client.
     *
     * @param outToFile
     *            the output stream wring to the file
     * @param input
     *            output stream to my file
     * @param inputFile
     *            a string of the words from the input file
     * @requires |word| > 0
     * @ensures outToFile contains code in html format
     */
    private static void printContentToFile(SimpleWriter outToFile,
            SimpleReader input, String inputFile,
            SortingMachine<Map.Pair<String, Integer>> count,
            SortingMachine<Map.Pair<String, Integer>> alpha) {
        assert outToFile != null : "Violation of: output is not null";
        assert outToFile.isOpen() : "Violation of: output.is_open";

        outToFile.println("<html>");
        outToFile.println("<head>");
        outToFile.println("<title>Project9</title>");
        outToFile.println("<link href=" + '"' + "doc/tagcloud.css" + '"'
                + " rel=" + '"' + "stylesheet" + '"' + " type =" + '"'
                + "text/css" + '"' + ">");

        outToFile.println("</head>");
        outToFile.println("<body>");
        // includes the name of the input file at the header
        outToFile.println("<h2> Top 100 words in " + inputFile + "</h2>");
        outToFile.println("<p class =" + '"' + "cbox" + '"' + ">");

        Map<String, Integer> wrdCounterMap = new Map1L<>();
        //      Queue<String> wordQ = new Queue1L<>();

        Map<Pair<String, Integer>, Integer> fontMap = new Map1L<Pair<String, Integer>, Integer>();

        wordProcessor(input, wrdCounterMap, fontMap);
        //       sorting(wrdCounterMap, wordQ);

        //Use for loop to iterate over queue and output
        //corresponding word count and print the word count to html
//        for (String print : wordQ) {
//            outToFile.print("<tr>\n<td>" + print + "</td>\n<td>"
//                    + wrdCounterMap.value(print) + "</td></tr>");
//        }

        //process the soprting machine here

        //add the extration mode from map
        while (wrdCounterMap.size() != 0) {
            count.add(wrdCounterMap.removeAny());
        }

        //now we need to sort the sortitng machine
        count.changeToExtractionMode();

        //sorting in alpha lexi order
        int lengthg = count.size();
        for (int i = 0; i < lengthg && i < 100; i++) {
            alpha.add(count.removeFirst());
        }
        alpha.changeToExtractionMode();

        //*****************************************

        System.out.println(count);
        System.out.println("*****************************************");
        System.out.println(alpha);
        //print out opur decided font size order and format html
        while (alpha.size() > 0) {

            Map.Pair<String, Integer> pair = alpha.removeFirst();
            int ttemp = pair.value();
            int font = ttemp / 10;
            outToFile.println("<span style=" + '"' + "cursor:default" + '"'
                    + " class=" + '"' + 'f' + font + '"' + " title=" + '"'
                    + "count: " + pair.value() + '"' + ">" + pair.key()
                    + "</span>");
        }

        outToFile.println("</p>");
        outToFile.println("</div>");
        outToFile.println("</body>");
        outToFile.println("</html>");
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments; unused here
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();

        out.print("Enter input file name with file extention: ");
        String inputFile = in.nextLine();
        SimpleReader input = new SimpleReader1L(inputFile);

        out.print("Enter output file name with extension: ");
        String outputFile = in.nextLine();
        SimpleWriter outToFile = new SimpleWriter1L(outputFile);

        Comparator<Map.Pair<String, Integer>> ci = new IntegerLT();
        SortingMachine<Map.Pair<String, Integer>> count = new SortingMachine1L<Map.Pair<String, Integer>>(
                ci);

        Comparator<Map.Pair<String, Integer>> cii = new StringLT();
        SortingMachine<Map.Pair<String, Integer>> alpha = new SortingMachine1L<Map.Pair<String, Integer>>(
                cii);

        printContentToFile(outToFile, input, inputFile, count, alpha);

        out.print("Done printing to file!");

        in.close();
        out.close();
    }

}
