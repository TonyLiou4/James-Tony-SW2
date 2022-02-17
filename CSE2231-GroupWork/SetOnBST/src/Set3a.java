import java.util.Iterator;

import components.binarytree.BinaryTree;
import components.binarytree.BinaryTree1;
import components.set.Set;
import components.set.SetSecondary;

/**
 * {@code Set} represented as a {@code BinaryTree} (maintained as a binary
 * search tree) of elements with implementations of primary methods.
 *
 * @param <T>
 *            type of {@code Set} elements
 * @mathdefinitions <pre>
 * IS_BST(
 *   tree: binary tree of T
 *  ): boolean satisfies
 *  [tree satisfies the binary search tree properties as described in the
 *   slides with the ordering reported by compareTo for T, including that
 *   it has no duplicate labels]
 * </pre>
 * @convention IS_BST($this.tree)
 * @correspondence this = labels($this.tree)
 *
 * @author Sungwoon Park and Tony Liou
 *
 */
public class Set3a<T extends Comparable<T>> extends SetSecondary<T> {

    /*
     * Private members --------------------------------------------------------
     */

    /**
     * Elements included in {@code this}.
     */
    private BinaryTree<T> tree;

    /**
     * Returns whether {@code x} is in {@code t}.
     *
     * @param <T>
     *            type of {@code BinaryTree} labels
     * @param t
     *            the {@code BinaryTree} to be searched
     * @param x
     *            the label to be searched for
     * @return true if t contains x, false otherwise
     * @requires IS_BST(t)
     * @ensures isInTree = (x is in labels(t))
     */
    private static <T extends Comparable<T>> boolean isInTree(BinaryTree<T> t,
            T x) {
        assert t != null : "Violation of: t is not null";
        assert x != null : "Violation of: x is not null";

        // initial value for the return boolean value
        boolean isIn = false;

        // create left and right tree
        BinaryTree<T> lt = t.newInstance();
        BinaryTree<T> rt = t.newInstance();

        if (t.size() != 0) {
            T root = t.disassemble(lt, rt);
            // if the root equals x, set isIn to true since it is in the tree
            // if (root.equals(x)) {
            //     isIn = true;
            // }
            // if not recursively call the isInTree method to check
            isIn = root.equals(x);
            if (!isIn) {
                isIn = isInTree(lt, x);
                if (!isIn) {
                    isIn = isInTree(rt, x);
                }
            }
            t.assemble(root, lt, rt);
        }
        return isIn;
    }

    /**
     * Inserts {@code x} in {@code t}.
     *
     * @param <T>
     *            type of {@code BinaryTree} labels
     * @param t
     *            the {@code BinaryTree} to be searched
     * @param x
     *            the label to be inserted
     * @aliases reference {@code x}
     * @updates t
     * @requires IS_BST(t) and x is not in labels(t)
     * @ensures IS_BST(t) and labels(t) = labels(#t) union {x}
     */
    private static <T extends Comparable<T>> void insertInTree(BinaryTree<T> t,
            T x) {
        assert t != null : "Violation of: t is not null";
        assert x != null : "Violation of: x is not null";

        // create left and right tree
        BinaryTree<T> lt = t.newInstance();
        BinaryTree<T> rt = t.newInstance();

        // takes care of the non-empty tree
        if (t.size() != 0) {
            T root = t.disassemble(lt, rt);

            if (x.compareTo(root) < 0) {
                // insert into left tree
                insertInTree(lt, x);
            } else {
                // insert into right tree
                insertInTree(rt, x);
            }
            t.assemble(root, lt, rt);
        } else {
            // takes care of empty tree so resulting a root node only
            t.assemble(x, lt, rt);
        }
    }

    /**
     * Removes and returns the smallest (left-most) label in {@code t}.
     *
     * @param <T>
     *            type of {@code BinaryTree} labels
     * @param t
     *            the {@code BinaryTree} from which to remove the label
     * @return the smallest label in the given {@code BinaryTree}
     * @updates t
     * @requires IS_BST(t) and |t| > 0
     * @ensures <pre>
     * IS_BST(t)  and  removeSmallest = [the smallest label in #t]  and
     *  labels(t) = labels(#t) \ {removeSmallest}
     * </pre>
     */
    private static <T> T removeSmallest(BinaryTree<T> t) {
        assert t != null : "Violation of: t is not null";
        assert t.size() > 0 : "Violation of: |t| > 0";

        BinaryTree<T> lt = t.newInstance();
        BinaryTree<T> rt = t.newInstance();

        T root = t.disassemble(lt, rt);
        T returnVal = root;

        // if the left tree is not empty, use recursion to get the smallest T value
        if (lt.size() != 0) {
            returnVal = removeSmallest(lt);
            t.assemble(root, lt, rt);
        } else {
            /*
             * if the left tree is empty, we want to remove the root and set the
             * right subtree to the original tree.
             */
            t.transferFrom(rt);
        }
        return returnVal;
    }

