package baekjoon;

import java.io.*;
import java.util.*;

public class p14719 {
    
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
   
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
   
    private static StringTokenizer st;

    private static int H, W = 0;

    private static int[] blocks;

    public static void main(String[] args) throws Exception {

        st = new StringTokenizer(br.readLine());

        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());

        blocks = new int[W];

        st = new StringTokenizer(br.readLine());

        for (int n = 0; n < W; n ++) {
            blocks[n] = Integer.parseInt(st.nextToken());
        }

        int totalSize = 0;

        for (int i = 1; i < W; i ++) {

            int leftMax = 0;

            for (int l = i - 1; l >= 0; l --) {
                leftMax = Math.max(leftMax, blocks[l]);
            }

            int rightMax = 0;

            for (int r = i + 1; r < W; r ++) {
                rightMax = Math.max(rightMax, blocks[r]);
            }

            int size = Math.min(leftMax, rightMax) - blocks[i];

            if (size < 0) continue;
            totalSize += size;
        }

        bw.write(totalSize + "\n");
        bw.flush();
    }

}
