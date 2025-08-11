package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class p1854 {
    static class Road implements Comparable<Road> {
        int index, time;
        Road(int index, int time) {
            this.index = index;
            this.time = time;
        }

        @Override
        public int compareTo(Road obj) {
            return Integer.compare(this.time, obj.time);
        }
    }

    static int N, M, K;
    static ArrayList<Road>[] map;
    static PriorityQueue<Integer>[] time;

    static void dijkstra() {
        PriorityQueue<Road> minQueue = new PriorityQueue<>();
        time[1].add(0);
        minQueue.add(new Road(1, 0));

        while (!minQueue.isEmpty()) {
            Road curt = minQueue.poll();

            if (curt.time > time[curt.index].peek()) {
                continue;
            }

            for (Road next : map[curt.index]) {
                int nextTime = curt.time + next.time;
                if (time[next.index].size() < K) {
                    time[next.index].add(nextTime);
                    minQueue.add(new Road(next.index, nextTime));
                } else if (nextTime < time[next.index].peek()){
                    time[next.index].poll();
                    time[next.index].add(nextTime);
                    minQueue.add(new Road(next.index, nextTime));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new ArrayList[N + 1];
        time = new PriorityQueue[N + 1];

        for (int i = 0; i <= N; i ++) {
            map[i] = new ArrayList<>();
            time[i] = new PriorityQueue<>(Collections.reverseOrder());
        }

        int a, b, c;

        for (int i = 0; i < M; i ++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            map[a].add(new Road(b, c));
        }

        dijkstra();

        for (int i = 1; i <= N; i ++) {
            if (time[i].size() < K) System.out.println(-1);
            else System.out.println(time[i].poll());
        }
    }
}
