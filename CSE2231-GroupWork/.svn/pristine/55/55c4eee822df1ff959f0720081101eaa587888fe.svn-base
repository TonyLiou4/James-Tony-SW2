import static org.junit.Assert.assertEquals;

import components.set.Set;

/**
 * JUnit test fixture for {@code Set<String>}'s constructor and kernel methods.
 *
 * @author Put your name here
 *
 */
public abstract class SetTest {

    /**
     * Invokes the appropriate {@code Set} constructor and returns the result.
     *
     * @return the new set
     * @ensures constructorTest = {}
     */
    protected abstract Set<String> constructorTest();

    /**
     * Invokes the appropriate {@code Set} constructor and returns the result.
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

    // test Contructor
    public final void testContrutor() {
        Set<T> test = this.constructorTest();
        Set<T> ref = this.constructorRef();

        assertEquals(ref, test);
    }

    // add method
    public final void testAdd() {
        Set<Integer> test = this.createFromArgsTest(1, 2);
        Set<Integer> expect = this.createFromArgsRef(3, 1, 2);

        test.add(3);
        assertEqiuals(expected, test);
    }

    // remove method
    public final void testRemove() {
        Set<Integer> test = this.createFromArgsTest(1, 2);
        Set<Integer> expect = this.createFromArgsRef(2);
        test.remove(1);

        assertEquals(expect, test);
    }

    // removeAny method
    public final void testRemoveAny() {
        Set<Integer> test = this.createFromArgsTest(1, 2);
        Set<Integer> expect = this.createFromArgsRef(2);
        Set<Integer> expect2 = this.createFromArgsRef(1);

        test.removeAny();
        assertEquals(expect || expect2, test);
    }

    // contains method

    public final void testContains() {
        Set<Integer> test = this.createFromArgsTest(1, 2);
        Set<Integer> expect = this.createFromArgsRef(2);

        test.contains(2);
        assertEquals(expect, test);
    }

    // size method
    public final void testSize() {
        Set<Integer> test = this.createFromArgsTest(1, 2);
        Set<Integer> expect = this.createFromArgsRef(2);

        test.size();
        assertEquals(test, expect);
    }
}
