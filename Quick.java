import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Quick {
    private Quick() {
    }

    public static void sort(Comparable<String>[] a) {
        StdRandom.shuffle(a);
        sort(a, 0, a.length - 1);
        assert isSorted(a);
    }

    private static void sort(Comparable<String>[] a, int lo, int hi) {
        if (hi <= lo) return;
        int j = partition(a, lo, hi);
        sort(a, lo, j - 1);
        sort(a, j + 1, hi);
        assert isSorted(a, lo, hi);
    }

    private static int partition(Comparable<String>[] a, int lo, int hi) {
        int i = lo;
        int j = hi + 1;
        //pick a partioning value
        Comparable<String> v = a[lo];
        while (true) {
            while (less(a[++i], v)) {
                if (i == hi) break;
            }
            while (less(v, a[--j])) {
                if (j == lo) break;
            }
            if (i >= j) break;
            exch(a, i, j);
        }
        exch(a, lo, j);
        return j;
    }

    public static Comparable<String> select(Comparable<String>[] a, int k) {
        if (k < 0 || k >= a.length) {
            throw new IllegalArgumentException("index is not between 0 and " + a.length + ": " + k);
        }
        int lo = 0, hi = a.length - 1;
        StdRandom.shuffle(a);
        while (hi > lo) {
            int i = partition(a, lo, hi);
            if (i > k) hi = i - 1;
            else if (i < k) lo = i + 1;
            else return a[i];
        }
        return a[lo];
    }

    private static void exch(Object[] a, int i, int j) {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    public static boolean less(Comparable<String> v, Comparable<String> w) {
        if (v == w) return false;
        return (v.compareTo((String) w) < 0);
    }

    private static boolean isSorted(Comparable<String>[] a) {
        return isSorted(a, 0, a.length - 1);
    }

    private static void show(Comparable<String>[] a) {
        for (Comparable<String> i : a) {
            StdOut.println(i);
        }
    }

    private static boolean isSorted(Comparable<String>[] a, int lo, int hi) {
        for (int i = lo + 1; i < a.length; i++) {
            if (less(a[i], a[i - 1])) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        String[] a = StdIn.readAllStrings();
        Quick.sort(a);
        show(a);
        assert isSorted(a);
        StdRandom.shuffle(a);
        StdOut.println();
        for (int i = 0; i < a.length; i++) {
            String ith = (String) Quick.select(a, i);
            StdOut.println(ith);
        }
    }
}
