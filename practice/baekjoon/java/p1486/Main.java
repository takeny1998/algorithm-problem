package p1486;

import java.io.*;
import java.util.*;

public class Main {

    private static class Edge implements Comparable<Edge> {
        
        private int y, x, dist;

        Edge(int y, int x, int dist) {
            this.y = y;
            this.x = x;
            this.dist = dist;
        }

        @Override
        public int compareTo(Edge edge) {
            return Integer.compare(dist, edge.dist);
        }

    }

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static final int MAX = Integer.MAX_VALUE / 2;

    private static final int[] DY = { -1, 0, 1, 0 }, 
                               DX = { 0, 1, 0, -1 };

    private static StringTokenizer st;

    private static Queue<Edge> queue;

    private static int N, M, T, D;

    private static int[][] matrix, distMat, reDistMat;

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        matrix = new int[N][M];
        distMat = new int[N][M];
        reDistMat = new int[N][M];

        for (int n = 0; n < N; n ++) {
            Arrays.fill(distMat[n], MAX);
            Arrays.fill(reDistMat[n], MAX);

            char[] array = br.readLine().toCharArray();

            for (int m = 0; m < M; m ++) {
                if (array[m] < 'a') {
                    matrix[n][m] = array[m] - 65;
                    continue;
                }
                matrix[n][m] = array[m] - 71;
            }
        }

        queue = new PriorityQueue<>();
        queue.add(new Edge(0, 0, 0));
        distMat[0][0] = 0;

        while (!queue.isEmpty()) {
            Edge curt = queue.poll();

            for (int d = 0; d < 4; d ++) {
                int ny = curt.y + DY[d];
                int nx = curt.x + DX[d];

                if (ny < 0 || ny >= N || nx < 0 || nx >= M) continue;
                
                int elv = matrix[curt.y][curt.x] - matrix[ny][nx];
                if (Math.abs(elv) > T) continue;

                int nextDist = curt.dist + (elv < 0 ? elv * elv : 1);

                if (nextDist >= distMat[ny][nx]) continue;
                distMat[ny][nx] = nextDist;
                queue.add(new Edge(ny, nx, distMat[ny][nx]));
            }
        }

        queue = new PriorityQueue<>();
        queue.add(new Edge(0, 0, 0));
        reDistMat[0][0] = 0;

        while (!queue.isEmpty()) {
            Edge curt = queue.poll();

            for (int d = 0; d < 4; d ++) {
                int ny = curt.y + DY[d];
                int nx = curt.x + DX[d];

                if (ny < 0 || ny >= N || nx < 0 || nx >= M) continue;
                
                int elv = matrix[curt.y][curt.x] - matrix[ny][nx];
                if (Math.abs(elv) > T) continue;

                int nextDist = curt.dist + (elv <= 0 ? 1 : elv * elv);
                
                if (nextDist >= reDistMat[ny][nx]) continue;
                reDistMat[ny][nx] = nextDist;
                queue.add(new Edge(ny, nx, reDistMat[ny][nx]));
            }
        }
        
        int max = 0;

        for (int y = 0; y < N; y ++) {
            for (int x = 0; x < M; x ++) {
                int dist = distMat[y][x] + reDistMat[y][x];
                if (dist > D) continue;
                max = Math.max(max, matrix[y][x]);
        
            }
        }

        bw.write(max + "\n");
        bw.flush();
    }
}
