package baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class p20438 {
    

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static StringTokenizer st;

    private static int[] sum;

    private static boolean[] cannotInvite, willInvite, invite;

    private static int N, K, Q, M;

    public static void main(String[] args) throws Exception {

        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        sum = new int[N + 3];
        cannotInvite = new boolean[N + 3];
        willInvite = new boolean[N + 3];
        invite = new boolean[N + 3];

        st = new StringTokenizer(br.readLine());
        for (int k = 0; k < K; k ++) {
            cannotInvite[Integer.parseInt(st.nextToken())] = true;
        }

        st = new StringTokenizer(br.readLine());
        for (int q = 0; q < Q; q ++) {
            willInvite[Integer.parseInt(st.nextToken())] = true;
        }

        for (int i = 3; i < N + 3; i ++) {
            if (cannotInvite[i] || !willInvite[i]) continue;

            for (int r = i; r < N + 3; r += i) {
                if (cannotInvite[r]) continue;
                invite[r] = true;
            }
        }

        for (int i = 3; i < N + 3; i ++) {
            sum[i] = sum[i - 1] + (invite[i] ? 0 : 1);
        }


        for (int m = 0; m < M; m ++) {
            st = new StringTokenizer(br.readLine());

            int S = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());

            bw.write(sum[E] - sum[S - 1] + "\n");
        }

        bw.flush();
    }

}
