package baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

import java.util.ArrayDeque;

public class p14442 {

    private static class Element {

        private int y, x, k, d;

        Element(int y, int x, int k, int d) {
            this.y = y;
            this.x = x;
            this.k = k;
            this.d = d;
        }

    }
    
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static final int[] DY = { -1, 0, 1, 0 }, DX = { 0, 1, 0, -1 };

    private static StringTokenizer st;

    private static int N, M, K;

    private static int[][] matrix;

    private static int[][][] dist;

    private static boolean[][][] visited;

    private static void bfs() {
        Queue<Element> queue = new ArrayDeque<>();

        queue.add(new Element(0, 0, 0, 1));
        visited[0][0][0] = true;
        dist[0][0][0] = 1;

        while (!queue.isEmpty()) {

            Element curt = queue.poll();

            for (int d = 0; d < 4; d ++) {
                Element next = new Element(curt.y + DY[d], curt.x + DX[d], curt.k, curt.d + 1);

                if (next.y < 0 || next.y >= N || next.x < 0 || next.x >= M) continue;
                if (matrix[next.y][next.x] == 1) {
                    next.k ++;
                    if (next.k > K) continue;
                }
                if (visited[next.k][next.y][next.x]) continue;
                
                visited[next.k][next.y][next.x] = true;
                dist[next.k][next.y][next.x] = next.d;
                
                queue.add(next);
            }
        }
    }

    public static void main(String[] args) throws Exception {

        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        dist = new int[K + 1][N][M];

        for (int k = 0; k <= K; k ++) {
            for (int[] arr : dist[k]) {
                Arrays.fill(arr, Integer.MAX_VALUE);
            }
        }

        visited = new boolean[K + 1][N][M];

        matrix = new int[N][M];

        for (int n = 0; n < N; n ++) {
            char[] elements = br.readLine().toCharArray();
            
            for (int m = 0; m < M; m ++) {
                matrix[n][m] = Integer.parseInt(elements[m] + "");
            }
        }

        bfs();
        
        int answer = Integer.MAX_VALUE;

        for (int k = 0; k <= K; k ++) {
            answer = Math.min(answer, dist[k][N - 1][M - 1]);
        }


        if (answer == Integer.MAX_VALUE) {
            bw.write("-1\n");
        } else {
            bw.write(answer + "\n");
        }

        bw.flush();
    }

}
