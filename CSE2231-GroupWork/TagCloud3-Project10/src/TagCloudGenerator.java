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
