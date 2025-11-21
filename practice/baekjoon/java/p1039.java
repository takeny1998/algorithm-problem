package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class p1039 {

    static int N, M, K;
    static int[][] dp;

    static String swap(String num, int a, int b) {
        StringBuilder sb = new StringBuilder(num);

        char temp = sb.charAt(a);
        sb.setCharAt(a, sb.charAt(b));
        sb.setCharAt(b, temp);

        return sb.toString();
    }

    static int recursion(int depth, String numStr) {
        int num = Integer.parseInt(numStr);
        if (depth == K) {
            return num;
        }

        int ret = dp[depth][num];

        if (ret != 0) {
            return ret;
        }

        ret = -1;
        for (int i = 0; i < M - 1; i ++) {
            for (int j = i + 1; j < M; j ++) {
                String swapStr = swap(numStr, i, j);
                if (swapStr.charAt(0) == '0') {
                    continue;
                }

                int nextRet = recursion(depth + 1, swapStr);
                if (nextRet > ret) {
                    ret = nextRet;
                }
            }
        }

        dp[depth][num] = ret;
        return ret;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        M = String.valueOf(N).split("").length;

        dp = new int[K][1000001];

        int answer = -1;
        answer = recursion(0,String.valueOf(N));

        System.out.println(answer);
    }
}
