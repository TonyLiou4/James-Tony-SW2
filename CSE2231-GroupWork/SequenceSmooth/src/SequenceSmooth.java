import components.sequence.Sequence;
import components.sequence.Sequence1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Implements method to smooth a {@code Sequence<Integer>}.
 *
 * @author Put your name here
 *
 */
public final class SequenceSmooth {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private SequenceSmooth() {
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
    public static void smooth1(Sequence<Integer> s1, Sequence<Integer> s2) {
        assert s1 != null : "Violation of: s1 is not null";
        assert s2 != null : "Violation of: s2 is not null";
        assert s1 != s2 : "Violation of: s1 is not s2";
        assert s1.length() >= 1 : "Violation of: |s1| >= 1";

        //****** All these methods are instance methods
        int x = s1.length();
        s2.clear();
        System.out.println(x + "\n this is iterative");

        if (x != 1) {
            for (int i = 0; i + 1 < x; i++) {
                int temp1 = s1.entry(i);
                int temp2 = s1.entry(i + 1);
                int temp3 = (temp1 + temp2) / 2;
                s2.add(i, temp3);
            }
        }
        //we may use method: Entry
        // only use add, remove, length methods

        System.out.print(s1);
        System.out.print(s2);
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
    public static void smooth2(Sequence<Integer> s1, Sequence<Integer> s2) {
        assert s1 != null : "Violation of: s1 is not null";
        assert s2 != null : "Violation of: s2 is not null";
        assert s1 != s2 : "Violation of: s1 is not s2";
        assert s1.length() >= 1 : "Violation of: |s1| >= 1";

        //****** All these methods are instance methods

        s2.clear();
        int j = 0;
        if (s1.length() != 1) {
            System.out.println("this is recursive");
            int temp1 = s1.remove(0);
            int temp2 = s1.remove(0);
            int temp3 = (temp1 + temp2) / 2;
            //add value to s2

            smooth2(s1, s2);
            s2.add(0, temp3);
            s1.add(0, temp2);
            //restore s1
            //  s1.add(0, temp2);
        }
        //we may use method: Entry
        // only use add, remove, length methods

        System.out.print(s1);
        System.out.print(s2);
    }

    public static void main(String[] args) {
        SimpleWriter out = new SimpleWriter1L();

        out.println("HellloWorld!");
        Sequence<Integer> s1 = new Sequence1L<>();
        Sequence<Integer> s2 = new Sequence1L<>();

        //sequence 1
        //  s1.add(0, Integer.MAX_VALUE);
        //  s1.add(1, Integer.MIN_VALUE);
        s1.add(0, 7);
        s1.add(1, 23);
        //s1.add(2, 2);
        //s1.add(3, 8);

        //sequence 2
        s2.add(0, 1);
        s2.add(1, 2);
        smooth2(s1, s2);
    }

}