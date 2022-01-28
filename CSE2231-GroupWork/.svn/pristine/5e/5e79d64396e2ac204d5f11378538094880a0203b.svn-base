import javax.sound.midi.Sequence;

import components.sequence.Sequence1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Implements method to smooth a {@code Sequence<Integer>}.
 *
 * @author Put your name here
 *
 */
public final class SequenceSmoothHW {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private SequenceSmoothHW() {
    }

    /**
     * Returns the integer average of two given {@code int}s.
     *
     * @param j
     *            the first of two integers to average
     * @param k
     *            the second of two integers to average
     * @return the integer average of j and k
     * @ensures average = (j+k)/2
     */
    public static int average(int j, int k) {

    }

    /**
     * Returns a sequence that is the average of the two numbers
     *
     * @param s1
     *            the sequence to smooth
     * @param s2
     *            the original sequence untouched
     * @return a new sequence that is the resulting sequence new variable that
     * @requires |s1| >= 1
     * @ensures <pre>
     * |s3| = |s1| - 1  and
     *  for all i, j: integer, a, b: string of integer
     *      where (s1 = a * <i> * <j> * b)
     *    (there exists c, d: string of integer
     *       (|c| = |a|  and
     *        s3 = c * <(i+j)/2> * d))
     * </pre>
     */
    public static Sequence<Integer> smooth1(Sequence<Integer> s1,
            Sequence<Integer> s2) {
        assert s1 != null : "Violation of: s1 is not null";
        assert s2 != null : "Violation of: s2 is not null";
        assert s1 != s2 : "Violation of: s1 is not s2";
        assert s1.length() >= 1 : "Violation of: |s1| >= 1";

        int x = s1.length();
        System.out.println(x + "\n this is iterative");
        Sequence<Integer> s3 = s1.newInstance();

        if (x != 1) {
            for (int i = 0; i + 1 < x; i++) {
                int temp3;
                int temp1 = s1.entry(i);
                int temp2 = s1.entry(i + 1);
                if ((temp1 + temp2) > Integer.MAX_VALUE) {
                    temp1 = temp1 / 2;
                    temp2 = temp2 / 2;
                    temp3 = (temp1 + temp2) + 1;
                } else if ((temp1 - temp2) < Integer.MIN_VALUE) {
                    temp1 = temp1 / 2;
                    temp3 = (temp1 - temp2) - 1;
                } else {
                    temp3 = (int) (((long) temp1) + ((long) temp2)) / 2;
                }
                s3.add(0, temp3);
            }
        }
        return s3;
    }

    /**
     * Smooths a given {@code Sequence<Integer>}.
     *
     * @param s1
     *            the sequence to smooth
     * @param s2
     *            the resulting sequence
     * @replaces s2
     * @requires |s1| >= 1
     * @ensures <pre>
     * |s2| = |s1| - 1  and
     *  for all i, j: integer, a, b: string of integer
     *      where (s1 = a * <i> * <j> * b)
     *    (there exists c, d: string of integer
     *       (|c| = |a|  and
     *        s2 = c * <(i+j)/2> * d))
     * </pre>
     */
    public static Sequence<Integer> smooth2(Sequence<Integer> s1,
            Sequence<Integer> s2) {
        assert s1 != null : "Violation of: s1 is not null";
        assert s2 != null : "Violation of: s2 is not null";
        assert s1 != s2 : "Violation of: s1 is not s2";
        assert s1.length() >= 1 : "Violation of: |s1| >= 1";

        //Creates a new instance of a sequence
        Sequence<Integer> s3 = s1.newInstance();
        if (s1.length() != 1) {
            System.out.println("this is recursive");
            int temp1 = s1.remove(0);
            int temp2 = s1.entry(0);
            int temp3 = (temp1 + temp2) / 2;

            Sequence<Integer> s4 = smooth2(s1, s3);
            s3.add(0, s4);
            s1.add(0, temp1);
        }
        return s3;
    }

    public static void main(String[] args) {
        SimpleWriter out = new SimpleWriter1L();

        out.println("HelloWorld!");
        Sequence<Integer> s1 = new Sequence1L<>();
        Sequence<Integer> s2 = new Sequence1L<>();

        //sequence 1
        //  s1.add(0, Integer.MAX_VALUE);
        //  s1.add(1, Integer.MIN_VALUE);
        s1.add(0, 7);
        s1.add(1, 23);
        s1.add(2, 2);
        s1.add(3, 8);

        //sequence 2
        s2.add(0, 1);
        s2.add(1, 2);
        Sequence<Integer> s3 = new Sequence1L<>();
        s3 = smooth1(s1, s2);
        System.out.println("s3: " + s3);
    }

}