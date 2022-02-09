import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.map.Map;

/**
 * JUnit test fixture for {@code Map<String, String>}'s constructor and kernel
 * methods.
 *
 * @author Sungwoon Park and Tony Liou
 *
 */
public abstract class MapTest {

    /**
     * Invokes the appropriate {@code Map} constructor for the implementation
     * under test and returns the result.
     *
     * @return the new map
     * @ensures constructorTest = {}
     */
    protected abstract Map<String, String> constructorTest();

    /**
     * Invokes the appropriate {@code Map} constructor for the reference
     * implementation and returns the result.
     *
     * @return the new map
     * @ensures constructorRef = {}
     */
    protected abstract Map<String, String> constructorRef();

    /**
     *
     * Creates and returns a {@code Map<String, String>} of the implementation
     * under test type with the given entries.
     *
     * @param args
     *            the (key, value) pairs for the map
     * @return the constructed map
     * @requires <pre>
     * [args.length is even]  and
     * [the 'key' entries in args are unique]
     * </pre>
     * @ensures createFromArgsTest = [pairs in args]
     */
    private Map<String, String> createFromArgsTest(String... args) {
        assert args.length % 2 == 0 : "Violation of: args.length is even";
        Map<String, String> map = this.constructorTest();
        for (int i = 0; i < args.length; i += 2) {
            assert !map.hasKey(args[i]) : ""
                    + "Violation of: the 'key' entries in args are unique";
            map.add(args[i], args[i + 1]);
        }
        return map;
    }

    /**
     *
     * Creates and returns a {@code Map<String, String>} of the reference
     * implementation type with the given entries.
     *
     * @param args
     *            the (key, value) pairs for the map
     * @return the constructed map
     * @requires <pre>
     * [args.length is even]  and
     * [the 'key' entries in args are unique]
     * </pre>
     * @ensures createFromArgsRef = [pairs in args]
     */
    private Map<String, String> createFromArgsRef(String... args) {
        assert args.length % 2 == 0 : "Violation of: args.length is even";
        Map<String, String> map = this.constructorRef();
        for (int i = 0; i < args.length; i += 2) {
            assert !map.hasKey(args[i]) : ""
                    + "Violation of: the 'key' entries in args are unique";
            map.add(args[i], args[i + 1]);
        }
        return map;
    }

    /**
     * Test case for the constructor
     */
    @Test
    public void testNoArgsConstructor_Empty() {
        Map<String, String> test = this.constructorTest();
        Map<String, String> expected = this.constructorRef();

        assertEquals(expected, test);
    }

    @Test
    public void testConstructor_OnePair() {
        Map<String, String> test = this.createFromArgsTest("Zero", "0");
        Map<String, String> expected = this.createFromArgsRef("Zero", "0");

        assertEquals(expected, test);
    }

    @Test
    public void testConstructor_MultiplePair() {
        Map<String, String> test = this.createFromArgsTest("zero", "0", "one",
                "1", "two", "2");
        Map<String, String> expected = this.createFromArgsRef("zero", "0",
                "one", "1", "two", "2");

        assertEquals(expected, test);
    }

    /**
     * Test add method
     */
    @Test
    public void testAdd_Empty() {

        Map<String, String> test = this.createFromArgsTest();
        Map<String, String> expected = this.createFromArgsRef();
        test.add("James", "1");
        expected.add("James", "1");

        assertEquals(expected, test);
    }

    @Test
    public void testAdd_OnePair() {
        Map<String, String> test = this.createFromArgsTest("James", "1");
        Map<String, String> expected = this.createFromArgsRef("James", "1");
        test.add("Tony", "2");
        expected.add("Tony", "2");

        assertEquals(expected, test);
    }

    /**
     * Test remove method
     */
    @Test
    public void testRemove_OnePair() {
        Map<String, String> test = this.createFromArgsTest("James", "1");
        Map<String, String> expected = this.createFromArgsRef("James", "1");
        Map.Pair<String, String> pair = test.remove("James");
        Map.Pair<String, String> pairExpected = expected.remove("James");

        assertEquals(pairExpected, pair);
        assertEquals(expected, test);
    }

    @Test
    public void testRemove_MultiplePair() {

        Map<String, String> test = this.createFromArgsTest("James", "1", "Tony",
                "2");
        Map<String, String> expected = this.createFromArgsRef("James", "1",
                "Tony", "2");
        Map.Pair<String, String> pair = test.remove("Tony");
        Map.Pair<String, String> pairExpected = expected.remove("Tony");

        assertEquals(pairExpected, pair);
        assertEquals(expected, test);
    }