    /**
     * Finds label {@code x} in {@code t}, removes it from {@code t}, and
     * returns it.
     *
     * @param <T>
     *            type of {@code BinaryTree} labels
     * @param t
     *            the {@code BinaryTree} from which to remove label {@code x}
     * @param x
     *            the label to be removed
     * @return the removed label
     * @updates t
     * @requires IS_BST(t) and x is in labels(t)
     * @ensures <pre>
     * IS_BST(t)  and  removeFromTree = x  and
     *  labels(t) = labels(#t) \ {x}
     * </pre>
     */
    private static <T extends Comparable<T>> T removeFromTree(BinaryTree<T> t,
            T x) {
        assert t != null : "Violation of: t is not null";
        assert x != null : "Violation of: x is not null";
        assert t.size() > 0 : "Violation of: x is in labels(t)";

        BinaryTree<T> lt = t.newInstance();
        BinaryTree<T> rt = t.newInstance();

        T returnVal = null;

        T root = t.disassemble(lt, rt);
        if (root.equals(x)) {
            // set the return value to the root if it equals x
            returnVal = root;
            /*
             * if the right side of the tree is not empty, extract the smallest
             * value from the right tree and set it as the new root
             */
            if (rt.size() != 0) {
                T newRoot = removeSmallest(rt);
                t.assemble(newRoot, lt, rt);
            } else {
                // if not, set the new tree as the left tree
                t.transferFrom(lt);
            }
        } else {
            // if the root is larger than x
            if (x.compareTo(root) < 0) {
                T temp1 = removeFromTree(lt, x);
                returnVal = temp1;
            } else {
                // if the root is smaller than x
                T temp2 = removeFromTree(rt, x);
                returnVal = temp2;
            }
            t.assemble(root, lt, rt);
        }
        return returnVal;
    }

    /**
     * Creator of initial representation.
     */
    private void createNewRep() {
        this.tree = new BinaryTree1<T>();
    }

    /*
     * Constructors -----------------------------------------------------------
     */

    /**
     * No-argument constructor.
     */
    public Set3a() {
        this.createNewRep();
    }

    /*
     * Standard methods -------------------------------------------------------
     */

    @SuppressWarnings("unchecked")
    @Override
    public final Set<T> newInstance() {
        try {
            return this.getClass().getConstructor().newInstance();
        } catch (ReflectiveOperationException e) {
            throw new AssertionError(
                    "Cannot construct object of type " + this.getClass());
        }
    }

    @Override
    public final void clear() {
        this.createNewRep();
    }

    @Override
    public final void transferFrom(Set<T> source) {
        assert source != null : "Violation of: source is not null";
        assert source != this : "Violation of: source is not this";
        assert source instanceof Set3a<?> : ""
                + "Violation of: source is of dynamic type Set3<?>";
        /*
         * This cast cannot fail since the assert above would have stopped
         * execution in that case: source must be of dynamic type Set3a<?>, and
         * the ? must be T or the call would not have compiled.
         */
        Set3a<T> localSource = (Set3a<T>) source;
        this.tree = localSource.tree;
        localSource.createNewRep();
    }

    /*
     * Kernel methods ---------------------------------------------------------
     */

    @Override
    public final void add(T x) {
        assert x != null : "Violation of: x is not null";
        assert !this.contains(x) : "Violation of: x is not in this";

        insertInTree(this.tree, x);
    }

    @Override
    public final T remove(T x) {
        assert x != null : "Violation of: x is not null";
        assert this.contains(x) : "Violation of: x is in this";

        return removeFromTree(this.tree, x);
    }

    @Override
    public final T removeAny() {
        assert this.size() > 0 : "Violation of: this /= empty_set";

        return removeSmallest(this.tree);
    }

    @Override
    public final boolean contains(T x) {
        assert x != null : "Violation of: x is not null";

        return isInTree(this.tree, x);
    }

    @Override
    public final int size() {
        return this.tree.size();
    }

    @Override
    public final Iterator<T> iterator() {
        return this.tree.iterator();
    }

}
