
import java.io.*;
import java.util.*;

public class p10217 {
    static class Flight implements Comparable<Flight> {
        int dest, cost, time;

        Flight(int dest, int cost, int time) {
            this.dest = dest;
            this.cost = cost;
            this.time = time;
        }

        @Override
        public int compareTo(Flight flight) {
            return Integer.compare(this.time, flight.time);
        }
    }

    final static int INF = Integer.MAX_VALUE;
    static int T, N, M, K;
    static ArrayList<Flight>[] airport;
    static int[][] flightTime;

    static void findShortestPath() {
        PriorityQueue<Flight> minQueue = new PriorityQueue<>();

        flightTime[1][0] = 0;
        minQueue.add(new Flight(1, 0, 0));

        while (!minQueue.isEmpty()) {
            Flight curt = minQueue.poll();

            for (Flight next : airport[curt.dest]) {
                int newTime = curt.time + next.time;
                int newCost = curt.cost + next.cost;
                if (newCost > M) continue;
                if (newTime < flightTime[next.dest][newCost]) {
                    for (int cost = newCost; cost <= M; cost ++) {
                        if (newTime <  flightTime[next.dest][cost]) {
                            flightTime[next.dest][cost] = newTime;
                        }
                    }
                    minQueue.add(new Flight(next.dest, newCost, newTime));
                }
            }
        }

    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t ++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            airport = new ArrayList[N + 1];
            flightTime = new int[N + 1][M + 1];

            for (int i = 0; i <= N; i ++) {
                airport[i] = new ArrayList<>();
            }

            for (int i = 0; i <= N; i ++) {
                for (int r = 0; r <= M; r ++) {
                    flightTime[i][r] = INF;
                }
            }

            for (int i = 1; i <= K; i ++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                int cost = Integer.parseInt(st.nextToken());
                int time = Integer.parseInt(st.nextToken());
                airport[from].add(new Flight(to, cost, time));
            }

            findShortestPath();

            int minTime = INF;
            for (int cost = 0; cost <= M; cost ++) {
                minTime = Math.min(minTime, flightTime[N][cost]);
            }

            if (minTime == INF) bw.write("Poor KCM\n");
            else bw.write(minTime + "\n");
        }

        bw.flush();
        bw.close();
    }
}
