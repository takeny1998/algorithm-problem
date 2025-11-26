
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class p3176 {
    static class Road {
        int index, dist;

        Road (int index, int dist) {
            this.index = index;
            this.dist = dist;
        }
    }
    static ArrayList<Road>[] map;
    static int[] depth;
    static int[][] parent, minDist, maxDist;
    static int N, K, maxDepth, minAnswer = 1000001, maxAnswer = 0;

    static void bfs() {
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        depth[1] = 1;
        queue.add(1);

        while(!queue.isEmpty()) {
            int curt = queue.poll();

            for (Road nextRoad : map[curt]) {
                int next = nextRoad.index;

                if (depth[next] == 0) {
                    depth[next] = depth[curt] + 1;
                    parent[0][next] = curt;
                    minDist[0][next] = nextRoad.dist;
                    maxDist[0][next] = nextRoad.dist;
                    queue.add(next);
                }
            }
        }
    }

    static void findParent() {
        for (int d = 1; d <= maxDepth; d ++) {
            for (int i = 1; i <= N; i ++) {
                parent[d][i] = parent[d - 1][parent[d - 1][i]];
                minDist[d][i] = Math.min(minDist[d - 1][i], minDist[d - 1][parent[d - 1][i]]);
                maxDist[d][i] = Math.max(maxDist[d - 1][i], maxDist[d - 1][parent[d - 1][i]]);
            }
        }
    }

    static int lca(int a, int b) {
        if (depth[a] < depth[b]) {
            return lca(b, a);
        }

        minAnswer = 1000001;
        maxAnswer = 0;

        for (int d = (maxDepth - 1); d >= 0; d --) {
            if (depth[parent[d][a]] >= depth[b]) {
                minAnswer = Math.min(minAnswer, minDist[d][a]);
                maxAnswer = Math.max(maxAnswer, maxDist[d][a]);
                a = parent[d][a];
            }
        }

        if (a == b) return a;

        for (int d = (maxDepth - 1); d >= 0; d --) {
            if (parent[d][a] != parent[d][b]) {
                minAnswer = Math.min(minAnswer, Math.min(minDist[d][a], minDist[d][b]));
                maxAnswer = Math.max(maxAnswer, Math.max(maxDist[d][a], maxDist[d][b]));
                a = parent[d][a];
                b = parent[d][b];
            }
        }

        minAnswer = Math.min(minAnswer, Math.min(minDist[0][a], minDist[0][b]));
        maxAnswer = Math.max(maxAnswer, Math.max(maxDist[0][a], maxDist[0][b]));

        return parent[0][a];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        int check = 1;
        while (check <= N) {
            maxDepth ++;
            check <<= 1;
        }

        map = new ArrayList[N + 1];
        depth = new int[N + 1];
        parent = new int[maxDepth + 1][N + 1];
        minDist = new int[maxDepth + 1][N + 1];
        maxDist = new int[maxDepth + 1][N + 1];

        for (int i = 0; i <= N; i ++) {
            map[i] = new ArrayList<>();
        }

        int A, B, C;
        for (int i = 0; i < (N - 1); i ++) {
            st = new StringTokenizer(br.readLine());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            map[A].add(new Road(B, C));
            map[B].add(new Road(A, C));
        }

        bfs();
        findParent();

        K = Integer.parseInt(br.readLine());
        int D, E;

        for (int i = 0; i < K; i ++) {
            st = new StringTokenizer(br.readLine());
            D = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());
            lca(D, E);
            sb.append(minAnswer + " " + maxAnswer + "\n");
        }
        System.out.println(sb);
    }
}
