import edu.princeton.cs.algs4.TST;
import edu.princeton.cs.algs4.StdOut;

public class CircularSuffixArray {
    private TST<Integer> tst;
    private String str;
    private int length;

    // circular suffix array of s
    public CircularSuffixArray(String s) {
        length = s.length();
        str = s;
        tst = new TST<Integer>();
        for (int i = 0; i < length; i++) {
            tst.put(ithString(s, i), i);
        }

        for (int i = 0; i < length(); i++)
            StdOut.println(String.format("index[%d]: %d", i, index(i)));
    }

    private String ithString(String s, int i) {
        return s.substring(i) + s.substring(0,i);
    }

    private String sortedIthString(int i) {
        int cnt = 0;
        for (String s : tst.keys()) {
            if (cnt == i) return s;
            cnt++;
        }
        return null;
    }

    // length of s
    public int length() {
        return length;
    }

    // returns index of ith sorted suffix
    public int index(int i) {
        return tst.get(sortedIthString(i));
    }

    // unit testing of the methods (optional)
    public static void main(String[] args) {
        CircularSuffixArray csa = new CircularSuffixArray("ABRACADABRA!");
    }
}