import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.sequence.Sequence;

/**
 * JUnit test fixture for {@code Sequence<String>}'s constructor and kernel
 * methods.
 *
 * @author Put your name here
 *
 */
public abstract class SequenceTest {

    /**
     * Invokes the appropriate {@code Sequence} constructor for the
     * implementation under test and returns the result.
     *
     * @return the new sequence
     * @ensures constructorTest = <>
     */
    protected abstract Sequence<String> constructorTest();

    /**
     * Invokes the appropriate {@code Sequence} constructor for the reference
     * implementation and returns the result.
     *
     * @return the new sequence
     * @ensures constructorRef = <>
     */
    protected abstract Sequence<String> constructorRef();

    /**
     *
     * Creates and returns a {@code Sequence<String>} of the implementation
     * under test type with the given entries.
     *
     * @param args
     *            the entries for the sequence
     * @return the constructed sequence
     * @ensures createFromArgsTest = [entries in args]
     */
    private Sequence<String> createFromArgsTest(String... args) {
        Sequence<String> sequence = this.constructorTest();
        for (String s : args) {
            sequence.add(sequence.length(), s);
        }
        return sequence;
    }

    /**
     *
     * Creates and returns a {@code Sequence<String>} of the reference
     * implementation type with the given entries.
     *
     * @param args
     *            the entries for the sequence
     * @return the constructed sequence
     * @ensures createFromArgsRef = [entries in args]
     */
    private Sequence<String> createFromArgsRef(String... args) {
        Sequence<String> sequence = this.constructorRef();
        for (String s : args) {
            sequence.add(sequence.length(), s);
        }
        return sequence;
    }

    // TODO - add test cases for constructor, add, remove, and length
//    @Test
//    public final void testNoArgumentConstructor() {
//        /*
//         * Set up variables and call method under test
//         */
//        Sequence<String> s = this.constructorTest();
//        Sequence<String> sExpected = this.constructorRef();
//        /*
//         * Assert that values of variables match expectations
//         */
//        assertEquals(sExpected, s);
//    }
//
//    /*
//     * Test cases for kernel methods
//     */
//
//    @Test
//    public final void testAddEmpty() {
//        /*
//         * Set up variables
//         */
//        Sequence<String> s = this.createFromArgsTest();
//        Sequence<String> sExpected = this.createFromArgsRef("red");
//        /*
//         * Call method under test
//         */
//        s.add(0, "red");
//
//        assertEquals(sExpected, s);
//
//    }

//    @Test
//    public final void testAddInMiddle() {
//        /*
//         * Set up variables
//         */
//        Sequence<String> s = this.createFromArgsTest("red", "blue", "green",
//                "yellow", "purple");
//        Sequence<String> sExpected = this.createFromArgsRef("red", "blue",
//                "black", "green", "yellow", "purple");
//        /*
//         * Call method under test
//         */
//        s.add(2, "black");
//
//        assertEquals(sExpected, s);
//    }

    @Test
    public final void testflip() {
        /*
         * Set up variables
         */
        Sequence<String> s = this.createFromArgsTest("red", "blue", "green",
                "yellow", "purple");
//        Sequence<String> sExpected = this.createFromArgsRef("purple", "yellow",
//                "green", "blue", "red");
        Sequence<String> sExpected = this.createFromArgsRef("red", "blue",
                "green", "yellow", "purple");
        /*
         * Call method under test
         */
        s.flip();
        sExpected.flip();

        assertEquals(sExpected, s);
    }

    @Test
    public final void testflip2() {
        /*
         * Set up variables
         */
        Sequence<String> s = this.createFromArgsTest("red");
        Sequence<String> sExpected = this.createFromArgsRef("red");
        /*
         * Call method under test
         */
        s.flip();

        assertEquals(sExpected, s);
    }

    @Test
    public final void testflip3() {
        /*
         * Set up variables
         */
        Sequence<String> s = this.createFromArgsTest("red", "blue", "green",
                "yellow", "purple", "black", "grey", "orange", "neon");
//        Sequence<String> sExpected = this.createFromArgsRef("neon", "orange",
//                "grey", "black", "purple", "yellow", "green", "blue", "red");
        Sequence<String> sExpected = this.createFromArgsRef("red", "blue",
                "green", "yellow", "purple", "black", "grey", "orange", "neon");
        /*
         * Call method under test
         */
        s.flip();
        sExpected.flip();

        assertEquals(sExpected, s);
    }
}