    /**
     * Test removeAny method
     */
    @Test
    public void testRemoveAny_OnePair() {
        Map<String, String> test = this.createFromArgsTest("James", "1");
        Map<String, String> expected = this.createFromArgsRef("James", "1");
        Map.Pair<String, String> pair = test.removeAny();
        Map.Pair<String, String> pairExpected = expected.remove(pair.key());

        assertEquals(true, expected.hasKey(pair.key()));
        assertEquals(pairExpected, pair);
        assertEquals(expected, test);
    }

    @Test
    public void testRemoveAny_TwoPair() {

        Map<String, String> test = this.createFromArgsTest("James", "1", "Tony",
                "2");
        Map<String, String> expected = this.createFromArgsRef("James", "1",
                "Tony", "2");
        Map.Pair<String, String> pair = test.removeAny();
        Map.Pair<String, String> pairExpected = expected.remove(pair.key());

        assertEquals(true, expected.hasKey(pair.key()));
        assertEquals(pairExpected, pair);
        assertEquals(expected, test);
    }

    @Test
    public void testRemoveAny_ThreePair() {

        Map<String, String> test = this.createFromArgsTest("James", "1", "Tony",
                "2", "Bob", "3");
        Map<String, String> expected = this.createFromArgsRef("James", "1",
                "Tony", "2", "Bob", "3");
        Map.Pair<String, String> pair = test.removeAny();
        Map.Pair<String, String> pairExpected = expected.remove(pair.key());

        assertEquals(true, expected.hasKey(pair.key()));
        assertEquals(pairExpected, pair);
        assertEquals(expected, test);
    }

    /**
     * Test value method
     */
    @Test
    public void testValue_onePair() {
        Map<String, String> test = this.createFromArgsTest("James", "1");
        Map<String, String> expected = this.createFromArgsRef("James", "1");
        String value = test.value("James");
        String valueExpected = test.value("James");

        assertEquals(valueExpected, value);
        assertEquals(expected, test);
    }

    @Test
    public void testValue_twoPair() {
        Map<String, String> test = this.createFromArgsTest("James", "1", "Tony",
                "2");
        Map<String, String> expected = this.createFromArgsRef("James", "1",
                "Tony", "2");
        String value = test.value("Tony");
        String valueExpected = test.value("Tony");

        assertEquals(valueExpected, value);
        assertEquals(expected, test);
    }

    /**
     * Test hasKey method
     */
    @Test
    public void testHasKey_IsInKey() {

        Map<String, String> test = this.createFromArgsTest("James", "1", "Tony",
                "2", "Bob", "3");
        Map<String, String> expected = this.createFromArgsTest("James", "1",
                "Tony", "2", "Bob", "3");
        boolean testBoo = test.hasKey("James");
        boolean expectedBoo = expected.hasKey("James");

        assertEquals(expectedBoo, testBoo);
        assertEquals(expected, test);
    }

    @Test
    public void testHasKey_NotInKey() {

        Map<String, String> test = this.createFromArgsTest("James", "1", "Tony",
                "2", "Bob", "3");
        Map<String, String> expected = this.createFromArgsTest("James", "1",
                "Tony", "2", "Bob", "3");
        boolean testBoo = test.hasKey("Kelvin");
        boolean expectedBoo = expected.hasKey("Kelvin");

        assertEquals(expectedBoo, testBoo);
        assertEquals(expected, test);
    }

    /**
     * Test size method
     */
    @Test
    public void testIsSize_Empty() {

        Map<String, String> test = this.createFromArgsTest();
        Map<String, String> expected = this.createFromArgsRef();
        int size = test.size();
        int expectedSize = expected.size();

        assertEquals(expectedSize, size);
        assertEquals(expected, test);
    }

    @Test
    public void testIsSize_OnePair() {
        Map<String, String> test = this.createFromArgsTest("James", "1");
        Map<String, String> expected = this.createFromArgsRef("James", "1");
        int size = test.size();
        int expectedSize = expected.size();

        assertEquals(expectedSize, size);
        assertEquals(expected, test);
    }

    @Test
    public void testIsSize_TwoPair() {
        Map<String, String> test = this.createFromArgsTest("James", "1", "Tony",
                "2");
        Map<String, String> expected = this.createFromArgsRef("James", "1",
                "Tony", "2");
        int size = test.size();
        int expectedSize = expected.size();

        assertEquals(expectedSize, size);
        assertEquals(expected, test);
    }
}
