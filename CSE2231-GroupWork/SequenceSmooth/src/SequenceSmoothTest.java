import static org.junit.Assert.assertEquals;

import javax.sound.midi.Sequence;

import org.junit.Test;

import components.sequence.Sequence1L;

/**
 * Sample JUnit test fixture for SequenceSmooth.
 *
 * @author Tony Liou
 *
 */
public final class SequenceSmoothTest {

    /**
     * Constructs and returns a sequence of the integers provided as arguments.
     *
     * @param args
     *            0 or more integer arguments
     * @return the sequence of the given arguments
     * @ensures createFromArgs= [the sequence of integers in args]
     */
    private Sequence<Integer> createFromArgs(Integer... args) {
        Sequence<Integer> s = new Sequence1L<Integer>();
        for (Integer x : args) {
            s.add(s.length(), x);
        }
        return s;
    }

    /**
     * Test smooth with s1 = <2, 56, 74, 787, 46, 8, 9, 38> and s2 = <432, 76,
     * 23, 7, 3, 876, 234, 876, 54325463, 56>.
     */
    @Test
    public void routineTest() {
        /*
         * rountine because numbers are not too big or small
         */
        Sequence<Integer> seq1 = this.createFromArgs(2, 56, 74, 787, 46, 8, 9,
                38);
        Sequence<Integer> expectedSeq1 = this.createFromArgs(2, 56, 74, 787, 46,
                8, 9, 38);
        Sequence<Integer> seq2 = this.createFromArgs(432, 76, 23, 7, 3, 876,
                234, 876, 54325463, 56);
        Sequence<Integer> expectedSeq2 = this.createFromArgs(29, 65, 430, 416,
                27, 8, 23);
        SequenceSmooth.smooth(seq1, seq2);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expectedSeq1, seq1);
        assertEquals(expectedSeq2, seq2);
    }

    /**
     * Test smooth with s1 = <Integer.MIN_VALUE, Integer.MAX_VALUE> and s2 =
     * <13, 17, 11>.
     */
    @Test
    public void edgeTest() {
        /*
         * edge case because it is what integer can handle
         */
        Sequence<Integer> seq1 = this.createFromArgs(Integer.MIN_VALUE,
                Integer.MAX_VALUE);
        Sequence<Integer> expectedSeq1 = this.createFromArgs(Integer.MIN_VALUE,
                Integer.MAX_VALUE);
        Sequence<Integer> seq2 = this.createFromArgs(13, 17, 11);
        Sequence<Integer> expectedSeq2 = this.createFromArgs(0);
        SequenceSmooth.smooth(seq1, seq2);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expectedSeq1, seq1);
        assertEquals(expectedSeq2, seq2);
    }

    /**
     * Test smooth with s1 = <6,8,6,9,18,21> and s2 = <>.
     */
    @Test
    public void challengingTest() {
        /*
         * Chanlleging because there are prime numbers
         */
        Sequence<Integer> seq1 = this.createFromArgs(3, 10, 7, 6, 13, 24, 19);
        Sequence<Integer> expectedSeq1 = this.createFromArgs(3, 10, 7, 6, 13,
                24, 19);
        Sequence<Integer> seq2 = this.createFromArgs();
        Sequence<Integer> expectedSeq2 = this.createFromArgs(6, 8, 6, 9, 18,
                21);
        SequenceSmooth.smooth(seq1, seq2);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expectedSeq1, seq1);
        assertEquals(expectedSeq2, seq2);
    }

}