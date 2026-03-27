package p6236;

import java.io.*;
import java.util.*;

public class Main {
    
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static StringTokenizer st;

    private static int N, M, MAX = 1_000_000_000;

    private static int[] array;

    private static int f(int x) {
        int m = 0, k = 0;

        for (int n = 0; n < N; n ++) {
            if (x < array[n]) return MAX;
            if (k < array[n]) {
                m ++; k = x;
            }
            k -= array[n];
        }
        
        return m;
    }
    
    public static void main(String[] args) throws Exception {
        
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        array = new int[N];
        for (int n = 0; n < N; n ++) {
            array[n] = Integer.parseInt(br.readLine());
        }

        int left = 1, right = MAX;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (f(mid) > M) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        bw.write(left + "\n");
        bw.flush();
        
    }
}
