package baekjoon;

import java.io.*;
import java.util.*;

public class p21921 {
    
    private static final BufferedReader br = new BufferedReader(
        new InputStreamReader(System.in)
    );

    private static final BufferedWriter bw = new BufferedWriter(
        new OutputStreamWriter(System.out)
    );

    private static StringTokenizer st;

    private static int N, X, maxVisit = 0, maxCnt = 1;

    private static int[] visits;

    private static void slidingWindow() {
        int left = 0, right = 0;
        
        int sum = visits[0];

        while (left <= right) {

            if (sum >= maxVisit) {
                if (sum == maxVisit) maxCnt ++;
                else maxCnt = 1;
                maxVisit = sum;
            }

            int size = right - left + 1;

            if (size < X && right < (N - 1)) sum += visits[++ right];
            else sum -= visits[left ++];
        }
    }

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        visits = new int[N];
        
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i ++) {
            visits[i] = Integer.parseInt(st.nextToken());
        }

        slidingWindow();
        
        if (maxVisit == 0) {
            bw.write("SAD\n");
        } else {
            bw.write(maxVisit + "\n");
            bw.write(maxCnt + "\n");
        }

        bw.flush();
    }

}
