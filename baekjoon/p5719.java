import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Loc implements Comparable<Loc> {
    int index;
    long dist;
    boolean isActive;

    Loc(int index, long dist, boolean isActive) {
        this.index = index;
        this.dist = dist;
        this.isActive = isActive;
    }

    @Override
    public int compareTo(Loc loc) {
        return Long.compare(this.dist, loc.dist);
    }
}

public class p5719 {
    static ArrayList<Loc>[] map;
    static ArrayList<Integer>[] track;
    static long[] dist;
    static boolean[][] isShortest;
    static int N, M, S, D;
    final static int INF = Integer.MAX_VALUE;

    static void dijkstra() {
        PriorityQueue<Loc> minQ = new PriorityQueue<>();

        dist[S] = 0;
        minQ.add(new Loc(S, 0, true));

        while (!minQ.isEmpty()) {
            Loc curtLoc = minQ.poll();

            if (curtLoc.dist > dist[curtLoc.index]) {
                continue;
            }

           for (Loc nextLoc : map[curtLoc.index]) {
               if (isShortest[curtLoc.index][nextLoc.index]) continue;

               long nextDist = dist[curtLoc.index] + nextLoc.dist;

               if (nextDist == dist[nextLoc.index]) {
                   track[nextLoc.index].add(curtLoc.index);
               }

               if (nextDist < dist[nextLoc.index]) {
                   track[nextLoc.index].clear();
                   track[nextLoc.index].add(curtLoc.index);
                   dist[nextLoc.index] = nextDist;
                   minQ.add(new Loc(nextLoc.index, nextDist, true));
               }
            }
        }
    }


    static void findShortest(int index, int end) {
        if (index == end) return;

        for (int next : track[index]) {
            if (!isShortest[next][index]) {
                isShortest[next][index] = true;
                findShortest(next, end);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            if (N == 0 && M == 0) break;

            st = new StringTokenizer(br.readLine());
            S = Integer.parseInt(st.nextToken());
            D = Integer.parseInt(st.nextToken());

            map = new ArrayList[N];
            track = new ArrayList[N];
            isShortest = new boolean[N][N];
            dist = new long[N];

            for (int i = 0; i < N; i ++) {
                dist[i] = INF;
                map[i] = new ArrayList<>();
                track[i] = new ArrayList<>();
            }

            for (int i = 0; i < M; i ++) {
                st = new StringTokenizer(br.readLine());
                int U = Integer.parseInt(st.nextToken());
                int V = Integer.parseInt(st.nextToken());
                int P = Integer.parseInt(st.nextToken());
                map[U].add(new Loc(V, P, true));
            }


            dijkstra();
            findShortest(D, S);

            for (int i = 0; i < N; i ++) {
                dist[i] = INF;
            }
            dijkstra();

            if (dist[D] == INF) sb.append(-1 + "\n");
            else sb.append(dist[D] + "\n");
        }
        System.out.println(sb);
    }
}
