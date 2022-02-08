import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.map.Map;

/**
 * JUnit test fixture for {@code Map<String, String>}'s constructor and kernel
 * methods.
 *
 * @author Put your name here
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

    // TODO - add test cases for constructor, add, remove, removeAny, value,
    // hasKey, and size

    /*
     * Test case for the constructor
     */
    @Test
    public void testNoArgsConstructor_Empty() {

        //  Set up variables and call method under test

        Map<String, String> test = this.constructorTest();
        Map<String, String> expected = this.constructorRef();

        //  Assert that values of variables match expectations

        assertEquals(expected, test);
    }

    @Test
    public void testConstructor() {

        //  Set up variables and call method under test

        Map<String, String> test = this.createFromArgsTest("0", "0");
        Map<String, String> expected = this.createFromArgsRef("0", "0");

        //  Assert that values of variables match expectations
        assertEquals(expected, test);
    }

    /**
     * Test add method
     */

    @Test
    public void testAdd_ZeroSize() {

        Map<String, String> test = this.createFromArgsTest();
        Map<String, String> expected = this.createFromArgsRef("James", "1");
        test.add("James", "1");

        assertEquals(expected, test);
    }

    @Test
    public void testAdd_Normal() {

        Map<String, String> test = this.createFromArgsTest("James", "1");
        Map<String, String> expected = this.createFromArgsRef("James", "1",
                "Tony", "2");
        test.add("Tony", "2");

        assertEquals(expected, test);
    }

    /**
     * Test remove method
     */

    @Test
    public void testRemove1() {

        Map<String, String> test = this.createFromArgsTest("James", "1");
        Map<String, String> expected = this.createFromArgsRef("James", "1");
        Map.Pair<String, String> pair = test.remove("James");
        Map.Pair<String, String> pairExpected = expected.remove("James");

        assertEquals(pairExpected, pair);
        assertEquals(expected, test);
    }

    @Test
    public void testRemove2() {

        Map<String, String> test = this.createFromArgsTest("James", "1", "Tony",
                "2");
        Map<String, String> expected = this.createFromArgsRef("Tony", "2");
        test.remove("James");

        assertEquals(expected, test);
    }

    /**
     * Test removeAny method
     */

    @Test
    public void testRemoveAny_OneElement() {

        Map<String, String> test = this.createFromArgsTest("James", "1");
        Map<String, String> expected = this.createFromArgsTest("James", "1");
        Map.Pair<String, String> pair = test.removeAny();

        assertEquals(true, expected.hasKey(pair.key()));
        Map.Pair<String, String> pairExpected = expected.remove(pair.key());
        assertEquals(pairExpected, pair);
        assertEquals(expected, test);
    }

    @Test
    public void testRemoveAny_TwoElement() {

        Map<String, String> test = this.createFromArgsTest("James", "1", "Tony",
                "2");
        Map<String, String> expected = this.createFromArgsTest("James", "1",
                "Tony", "2");
        Map.Pair<String, String> pair = test.removeAny();

        assertEquals(true, expected.hasKey(pair.key()));
        Map.Pair<String, String> pairExpected = expected.remove(pair.key());
        assertEquals(pairExpected, pair);
        assertEquals(expected, test);
    }

    @Test
    public void testRemoveAny_TElement() {

        Map<String, String> test = this.createFromArgsTest("James", "1", "Tony",
                "2", "Bob", "3");
        Map<String, String> expected = this.createFromArgsTest("James", "1",
                "Tony", "2", "Bob", "3");
        Map.Pair<String, String> pair = test.removeAny();

        assertEquals(true, expected.hasKey(pair.key()));
        Map.Pair<String, String> pairExpected = expected.remove(pair.key());
        assertEquals(pairExpected, pair);
        assertEquals(expected, test);
    }

    /**
     * Test value method
     */

    @Test
    public void testValue1() {

        Map<String, String> map = this.createFromArgsTest("James", "1", "Tony",
                "2");
        String test = map.value("Tony");
        String expected = "2";

        assertEquals(expected, test);
    }

    @Test
    public void testValue2() {

        Map<String, String> map = this.createFromArgsTest("James", "1", "Tony",
                "2");
        String test = map.value("James");
        String expected = "1";

        assertEquals(expected, test);
    }

    @Test
    public void testValue() {
        Map<String, String> map = this.createFromArgsTest("James", "1", "Tony",
                "2", "John", "10000000000000000000");
        String test = map.value("John");
        String expected = "10000000000000000000";

        assertEquals(expected, test);
    }

    /**
     * Test hasKey method
     */

    @Test
    public void testHasKey_isInKey() {
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
    public void testHasKey_notInKey() {
        Map<String, String> test = this.createFromArgsTest("James", "1", "Tony",
                "2", "Bob", "3");
        Map<String, String> expected = this.createFromArgsTest("James", "1",
                "Tony", "2", "Bob", "3");
        boolean testBoo = test.hasKey("BooB");
        boolean expectedBoo = expected.hasKey("BooB");

        assertEquals(expectedBoo, testBoo);
        assertEquals(expected, test);
    }

    /**
     * Test size method
     */
    @Test
    public void testIsSize1() {

        Map<String, String> map = this.createFromArgsTest();
        int test = map.size();
        int expected = 0;

        assertEquals(expected, test);
    }

    @Test
    public void testIsSize2() {

        Map<String, String> map = this.createFromArgsTest("0", "0");
        int test = map.size();
        int expected = 1;

        assertEquals(expected, test);
    }

    @Test
    public void testIsSize3() {

        Map<String, String> map = this.createFromArgsTest("0", "0", "1", "1",
                "2", "2", "3", "3", "4", "4");
        int test = map.size();
        int expected = 5;

        assertEquals(expected, test);
    }
}
