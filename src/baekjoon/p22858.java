package baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class p22858 {
        
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static StringTokenizer st;

    private static int N, K;

    private static int[] S, D;

    public static void main(String[] args) throws Exception {

        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        S = new int[N + 1];
        st = new StringTokenizer(br.readLine());

        for (int i = 1; i <= N; i ++) {
            S[i] = Integer.parseInt(st.nextToken());
        }

        D = new int[N + 1];
        st = new StringTokenizer(br.readLine());

         for (int i = 1; i <= N; i ++) {
            D[i] = Integer.parseInt(st.nextToken());
        }

        for (int k = 0; k < K; k ++) {
            int[] curt = S.clone();

            for (int i = 1; i <= N; i ++) {
                S[D[i]] = curt[i];
            }
        }

        for (int i = 1; i <= N; i ++) {
            bw.write(S[i] + " ");
        }
        bw.write("\n");
        bw.flush();
    }
}

