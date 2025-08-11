package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class p2960 {
    static boolean[] visited = new boolean[1001];
    static int[] erased = new int[1001];
    static int N, K, ptr = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        for (int i = 2; i <= N; i ++) {
            int num = i;
            for (int r = i; r <= N; r += num) {
                if (!visited[r]) {
                    visited[r] = true;
                    erased[ptr ++] = r;
                }
            }
        }

        System.out.println(erased[K - 1]);
    }
}
