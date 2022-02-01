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
    public void testNoArgsConstructor1() {

        //empty constructor
        NaturalNumber test = this.constructorTest();
        NaturalNumber expected = this.constructorRef();

        //  Assert that values of variables match expectations

        assertEquals(expected, test);
    }

    @Test
    public void testNoArgsConstructor2() {

        //empty string

        NaturalNumber test = this.constructorTest("");
        NaturalNumber expected = this.constructorRef("");

        //  Assert that values of variables match expectations

        assertEquals(expected, test);
    }

    @Test
    public void testNoArgsConstructor3() {

        //int zero

        NaturalNumber test = this.constructorTest(0);
        NaturalNumber expected = this.constructorRef(0);

        //  Assert that values of variables match expectations

        assertEquals(expected, test);
    }

    /*
     * Test cases for the 2nd constructor with int value
     */
    @Test
    public void testIntConstructor1() {

        //  Set up variables and call method under test

        NaturalNumber test = this.constructorTest();
        NaturalNumber expected = this.constructorRef();

        //  Assert that values of variables match expectations

        assertEquals(expected, test);
    }

    @Test
    public void testIntConstructor2() {

        //  Set up variables and call method under test

        NaturalNumber test = this.constructorTest(0);
        NaturalNumber expected = this.constructorRef(0);

        //  Assert that values of variables match expectations

        assertEquals(expected, test);
    }

    @Test
    public void testIntConstructor3() {

        //  Set up variables and call method under test

        NaturalNumber test = this.constructorTest("");
        NaturalNumber expected = this.constructorRef("");

        //  Assert that values of variables match expectations

        assertEquals(expected, test);
    }

    /*
     * Test cases for the 3rd constructor with String value
     */
    @Test
    public void testStringConstructor1() {

        //  Set up variables and call method under test

        NaturalNumber test = this.constructorTest();
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

        NaturalNumber test = this.constructorTest("");
        NaturalNumber expected = this.constructorRef("");

        //  Assert that values of variables match expectations

        assertEquals(expected, test);
    }

    /*
     * Test cases for the 3rd constructor with String value
     */
    @Test
    public void testStringConstructor3() {

        //  Set up variables and call method under test

        NaturalNumber test = this.constructorTest(0);
        NaturalNumber expected = this.constructorRef(0);

        //  Assert that values of variables match expectations

        assertEquals(expected, test);
    }

    /*
     * Test cases for the 4th constructor with NaturalNumber value
     */
    @Test
    public void testNNConstructor1() {

        //  Set up variables and call method under test
        NaturalNumber x = new NaturalNumber1L();
        NaturalNumber test = this.constructorTest(x);
        NaturalNumber expected = this.constructorRef(x);

        //  Assert that values of variables match expectations

        assertEquals(expected, test);
    }

    @Test
    public void testNNConstructor2() {

        //  Set up variables and call method under test
        NaturalNumber x = new NaturalNumber1L("0");
        NaturalNumber test = this.constructorTest(x);
        NaturalNumber expected = this.constructorRef(x);

        //  Assert that values of variables match expectations

        assertEquals(expected, test);
    }

    @Test
    public void testNNConstructor3() {

        //  Set up variables and call method under test
        NaturalNumber x = new NaturalNumber1L(0);
        NaturalNumber test = this.constructorTest(x);
        NaturalNumber expected = this.constructorRef(x);

        //  Assert that values of variables match expectations

        assertEquals(expected, test);
    }

    /*
     * Test cases for the multiplyBy10 method. 1: testing with double digit int
     * numbers. 2: testing with large digit int numbers 3: testing with int max
     * value 4: testing with int zero 5: testing with string zero 6: testing
     * with
     */
    @Test
    public void testMultiBy10_1() {

        //  Set up variables and call method under test
        NaturalNumber test = this.constructorTest(10);
        NaturalNumber expected = this.constructorRef(10);

        test.multiplyBy10(0);
        expected.multiplyBy10(0);

        //  Assert that values of variables match expectations

        assertEquals(expected, test);
    }

    /*
     * Test cases for the multiplyBy10 method
     */
    @Test
    public void testMultiBy10_2() {

        //  Set up variables and call method under test
        NaturalNumber test = this.constructorTest(123456);
        NaturalNumber expected = this.constructorRef(123456);

        //  Assert that values of variables match expectations

        test.multiplyBy10(0);
        expected.multiplyBy10(0);

        assertEquals(expected, test);
    }

    /*
     * Test cases for the multiplyBy10 method
     */
    @Test
    public void testMultiBy10_3() {

        //  Set up variables and call method under test
        int maxVal = Integer.MAX_VALUE;
        NaturalNumber test = this.constructorTest(maxVal);
        NaturalNumber expected = this.constructorRef(maxVal);

        //  Assert that values of variables match expectations

        test.multiplyBy10(0);
        expected.multiplyBy10(0);

        assertEquals(expected, test);
    }

    /*
     * Test cases for the multiplyBy10 method
     */
    @Test
    public void testMultiBy10_4() {

        //  Set up variables and call method under test
        NaturalNumber test = this.constructorTest(0);
        NaturalNumber expected = this.constructorRef(0);

        //  Assert that values of variables match expectations

        test.multiplyBy10(0);
        expected.multiplyBy10(0);
        assertEquals(expected, test);
    }

    /*
     * Test cases for the multiplyBy10 method
     */
    @Test
    public void testMultiBy10_5() {

        //  Set up variables and call method under test
        NaturalNumber test = this.constructorTest("0");
        NaturalNumber expected = this.constructorRef("0");

        //  Assert that values of variables match expectations

        test.multiplyBy10(0);
        expected.multiplyBy10(0);

        assertEquals(expected, test);
    }

    /*
     * Test cases for the multiplyBy10 method
     */
    @Test
    public void testMultiBy10_6() {

        //  Set up variables and call method under test
        NaturalNumber test = this.constructorTest("1");
        NaturalNumber expected = this.constructorRef("1");

        NaturalNumber test2 = this.constructorTest(test);
        NaturalNumber expected2 = this.constructorTest(expected);

        //  Assert that values of variables match expectations

        test2.multiplyBy10(0);
        expected2.multiplyBy10(0);

        assertEquals(expected, test);
    }

    /*
     * Test cases for the divideBy10 method
     */
    @Test
    public void testDivBy10_1() {

        //Call divideBy10 method using max int value
        int maxVal = Integer.MAX_VALUE;
        NaturalNumber test = this.constructorTest(maxVal);
        NaturalNumber expected = this.constructorRef(maxVal);

        //  Assert that values of variables match expectations

        int x = test.divideBy10();
        int y = expected.divideBy10();

        assertEquals(expected, test);
        assertEquals(y, x);

    }

    @Test
    public void testDivBy10_2() {

        //Call divideBy10 method using int zeros
        NaturalNumber test = this.constructorTest(0);
        NaturalNumber expected = this.constructorRef(0);

        //  Assert that values of variables match expectations

        int x = test.divideBy10();
        int y = expected.divideBy10();

        assertEquals(expected, test);
        assertEquals(y, x);
    }

    @Test
    public void testDivBy10_3() {

        //Call divideBy10 method using large strings
        NaturalNumber test = this.constructorTest("98765432123456789");
        NaturalNumber expected = this.constructorRef("98765432123456789");

        //  Assert that values of variables match expectations

        int x = test.divideBy10();
        int y = expected.divideBy10();

        assertEquals(expected, test);
        assertEquals(y, x);
    }

    @Test
    public void testDivBy10_4() {

        //  Set up variables and call method under test
        NaturalNumber test = this.constructorTest(00);
        NaturalNumber expected = this.constructorRef(00);

        //  Assert that values of variables match expectations

        int x = test.divideBy10();
        int y = expected.divideBy10();

        assertEquals(expected, test);
        assertEquals(y, x);
    }

    /*
     * Test cases for the divideBy10 method using NaturalNumber
     */
    @Test
    public void testDivBy10_5() {

        //Call divideBy10 method using NaturalNumber
        NaturalNumber test = this.constructorTest(10);
        NaturalNumber expected = this.constructorRef(10);

        NaturalNumber test2 = this.constructorTest(test);
        NaturalNumber expected2 = this.constructorRef(expected);

        //  Assert that values of variables match expectations

        int x = test2.divideBy10();
        int y = expected2.divideBy10();

        assertEquals(expected2, test2);
        assertEquals(y, x);
    }

}
