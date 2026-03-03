package p16927;

import java.io.*;
import java.util.*;

public class Main {
    
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static StringTokenizer st;

    private static int N, M;

    private static int[][] matrix, answer, ptrs;

    private static long R;

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Long.parseLong(st.nextToken());

        matrix = new int[N][M];
        answer = new int[N][M];

        // ptrs[i][N][M][(N * 2) + (M * 2) - 4]
        ptrs = new int[Math.min(N, M) / 2][3];

        int n = N, m = M;
        for (int i = 0; i < ptrs.length; i ++) {
            ptrs[i][0] = n;
            ptrs[i][1] = m;
            ptrs[i][2] = (n * 2) + (m * 2) - 4;
            
            n -= 2; m -= 2;
            // bw.write(Arrays.toString(ptrs[i]) + "\n");
        }

        for (n = 0; n < N; n ++) {
            st = new StringTokenizer(br.readLine());
            for (m = 0; m < M; m ++) {
                matrix[n][m] = Integer.parseInt(st.nextToken());
                answer[n][m] = matrix[n][m];
            }
        }

        for (int i = 0; i < ptrs.length; i ++) {
            
            long r = R % ((long) ptrs[i][2]);
            int count = 0;

            Queue<Integer> beforeR = new ArrayDeque<>();
            Queue<Integer> afterR = new ArrayDeque<>();
            
            for (m = i; m < (i + ptrs[i][1]); m ++) {
                n = i;
                if ((count ++) < r) beforeR.add(matrix[n][m]);
                else afterR.add(matrix[n][m]);
            }
            for (n = i + 1; n < (i + ptrs[i][0]); n ++) {
                m = i + ptrs[i][1] - 1;
                if ((count ++) < r) beforeR.add(matrix[n][m]);
                else afterR.add(matrix[n][m]);
            }
            for (m = (i + ptrs[i][1] - 2); m >= i; m --) {
                n = i + ptrs[i][0] - 1;
                if ((count ++) < r) beforeR.add(matrix[n][m]);
                else afterR.add(matrix[n][m]);
            }
            for (n = (i + ptrs[i][0] - 2); n >= i + 1; n --) {
                m = i;
                if ((count ++) < r) beforeR.add(matrix[n][m]);
                else afterR.add(matrix[n][m]);
            }

            // bw.write(r + " " + beforeR + " " + afterR + "\n");
            
            for (m = i; m < (i + ptrs[i][1]); m ++) {
                n = i;
                if (!afterR.isEmpty()) answer[n][m] = afterR.poll();
                else answer[n][m] = beforeR.poll();
            }
            for (n = i + 1; n < (i + ptrs[i][0]); n ++) {
                m = i + ptrs[i][1] - 1;
                if (!afterR.isEmpty()) answer[n][m] = afterR.poll();
                else answer[n][m] = beforeR.poll();
            }
            for (m = (i + ptrs[i][1] - 2); m >= i; m --) {
                n = i + ptrs[i][0] - 1;
                if (!afterR.isEmpty()) answer[n][m] = afterR.poll();
                else answer[n][m] = beforeR.poll();
            }
            for (n = (i + ptrs[i][0] - 2); n >= i + 1; n --) {
                m = i;
                if (!afterR.isEmpty()) answer[n][m] = afterR.poll();
                else answer[n][m] = beforeR.poll();
            }
        }

        
        for (n = 0; n < N; n ++) {
            for (m = 0; m < M; m ++) {
                bw.write(answer[n][m] + "");
                if (m < (M - 1)) bw.write(" ");
            }
            bw.write("\n");
        }

        bw.flush();
        
    }

}
