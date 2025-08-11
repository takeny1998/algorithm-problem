package baekjoon;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class p1107 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static boolean[] buttons = new boolean[10];
    static int N, M, minCnt = Integer.MAX_VALUE;

    static void find(String str) {
        for (int num = 0; num < 10; num ++) {
            if (!buttons[num]) continue;

            String newStr = str + num;
            int channel = Integer.parseInt(newStr);
            minCnt = Math.min(minCnt, Math.abs(N - channel) + newStr.length());

            if (str.length() < 6)
                find(newStr);
        }
    }

    public static void main(String[] args) throws IOException {
        Arrays.fill(buttons, true);
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());


        if (M > 0) {
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i ++) {
                int index = Integer.parseInt(st.nextToken());
                buttons[index] = false;
            }
        }

        if (N == 100) {
            System.out.println(0);
            return;
        }
        minCnt = Math.min(minCnt, Math.abs(N - 100));

        find("");

        System.out.println(minCnt);
    }
}
