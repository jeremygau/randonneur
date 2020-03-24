import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        int[] c = {0, 1, 2, 3, 5, 2, 1, 5, 3, 3, 1, 0};
        System.out.println(Arrays.toString(couts(new int[12], c)));
        System.out.println(etapes(new ArrayList<>(), couts(new int[12], c)));

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
}
