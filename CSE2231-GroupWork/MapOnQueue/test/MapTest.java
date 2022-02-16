import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.map.Map;
import components.map.Map.Pair;

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

    /*
     * Test cases for constructors
     */

    @Test
    public final void testNoArgumentConstructor() {
        /*
         * Set up variables and call method under test
         */
        Map<String, String> m = this.constructorTest();
        Map<String, String> mExpected = this.constructorRef();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
    }

    /*
     * Test cases for kernel methods
     */

    // add, remove, removeAny, value, hasKey, and size

    @Test
    public final void testAddEmpty() {
        /*
         * Set up variables
         */
        Map<String, String> m = this.createFromArgsTest();
        Map<String, String> mExpected = this.createFromArgsRef("0", "red");
        /*
         * Call method under test
         */
        m.add("0", "red");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
    }

    @Test
    public final void testAddNonEmptyOne() {
        /*
         * Set up variables
         */
        Map<String, String> m = this.createFromArgsTest("0", "red");
        Map<String, String> mExpected = this.createFromArgsRef("0", "red", "1",
                "blue");
        /*
         * Call method under test
         */
        m.add("1", "blue");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
    }

    @Test
    public final void testAddNonEmptyMoreThanOne() {
        /*
         * Set up variables
         */
        Map<String, String> m = this.createFromArgsTest("0", "red", "1", "blue",
                "2", "green");
        Map<String, String> mExpected = this.createFromArgsRef("0", "red", "1",
                "blue", "2", "green", "3", "yellow");
        /*
         * Call method under test
         */
        m.add("3", "yellow");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
    }

    @Test
    public final void testRemoveLeavingEmpty() {
        /*
         * Set up variables
         */
        Map<String, String> m = this.createFromArgsTest("0", "red");
        Map<String, String> mExpected = this.createFromArgsRef();
        /*
         * Call method under test
         */
        Pair<String, String> x = m.remove("0");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals("0", x.key());
        assertEquals("red", x.value());
        assertEquals(mExpected, m);
    }

    @Test
    public final void testRemoveLeavingNonEmptyOne() {
        /*
         * Set up variables
         */
        Map<String, String> m = this.createFromArgsTest("0", "red", "1",
                "blue");
        Map<String, String> mExpected = this.createFromArgsRef("1", "blue");
        /*
         * Call method under test
         */
        Pair<String, String> x = m.remove("0");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals("0", x.key());
        assertEquals("red", x.value());
        assertEquals(mExpected, m);
    }

    @Test
    public final void testRemoveLeavingNonEmptyMoreThanOne() {
        /*
         * Set up variables
         */
        Map<String, String> m = this.createFromArgsTest("0", "red", "1", "blue",
                "2", "black", "3", "yellow");
        Map<String, String> mExpected = this.createFromArgsRef("0", "red", "1",
                "blue", "2", "black");
        /*
         * Call method under test
         */
        Pair<String, String> x = m.remove("3");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals("3", x.key());
        assertEquals("yellow", x.value());
        assertEquals(mExpected, m);
    }

    @Test
    public final void testValueOneEntry() {
        /*
         * Set up variables
         */
        Map<String, String> m = this.createFromArgsTest("0", "red");
        /*
         * Call method under test
         */
        String x = m.value("0");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals("red", x);
    }

    @Test
    public final void testValueTwoEntries() {
        /*
         * Set up variables
         */
        Map<String, String> m = this.createFromArgsTest("0", "red", "1",
                "green");
        /*
         * Call method under test
         */
        String x = m.value("1");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals("green", x);
    }

    @Test
    public final void testValueThreeEntries() {
        /*
         * Set up variables
         */
        Map<String, String> m = this.createFromArgsTest("0", "red", "1",
                "green", "2", "black");
        /*
         * Call method under test
         */
        String x = m.value("2");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals("black", x);
    }

    @Test
    public final void testHasKeyOneEntryTrue() {
        /*
         * Set up variables
         */
        Map<String, String> m = this.createFromArgsTest("0", "red");
        /*
         * Call method under test
         */
        boolean x = m.hasKey("0");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(true, x);
    }

    @Test
    public final void testHasKeyOneEntryFalse() {
        /*
         * Set up variables
         */
        Map<String, String> m = this.createFromArgsTest("0", "red");
        /*
         * Call method under test
         */
        boolean x = m.hasKey("1");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(false, x);
    }

    @Test
    public final void testHasKeyTwoEntriesTrue() {
        /*
         * Set up variables
         */
        Map<String, String> m = this.createFromArgsTest("0", "red", "1",
                "green");
        /*
         * Call method under test
         */
        boolean x = m.hasKey("1");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(true, x);
    }

    @Test
    public final void testHasKeyTwoEntriesFalse() {
        /*
         * Set up variables
         */
        Map<String, String> m = this.createFromArgsTest("0", "red", "1",
                "green");
        /*
         * Call method under test
         */
        boolean x = m.hasKey("10");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(false, x);
    }

    @Test
    public final void testHasKeyThreeEntriesTrue() {
        /*
         * Set up variables
         */
        Map<String, String> m = this.createFromArgsTest("0", "red", "1",
                "green", "2", "black");
        /*
         * Call method under test
         */
        boolean x = m.hasKey("2");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(true, x);
    }

    @Test
    public final void testHasKeyThreeEntriesFalse() {
        /*
         * Set up variables
         */
        Map<String, String> m = this.createFromArgsTest("0", "red", "1",
                "green", "2", "black");
        /*
         * Call method under test
         */
        boolean x = m.hasKey("20");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(false, x);
    }

    @Test
    public final void testLengthEmpty() {
        /*
         * Set up variables
         */
        Map<String, String> m = this.createFromArgsTest();
        /*
         * Call method under test
         */
        int i = m.size();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(0, i);
    }

    @Test
    public final void testLengthNonEmpty() {
        /*
         * Set up variables
         */
        Map<String, String> m = this.createFromArgsTest("0", "red");
        /*
         * Call method under test
         */
        int i = m.size();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(1, i);
    }

    @Test
    public final void testLengthNonEmptyMoreThanOne() {
        /*
         * Set up variables
         */
        Map<String, String> m = this.createFromArgsTest("0", "red", "1",
                "blue");
        /*
         * Call method under test
         */
        int i = m.size();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(2, i);
    }

}
