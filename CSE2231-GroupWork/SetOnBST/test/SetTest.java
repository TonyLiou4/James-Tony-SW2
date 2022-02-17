import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import components.set.Set;

/**
 * JUnit test fixture for {@code Set<String>}'s constructor and kernel methods.
 *
 * @author Sungwoon Park and Tony Liou
 *
 */
public abstract class SetTest {

    /**
     * Invokes the appropriate {@code Set} constructor for the implementation
     * under test and returns the result.
     *
     * @return the new set
     * @ensures constructorTest = {}
     */
    protected abstract Set<String> constructorTest();

    /**
     * Invokes the appropriate {@code Set} constructor for the reference
     * implementation and returns the result.
     *
     * @return the new set
     * @ensures constructorRef = {}
     */
    protected abstract Set<String> constructorRef();

    /**
     * Creates and returns a {@code Set<String>} of the implementation under
     * test type with the given entries.
     *
     * @param args
     *            the entries for the set
     * @return the constructed set
     * @requires [every entry in args is unique]
     * @ensures createFromArgsTest = [entries in args]
     */
    private Set<String> createFromArgsTest(String... args) {
        Set<String> set = this.constructorTest();
        for (String s : args) {
            assert !set.contains(
                    s) : "Violation of: every entry in args is unique";
            set.add(s);
        }
        return set;
    }

    /**
     * Creates and returns a {@code Set<String>} of the reference implementation
     * type with the given entries.
     *
     * @param args
     *            the entries for the set
     * @return the constructed set
     * @requires [every entry in args is unique]
     * @ensures createFromArgsRef = [entries in args]
     */
    private Set<String> createFromArgsRef(String... args) {
        Set<String> set = this.constructorRef();
        for (String s : args) {
            assert !set.contains(
                    s) : "Violation of: every entry in args is unique";
            set.add(s);
        }
        return set;
    }

    // TODO - add test cases for constructor, add, remove, removeAny, contains, and size

    /**
     * Test constructors
     */
    @Test
    public void testNoArgsConstructor_Empty() {
        Set<String> test = this.constructorTest();
        Set<String> expected = this.constructorRef();

        assertEquals(expected, test);
    }

    /**
     * Test add method
     */

    @Test
    public void testAdd_Empty() {

        Set<String> test = this.createFromArgsTest();
        Set<String> expected = this.createFromArgsRef();
        test.add("James");
        expected.add("James");

        assertEquals(expected, test);
    }

    @Test
    public void testAdd_Non_Empty() {

        Set<String> test = this.createFromArgsTest("Tony");
        Set<String> expected = this.createFromArgsRef("Tony");
        test.add("James");
        expected.add("James");

        assertEquals(expected, test);
    }

    /**
     * Test remove method
     */

    @Test
    public void testRemove_oneToEmpty() {

        Set<String> test = this.createFromArgsTest("James");
        Set<String> expected = this.createFromArgsRef("James");
        String string = test.remove("James");
        String stringExpected = expected.remove("James");

        assertEquals(expected, test);
        assertEquals(stringExpected, string);
    }

    @Test
    public void testRemove_twoToOne() {

        Set<String> test = this.createFromArgsTest("James", "Tony");
        Set<String> expected = this.createFromArgsRef("James", "Tony");
        String string = test.remove("James");
        String stringExpected = expected.remove("James");

        assertEquals(expected, test);
        assertEquals(stringExpected, string);
    }

    /**
     * Test removeAny method
     */

    @Test
    public void testRemoveAny_oneElement() {

        Set<String> test = this.createFromArgsTest("James");
        Set<String> expected = this.createFromArgsRef("James");
        String string = test.removeAny();
        assertTrue(expected.contains(string));
        String stringExpected = expected.remove(string);

        assertEquals(expected, test);
        assertEquals(stringExpected, string);
    }

    @Test
    public void testRemoveAny_twoElement() {

        Set<String> test = this.createFromArgsTest("James", "Tony");
        Set<String> expected = this.createFromArgsRef("James", "Tony");
        String string = test.removeAny();
        assertTrue(expected.contains(string));
        String stringExpected = expected.remove(string);

        assertEquals(expected, test);
        assertEquals(stringExpected, string);
    }

    /**
     * Test contains method
     */

    @Test
    public void testContains_empty() {

        Set<String> test = this.createFromArgsTest();
        Set<String> expected = this.createFromArgsRef();
        boolean contain = test.contains("James");
        boolean containExpected = expected.contains("James");

        assertEquals(expected, test);
        assertEquals(containExpected, contain);
    }

    @Test
    public void testContains_notContains() {

        Set<String> test = this.createFromArgsTest("James");
        Set<String> expected = this.createFromArgsRef("James");
        boolean contain = test.contains("James");
        boolean containExpected = expected.contains("James");

        assertEquals(expected, test);
        assertEquals(containExpected, contain);
    }

    @Test
    public void testContains_itContains() {

        Set<String> test = this.createFromArgsTest("1", "2", "3", "4", "5");
        Set<String> expected = this.createFromArgsRef("1", "2", "3", "4", "5");
        boolean contain = test.contains("a");
        boolean containExpected = expected.contains("a");

        assertEquals(expected, test);
        assertEquals(containExpected, contain);
    }

    /**
     * Test size method
     */

    @Test
    public void testSize_empty() {

        Set<String> test = this.createFromArgsTest();
        Set<String> expected = this.createFromArgsRef();
        int size = test.size();
        int sizeExpected = expected.size();

        assertEquals(expected, test);
        assertEquals(sizeExpected, size);
    }

    @Test
    public void testSize_oneElement() {

        Set<String> test = this.createFromArgsTest("James");
        Set<String> expected = this.createFromArgsRef("James");
        int size = test.size();
        int sizeExpected = expected.size();

        assertEquals(expected, test);
        assertEquals(sizeExpected, size);
    }

    @Test
    public void testSize_fiveElement() {

        Set<String> test = this.createFromArgsTest("1", "2", "3", "4", "5");
        Set<String> expected = this.createFromArgsRef("1", "2", "3", "4", "5");
        int size = test.size();
        int sizeExpected = expected.size();

        assertEquals(expected, test);
        assertEquals(sizeExpected, size);
    }
}
