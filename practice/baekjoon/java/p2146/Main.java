package p2146;

import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static Queue<int[]> queue;

    private static StringTokenizer st;

    private static int N, matrix[][], island[][], dist[][], n;
    
    private static final int DY[] = { -1, 0, 1, 0 }, DX[] = { 0, 1, 0, -1 };

    public static void main(String[] args) throws Exception {

        N = Integer.parseInt(br.readLine());

        matrix = new int[N][N];
        island = new int[N][N];

        for (int i = 0; i < N; i ++) {
            st = new StringTokenizer(br.readLine());
            for (int r = 0; r < N; r ++) {
                island[i][r] = Integer.parseInt(st.nextToken());
            }
        }

        n = 0;

        for (int i = 0; i < N; i ++) {
            for (int r = 0; r < N; r ++) {
                if (island[i][r] == 0 || matrix[i][r] > 0) continue;
                queue = new LinkedList<>();
            
                queue.add(new int[] { i, r });
                matrix[i][r] = ++ n;

                while (!queue.isEmpty()) {
                    int[] curt = queue.poll();

                    for (int d = 0; d < 4; d ++) {
                        int[] next = { curt[0] + DY[d], curt[1] + DX[d] };
                        
                        if (next[0] >= N || next[0] < 0 || next[1] >= N || next[1] < 0) continue;
                        if (matrix[next[0]][next[1]] != 0 || island[next[0]][next[1]] == 0) continue;

                        matrix[next[0]][next[1]] = n;
                        queue.add(next);
                    }
                }
            }
        }

        int answer = Integer.MAX_VALUE;

        for (int k = 1; k <= n; k ++) {
            queue = new LinkedList<>();
            dist = new int[N][N];

            for (int i = 0; i < N; i ++) {
                for (int r = 0; r < N; r ++) {
                    if (matrix[i][r] != k) continue;
                    queue.add(new int[] { i, r, 1 });
                    dist[i][r] = 1;
                }
            }

            
            while (!queue.isEmpty()) {
                int[] curt = queue.poll();
                for (int d = 0; d < 4; d ++) {
                    int[] next = { curt[0] + DY[d], curt[1] + DX[d], curt[2] + 1 };
                    
                    if (next[0] >= N || next[0] < 0 || next[1] >= N || next[1] < 0) continue;
                    if (dist[next[0]][next[1]] != 0) continue;
                    
                    dist[next[0]][next[1]] = next[2];

                    if (matrix[next[0]][next[1]] != 0 ) {
                        answer = Math.min(answer, curt[2] - 1);
                        continue;
                    }
                    queue.add(next);
                }
            }
        }
        bw.write(answer + "\n");
        bw.flush();
    }
}
