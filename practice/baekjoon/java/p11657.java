package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;


public class p11657 {

    static class Bus {
        int from, to, cost;

        Bus(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }

    static int N, M;
    static ArrayList<Bus> busList = new ArrayList<>();
    static long[] dist;
    static long INF = Long.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        dist = new long[N + 1];

        for (int i = 0; i < M; i ++) {
            st = new StringTokenizer(br.readLine());

            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            busList.add(new Bus(A, B, C));
        }

        for (int i = 1; i <= N; i ++) {
            dist[i] = INF;
        }

        bellmanFord();

        if (checkNegativeCycle()) {
            System.out.println(-1);
            return;
        }

        for (int i = 2; i <= N; i ++) {
            if (dist[i] == INF) {
                System.out.println(-1);
            } else {
                System.out.println(dist[i]);
            }
        }
    }

    static void bellmanFord() {
        dist[1] = 0;

        for (int i = 0; i < (N - 1); i ++) {
            for (Bus bus : busList) {
                if (dist[bus.from] != INF) {
                    long nextDist = dist[bus.from] + bus.cost;
                    if (nextDist < dist[bus.to]) {
                        dist[bus.to] = nextDist;
                    }
                }
            }
        }
    }

    static boolean checkNegativeCycle() {
        for (Bus bus : busList) {
            if (dist[bus.from] != INF) {
                long nextDist = dist[bus.from] + bus.cost;
                if (nextDist < dist[bus.to]) {
                    return true;
                }
            }
        }

        return false;
    }
}
