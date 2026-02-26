package p14002;

import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static StringTokenizer st;

    private static int N, lisLength, lisIdx;

    private static int[] A, answer;

    private static int[][] dp;
    
    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(br.readLine());

        A = new int[N];
        dp = new int[N][2];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i ++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        lisLength = dp[0][0];
        lisIdx = 0;

        for (int i = 0; i < N; i ++) {
            for (int r = (i + 1); r < N; r ++) {
                if (A[i] >= A[r]) continue;
                if ((dp[i][0] + 1) < dp[r][0]) continue;
                dp[r][0] = dp[i][0] + 1;
                dp[r][1] = i;
                if (dp[r][0] < lisLength) continue;
                lisLength = dp[r][0];
                lisIdx = r;
            }
        }

        lisLength ++;

        answer = new int[lisLength];
        int ptr = lisLength - 1;
        int next = lisIdx;

        while (ptr >= 0) {
            answer[ptr --] = A[next];
            next = dp[next][1];
        }

        bw.write(lisLength + "\n");
        for (int i = 0; i < lisLength; i ++) {
            bw.write(answer[i] + " ");
        }
        bw.write("\n");
        bw.flush();
    }
    
}
