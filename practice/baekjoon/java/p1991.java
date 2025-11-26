
import java.util.Scanner;

public class p1991 {
    static int N;
    static String[][] tree = new String[27][3];

    static int find_child(String child) {
        for (int i = 0; i < N; i ++) {
            if (tree[i][0].equals(child)) {
                return i;
            }
        }
        return -1;
    }

    static void prefix_order(int index) {
        if (index < 0) return;
        if (index >= N) {
            System.out.print("\n");
            return;
        }

        System.out.printf("%s", tree[index][0]);
        prefix_order(find_child(tree[index][1]));
        prefix_order(find_child(tree[index][2]));
    }

    static void infix_order(int index) {
        if (index < 0) return;
        if (index >= N) {
            System.out.print("\n");
            return;
        }

        infix_order(find_child(tree[index][1]));
        System.out.printf("%s", tree[index][0]);
        infix_order(find_child(tree[index][2]));
    }

    static void postfix_order(int index) {
        if (index < 0) return;
        if (index >= N) {
            System.out.print("\n");
            return;
        }

        postfix_order(find_child(tree[index][1]));
        postfix_order(find_child(tree[index][2]));
        System.out.printf("%s", tree[index][0]);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();

        for (int i = 0; i < N; i ++) {
            for (int r = 0; r < 3; r ++) {
                tree[i][r] = sc.next();
            }
        }
        prefix_order(0);
        System.out.println();
        infix_order(0);
        System.out.println();
        postfix_order(0);
    }
}
