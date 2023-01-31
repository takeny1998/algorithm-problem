import java.util.Scanner;

public class baekjoon_2805 {
    static int N, M, tree_max;
    static int[] tree;

    static long cal_amount(int h) {
        long sum = 0;
        for (int i = 0; i < N; i ++) {
            if (tree[i] > h)
                sum += tree[i] - h;
        }
        return sum;
    }

    static int cut_tree() {
        int left = 0, right = tree_max, answer = 0, h;

        while (left <= right) {
            h = (left + right) / 2;

            if (cal_amount(h) >= M) {
                answer = h;
                left = h + 1;
            } else {
                right = h - 1;
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        tree = new int[N];
        tree_max = 0;

        for (int i = 0; i < N; i ++) {
            tree[i] = sc.nextInt();
            if (tree[i] > tree_max) {
                tree_max = tree[i];
            }
        }

        System.out.println(cut_tree());
    }
}
