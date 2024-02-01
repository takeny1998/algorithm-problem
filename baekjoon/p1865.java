import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class p1865 {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringTokenizer st;
    private static int TC, N, M, W;
    private static int[] minDist;
    private static ArrayList<Road>[] roads;

    private static class Road {
        int end, time;

        public Road(int end, int time) {
            this.end = end;
            this.time = time;
        }
    }

    private static boolean bellmanFord(int start) {
        Arrays.fill(minDist, Integer.MAX_VALUE);
        minDist[start] = 0;
        boolean isUpdated = false;

        for (int iter = 1; iter < N; iter ++) {
            isUpdated = false;

            // 돌아가면서 최단 거리를 초기화한다.
            for (int i = 1; i <= N; i ++) {
                for (Road road: roads[i]) {
                    if (minDist[i] != Integer.MAX_VALUE && minDist[road.end] > minDist[i] + road.time) {
                        minDist[road.end] = minDist[i] + road.time;
                        isUpdated = true;
                    }
                }
            }

            // 만약 업데이트 되지 않으면 바로 종료한다 (Time-Cutting)
            if (!isUpdated) break;
        }

        // 한번 더 검사한다.
        if (isUpdated) {
            for (int i = 1; i <= N; i ++) {
                for (Road road: roads[i]) {
                    int dist = minDist[i] + road.time;
                    if (minDist[i] != Integer.MAX_VALUE && minDist[road.end] > dist) {
                        return true;
                    }
                }
            }
        }

        return false;
    }



    public static void main(String[] args) throws IOException {
        TC = Integer.parseInt(br.readLine());
        int S, E, T;

        for (int t = 0; t < TC; t ++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());

            minDist = new int[N + 1];


            roads = new ArrayList[N + 1];
            for (int s = 0; s <= N; s ++) {
                roads[s] = new ArrayList<>();
            }

            // 도로를 입력 받는다
            for (int m = 0; m < M + W; m ++) {
                st = new StringTokenizer(br.readLine());

                S = Integer.parseInt(st.nextToken());
                E = Integer.parseInt(st.nextToken());
                T = Integer.parseInt(st.nextToken());

                if (m < M) {
                    // 도로는 양방향 간선 이다.
                    roads[S].add(new Road(E, T));
                    roads[E].add(new Road(S, T));
                } else {
                    // 웜홀은 단반향 간선 이다.
                    // - 웜홀의 시간은 (-)를 곱해야 한다.
                    roads[S].add(new Road(E, -T));
                }

            }

            boolean hasNegativeCycle = false;

            for (int start = 1; start <= N; start ++) {
                if (bellmanFord(start)) {
                    hasNegativeCycle = true;
                    bw.write("YES\n");
                    break;
                }
            }

            if (!hasNegativeCycle) {
                bw.write("NO\n");
            }
        }

        bw.flush();
        bw.close();
    }
}
