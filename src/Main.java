import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {

//        int[] c = {0, 1, 2, 3, 5, 2, 1, 5, 3, 3, 1, 0};
        int[] c = {0, 1, 2, 3, 5, 2, 1, 5, 3, 3, 1, 0};
        long a = System.currentTimeMillis();
        System.out.println(Arrays.toString(couts(new int[c.length], c)));
        System.out.println(etapes(new ArrayList<>(), couts(new int[c.length], c)));
        System.out.println(System.currentTimeMillis()-a);
        a = System.currentTimeMillis();
        System.out.println(Arrays.toString(constructCostTable(c)));
        System.out.println(etapesTable(new ArrayList<>(), c));
        System.out.println(System.currentTimeMillis()-a);
    }

    static int[] couts(int[] T, int[] c) {
        T[0] = 0;
        T[1] = T[0] + c[1];
        T[2] = Math.min(T[0], T[1]) + c[2];
        for (int i = 3; i < c.length; i++) {
            T[i] = Math.min(T[i - 1], Math.min(T[i - 2], T[i - 3])) + c[i];
        }
        return T;
    }

    static int[] constructCostTable(int[] c) {
        int[] table = new int[c.length];
        for (int i = 0; i < c.length; i++) {
            table[i] = T(i, c);
        }
        return table;
    }

    //recursive
    static int T(int i, int[] c) {
        if (i == 1) return T(i-1, c) + c[i];
        if (i == 2) return Math.min(T(i-1, c), T(i-2, c)) + c[i];
        if (i > 2) return Math.min(T(i-1, c), Math.min(T(i-2, c), T(i-3, c))) + c[i];
        return c[0];
    }

    static List<Integer> etapes(List<Integer> e, int[] T) {
        for (int i = T.length; i > 0 ;) {
            if (T[i - 1] > T[i - 2]) {
                if (T[i - 2] > T[i - 3]) {
                    e.add(i - 3);
                    i -= 3;
                } else {
                    e.add(i - 2);
                    i -= 2;
                }
            } else {
                if (T[i - 1] > T[i - 3]) {
                    e.add(i - 3);
                    i -= 3;
                } else {
                    e.add(i - 1);
                    i--;
                }
            }
        }
        return e;
    }

    //recursive
    static List<Integer> etapesTable(List<Integer> e, int [] c) {
        for (int i = c.length; i > 0 ;) {
            if (T(i - 1, c) > T(i - 2, c)) {
                if (T(i - 2, c) > T(i - 3, c)) {
                    e.add(i - 3);
                    i -= 3;
                } else {
                    e.add(i - 2);
                    i -= 2;
                }
            } else {
                if (T(i - 1, c) > T(i - 3, c)) {
                    e.add(i - 3);
                    i -= 3;
                } else {
                    e.add(i - 1);
                    i--;
                }
            }
        }
        return e;
    }
}
