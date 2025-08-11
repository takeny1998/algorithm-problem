package baekjoon;

import java.util.Scanner;

public class p2003 {
    static int N, M;
    static int[] A;

    static int find_sum() {
        int left = 0, right = 0, count = 0;

        while (right < N) {
            int sum = 0;
            for (int i = left; i <= right; i ++) {
                sum += A[i];
            }
            if (sum == M) {
                count ++; left ++; right ++;
            }
            if (sum < M) right ++;
            if (sum > M) left ++;

            if (left > right) right = left;
        }

        return count;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        A = new int[N];

        for (int i = 0; i < N; i ++) {
            A[i] = sc.nextInt();
        }

        System.out.println(find_sum());
    }
}
