package baekjoon;

import java.io.*;
import java.util.*;

public class p2751 {

    private static final BufferedReader br = new BufferedReader(
        new InputStreamReader(System.in)
    );

    private static final BufferedWriter bw = new BufferedWriter(
        new OutputStreamWriter(System.out)
    );

    private static int N;

    private static int[] nums;

    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(br.readLine());
        nums = new int[N];

        for (int i = 0; i < N; i ++) {
            nums[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(nums);

        for (int i = 0; i < N; i ++) {
            bw.write(nums[i] + "\n");
        }
        bw.flush();
    }
    
}
