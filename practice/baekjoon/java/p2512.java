package baekjoon;

import java.io.*;
import java.util.*;

public class p2512 {
    private static final BufferedReader br = new BufferedReader(
        new InputStreamReader(System.in)
    );

    private static final BufferedWriter bw = new BufferedWriter(
        new OutputStreamWriter(System.out)
    );

    private static StringTokenizer st;

    private static int[] numbers;

    private static int N, M, low = 0, high = -1;

    private static void parametricSearch() {
        
        while (low <= high) {
            int mid = (low + high) / 2,
                sum = 0;

            for (int i = 0; i < N; i ++) {
                sum += Math.min(numbers[i], mid);
            }

            if (sum <= M) low = mid + 1;
            else high = mid - 1;
        }
    }
    
    public static void main(String[] args) throws Exception {
        
        N = Integer.parseInt(br.readLine());
        numbers = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i ++) {
            numbers[i] = Integer.parseInt(st.nextToken());
            high = Math.max(high, numbers[i]);
        }

        M = Integer.parseInt(br.readLine());
        parametricSearch();

        bw.write(high + "\n");
        bw.flush();
    }
}
