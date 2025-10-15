package baekjoon;

import java.io.*;
import java.util.*;

public class p11047 {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static StringTokenizer st;

    private static int N, K;

    private static int[] arr;

    public static void main(String[] args) throws Exception {

        st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new int[N];

        for (int i = 0; i < N; i ++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        int answer = 0;

        for (int i = N - 1; i >= 0; i --) {
            answer += (K / arr[i]);
            K -= (K / arr[i]) * arr[i];
        }

        bw.write(answer + "\n");
        bw.flush();
    }
}
