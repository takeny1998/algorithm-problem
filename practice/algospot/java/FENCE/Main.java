package algospot.java.FENCE;

import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static StringTokenizer st;

    private static int C, N;

    private static int[] heights;

    private static int dfs(int left, int right) {
        
        if (left == right) return heights[left];

        int mid = (left + right) / 2;

        int result = Math.max(dfs(left, mid), dfs(mid + 1, right));
        
        int low = mid; int high = mid + 1;

        int height = Math.min(heights[low], heights[high]);
        result = Math.max(result, height * 2);

        while(left < low || high < right) {

            if (high < right && (low == left || heights[high + 1] >= heights[low - 1])) {
                height = Math.min(height, heights[++ high]);
            } else {
                height = Math.min(height, heights[-- low]);
            }
        
            result = Math.max(result, (high - low + 1) * height);
        }

        return result;
    }

    public static void main(String[] args) throws Exception {
        C = Integer.parseInt(br.readLine());

        for (int c = 0; c < C; c ++) {
            N = Integer.parseInt(br.readLine());
            heights = new int[N];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i ++) {
                heights[i] = Integer.parseInt(st.nextToken());                
            }
            bw.write(dfs(0, N - 1) + "\n");
        }
        bw.flush();
    }
}
