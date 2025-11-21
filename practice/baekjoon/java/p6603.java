package baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class p6603 {
    

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static StringTokenizer st;

    private static int[] nums, answer;

    private static int k;


    private static void dfs(int i, int n) throws Exception {
        if (n == 6) {
            for (int r = 0; r < 6; r ++) {
                bw.write(answer[r] + " ");
            }
            bw.write("\n");
            return;
        }
        if (i == k) return;

        answer[n] = nums[i];
        dfs(i + 1, n + 1);
        dfs(i + 1, n);
    }
    

    public static void main(String[] args) throws Exception {

        
        while (true) {
            st = new StringTokenizer(br.readLine());
            k = Integer.parseInt(st.nextToken());

            if (k == 0) break;

            
            nums = new int[k];
            answer = new int[6];

            for (int i = 0; i < k; i ++) {
                nums[i] = Integer.parseInt(st.nextToken());
            }

            
            dfs(0, 0);
            bw.write("\n");
        }

        bw.flush();
    }
}
