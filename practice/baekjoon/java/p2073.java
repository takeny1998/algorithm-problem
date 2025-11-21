package baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class p2073 {
    
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static StringTokenizer st;

    private static int D, P;

    private static int[] L, C;

    private static int[] dp;

    public static void main(String[] args) throws Exception {

        st = new StringTokenizer(br.readLine());

        D = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        
        L = new int[P + 1];
        C = new int[P + 1];

        dp = new int[D + 1];

        for (int i = 1; i <= P; i ++) {
            st = new StringTokenizer(br.readLine());
            
            L[i] = Integer.parseInt(st.nextToken());
            C[i] = Integer.parseInt(st.nextToken());

        }

        dp[0] = Integer.MAX_VALUE;
        
        for (int i = 1; i <= P; i ++) {
            // 역방향으로 순회하는 이유?
            // - dp[j - L[i]] 로 현재 이전 인덱스에 접근함
            // - 만약 정방향이면, 현재 원소 i를 두 번 이상 사용할 수 있음
            for (int j = D; j >= L[i]; j --) {
                dp[j] = Math.max(dp[j], Math.min(dp[j - L[i]], C[i]));
            }
            System.out.println(Arrays.toString(dp));
        }

        bw.write(dp[D] + "\n");
        bw.flush();
    }
}
