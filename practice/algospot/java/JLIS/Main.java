package algospot.java.JLIS;
    
import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static StringTokenizer st;

    private static int C, N, M, A[], B[], cache[][];

    private static int bruteForce(int a, int b) {

        if (cache[a + 1][b + 1] != -1) return cache[a + 1][b + 1];
    
        int ret = 0;

        long next = Long.MIN_VALUE;
        if (0 <= a) next = Math.max(next, A[a]);
        if (0 <= b) next = Math.max(next, B[b]);
    
        for (int ia = a + 1; ia < N; ia ++) {
            if (A[ia] <= next) continue;
            cache[a + 1][b + 1] = ret = Math.max(ret, bruteForce(ia, b) + 1);
        }

        for (int ib = b + 1; ib < M; ib ++) {
            if (B[ib] <= next) continue;
            cache[a + 1][b + 1] = ret = Math.max(ret, bruteForce(a, ib) + 1);
        }
        return ret;
    }


    public static void main(String[] args) throws Exception {
        C = Integer.parseInt(br.readLine());

        for (int c = 0; c < C; c ++) {
            st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            cache = new int[101][101];
            for (int i = 0; i < 101; i ++) {
                Arrays.fill(cache[i], -1);
            }

            A = new int[N]; B = new int[M];

            st = new StringTokenizer(br.readLine());
            for (int a = 0; a < N; a ++) {
                A[a] = Integer.parseInt(st.nextToken());
            }
            st = new StringTokenizer(br.readLine());
            for (int b = 0; b < M; b ++) {
                B[b] = Integer.parseInt(st.nextToken());
            }

            bw.write(bruteForce(-1, -1) + "\n");
            
        }
        bw.flush();
    }
}
