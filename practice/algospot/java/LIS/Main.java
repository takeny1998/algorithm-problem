package algospot.java.LIS;

import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static StringTokenizer st;

    private static int C, N, nums[], cache[];

    private static int lis2(int start) {
        if (cache[start] != -1) return cache[start];

        int result = 1;
        for (int i = start + 1; i < nums.length; i ++) {
            if (nums[start] >= nums[i]) continue;
            cache[start] = result = Math.max(result, lis2(i) + 1);
        }
        return result;
    }

    public static void main(String[] args) throws Exception {
        C = Integer.parseInt(br.readLine());

        for (int c = 0; c < C; c ++) {
            N = Integer.parseInt(br.readLine());
            nums = new int[N];
            cache = new int[N];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i ++) {
                nums[i] = Integer.parseInt(st.nextToken());
                cache[i] = -1;
            }

            int maxLen = 0;
            for (int i = 0; i < N; i ++) {
                maxLen = Math.max(maxLen, lis2(i));
            }
            bw.write(maxLen + "\n");
        }
        bw.flush();

    }
}
