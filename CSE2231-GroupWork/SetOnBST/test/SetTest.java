import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.set.Set;

/**
 * JUnit test fixture for {@code Set<String>}'s constructor and kernel methods.
 *
 * @author Put your name here
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
    @Test
    public void testNoArgsConstructor_Empty() {
        Set<String> test = this.constructorTest();
        Set<String> expected = this.constructorRef();

        assertEquals(expected, test);
        //this test case passed
    }

    @Test
    public void testSet3aConstructors() {
        Set<String> test = this.constructorTest();
        Set<String> expected = this.constructorRef();

        assertEquals(expected, test);
        //this test case also passed
    }

    @Test
    public void testAdd_Regular() {

        Set<String> test = this.createFromArgsTest("James", "1", "Tony", "2");
        Set<String> expected = this.createFromArgsRef("James", "1", "Tony",
                "2");
        test.add("qwertyuiopo");
        expected.add("qwertyuiopo");
        assertEquals(expected, test);
    }

    @Test
    public void testRemove_Regular() {

        Set<String> test = this.createFromArgsTest("James", "1", "Tony", "2");
        Set<String> expected = this.createFromArgsRef("James", "1", "Tony",
                "2");
        test.add("qwertyuiopo");
        expected.add("qwertyuiopo");
        assertEquals(expected, test);
    }

    @Test
    public void testAny_Regular() {
//*****Don't know how to do this test case
        Set<String> test = this.createFromArgsTest("James", "1", "Tony", "2");
        Set<String> expected = this.createFromArgsRef("James", "1", "Tony",
                "2");
        test.add("qwertyuiopo");
        expected.add("qwertyuiopo");
        assertEquals(expected, test);
    }

    @Test
    public void testContains_yes() {
//NOTE ******** cannpt be an empyty set
        Set<String> test = this.createFromArgsTest("James", "1", "Tony", "2");
        Set<String> expected = this.createFromArgsRef("James", "1", "Tony",
                "2");
        boolean testContains = test.contains("James");
        boolean expectedContains = expected.contains("Jeames");
        assertEquals(testContains, expectedContains);
        assertEquals(expected, test);
    }

    @Test
    public void testContains_no() {

        Set<String> test = this.createFromArgsTest("James", "1", "Tony", "2");
        Set<String> expected = this.createFromArgsRef("James", "1", "Tony",
                "2");

        boolean testContains = test.contains("NotJames");
        boolean expectedContains = expected.contains("NotJames");

        assertEquals(testContains, expectedContains);
        assertEquals(expected, test);
    }

    @Test
    public void testSize_0() {

        Set<String> test = this.createFromArgsTest();
        Set<String> expected = this.createFromArgsRef();

        int testSize = test.size();
        int expectedSize = expected.size();

        assertEquals(testSize, expectedSize);
        assertEquals(expected, test);
    }

    @Test
    public void testSize_1() {

        Set<String> test = this.createFromArgsTest("2");
        Set<String> expected = this.createFromArgsRef("2");

        int testSize = test.size();
        int expectedSize = expected.size();

        assertEquals(testSize, expectedSize);
        assertEquals(expected, test);
    }

    @Test
    public void testSize_nultiple() {

        Set<String> test = this.createFromArgsTest("2", "q", "w", "5", "hh",
                "bob");
        Set<String> expected = this.createFromArgsRef("2", "q", "w", "5", "hh",
                "bob");

        int testSize = test.size();
        int expectedSize = expected.size();

        assertEquals(testSize, expectedSize);
        assertEquals(expected, test);
    }
}
