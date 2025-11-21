package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class p11438 {
    static int[] depth;
    static int[][] parents;
    static int N, M;
    static int logN;

    static int getLogN() {
        int logN = 0;

        for (; logN < N; logN *= 2) {
        }

        return logN;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        System.out.println(getLogN());
    }
}
