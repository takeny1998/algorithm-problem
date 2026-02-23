import java.io.*;
import java.util.*;

public class p10451 {
    
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static StringTokenizer st;

    private static int T, N;

    private static int[] nums, visits;

    private static int answer;

    private static void dfs(int curt) {

        visits[curt] = -1;

        int next = nums[curt];

        if (visits[next] == 0) dfs(nums[curt]);
        else if (visits[next] == -1) answer ++;
        
        visits[curt] = 1;
    }

    public static void main(String[] args) throws Exception {
        T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t ++) {
            N = Integer.parseInt(br.readLine());
            
            nums = new int[N + 1];
            visits = new int[N + 1];
            answer = 0;

            st = new StringTokenizer(br.readLine());
            for (int n = 1; n <= N; n ++) {
                nums[n] = Integer.parseInt(st.nextToken());
            }
            for (int n = 1; n <= N; n ++) {
                if (visits[n] != 0) continue;
                dfs(n);
            }
            bw.write(answer + "\n");
        }

        bw.flush();
    }
}
