import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber1L;

/**
 * JUnit test fixture for {@code NaturalNumber}'s constructors and kernel
 * methods.
 *
 * @author Tony Liou and James Park
 *
 */
public abstract class NaturalNumberTest {

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * implementation under test and returns the result.
     *
     * @return the new number
     * @ensures constructorTest = 0
     */
    protected abstract NaturalNumber constructorTest();

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * implementation under test and returns the result.
     *
     * @param i
     *            {@code int} to initialize from
     * @return the new number
     * @requires i >= 0
     * @ensures constructorTest = i
     */
    protected abstract NaturalNumber constructorTest(int i);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * implementation under test and returns the result.
     *
     * @param s
     *            {@code String} to initialize from
     * @return the new number
     * @requires there exists n: NATURAL (s = TO_STRING(n))
     * @ensures s = TO_STRING(constructorTest)
     */
    protected abstract NaturalNumber constructorTest(String s);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * implementation under test and returns the result.
     *
     * @param n
     *            {@code NaturalNumber} to initialize from
     * @return the new number
     * @ensures constructorTest = n
     */
    protected abstract NaturalNumber constructorTest(NaturalNumber n);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * reference implementation and returns the result.
     *
     * @return the new number
     * @ensures constructorRef = 0
     */
    protected abstract NaturalNumber constructorRef();

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * reference implementation and returns the result.
     *
     * @param i
     *            {@code int} to initialize from
     * @return the new number
     * @requires i >= 0
     * @ensures constructorRef = i
     */
    protected abstract NaturalNumber constructorRef(int i);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * reference implementation and returns the result.
     *
     * @param s
     *            {@code String} to initialize from
     * @return the new number
     * @requires there exists n: NATURAL (s = TO_STRING(n))
     * @ensures s = TO_STRING(constructorRef)
     */
    protected abstract NaturalNumber constructorRef(String s);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * reference implementation and returns the result.
     *
     * @param n
     *            {@code NaturalNumber} to initialize from
     * @return the new number
     * @ensures constructorRef = n
     */
    protected abstract NaturalNumber constructorRef(NaturalNumber n);

    // TODO - add test cases for four constructors, multiplyBy10, divideBy10, isZero

    /*
     * Test cases for the first empty constructor
     */
    @Test
    public void testNoArgsConstructor() {

        //  Set up variables and call method under test

        NaturalNumber test = this.constructorTest();
        NaturalNumber expected = this.constructorRef();

        //  Assert that values of variables match expectations

        assertEquals(expected, test);
    }

    /*
     * Test cases for the 2nd constructor with int value
     */
    @Test
    public void testIntConstructor1() {

        //  Set up variables and call method under test

        NaturalNumber test = this.constructorTest(0);
        NaturalNumber expected = this.constructorRef();

        //  Assert that values of variables match expectations

        assertEquals(expected, test);
    }

    /*
     * Test cases for the 2nd constructor with int value
     */
    @Test
    public void testIntConstructor2() {

        //  Set up variables and call method under test

        NaturalNumber test = this.constructorTest(3);
        NaturalNumber expected = this.constructorRef(3);

        //  Assert that values of variables match expectations

        assertEquals(expected, test);
    }

    /*
     * Test cases for the 3rd constructor with String value
     */
    @Test
    public void testStringConstructor1() {

        //  Set up variables and call method under test

        NaturalNumber test = this.constructorTest("0");
        NaturalNumber expected = this.constructorRef();

        //  Assert that values of variables match expectations

        assertEquals(expected, test);
    }

    /*
     * Test cases for the 3rd constructor with String value
     */
    @Test
    public void testStringConstructor2() {

        //  Set up variables and call method under test

        NaturalNumber test = this.constructorTest("9");
        NaturalNumber expected = this.constructorRef("9");

        //  Assert that values of variables match expectations

        assertEquals(expected, test);
    }

    /*
     * Test cases for the 3rd constructor with String value
     */
    @Test
    public void testStringConstructor3() {

        //  Set up variables and call method under test

        NaturalNumber test = this.constructorTest("");
        NaturalNumber expected = this.constructorRef("");

        //  Assert that values of variables match expectations

        assertEquals(expected, test);
    }

    /*
     * Test cases for the 4th constructor with NaturalNumber value
     */
    @Test
    public void testNNConstructor1() {

        //  Set up variables and call method under test
        NaturalNumber x = new NaturalNumber1L("0");
        NaturalNumber test = this.constructorTest(x);
        NaturalNumber expected = this.constructorRef(x);

        //  Assert that values of variables match expectations

        assertEquals(expected, test);
    }

    /*
     * Test cases for the 4th constructor with NaturalNumber value
     */
    @Test
    public void testNNConstructor2() {

        //  Set up variables and call method under test
        NaturalNumber x = new NaturalNumber1L("999999999999999999999");
        NaturalNumber test = this.constructorTest(x);
        NaturalNumber expected = this.constructorRef(x);

        //  Assert that values of variables match expectations

        assertEquals(expected, test);
    }

    /*
     * Test cases for the multiplyBy10 method
     */
    @Test
    public void testMultiBy10_1() {

        //  Set up variables and call method under test
        NaturalNumber test = this.constructorTest(0);
        NaturalNumber expected = this.constructorRef();

        //  Assert that values of variables match expectations

        assertEquals(expected, test);
    }

    /*
     * Test cases for the multiplyBy10 method
     */
    @Test
    public void testMultiBy10_2() {

        //  Set up variables and call method under test
        NaturalNumber test = this.constructorTest(1234567);
        NaturalNumber expected = this.constructorRef(1234567);

        //  Assert that values of variables match expectations

        assertEquals(expected, test);
    }

    /*
     * Test cases for the multiplyBy10 method
     */
    @Test
    public void testMultiBy10_3() {

        //  Set up variables and call method under test
        int y = Integer.MAX_VALUE;
        NaturalNumber test = this.constructorTest(y);
        NaturalNumber expected = this.constructorRef(y);

        //  Assert that values of variables match expectations

        assertEquals(expected, test);
    }

    /*
     * Test cases for the divideBy10 method
     */
    @Test
    public void testDivBy10_1() {

        //  Set up variables and call method under test
        int y = Integer.MAX_VALUE;
        NaturalNumber test = this.constructorTest(y);
        NaturalNumber expected = this.constructorRef(y);

        //  Assert that values of variables match expectations

        assertEquals(expected, test);
    }
}
