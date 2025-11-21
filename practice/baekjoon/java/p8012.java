package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class p8012 {
    static int N, M, maxDepth = 0, answer = 0;
    static ArrayList<Integer>[] map;
    static int[] depth, depthDist;
    static int[][] parent;

    static void findDepth() {
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        queue.add(1);
        depth[1] = 1;

        while (!queue.isEmpty()) {
            int curt = queue.poll();

            for (int next : map[curt]) {
                if (depth[next] == 0) {
                    depth[next] = depth[curt] + 1;
                    parent[0][next] = curt;
                    queue.add(next);
                }
            }
        }
    }

    static void findParent() {
        for (int d = 1; d <= maxDepth; d ++) {
            for (int i = 1; i <= N; i ++) {
                parent[d][i] = parent[d - 1][parent[d - 1][i]];
            }
        }
    }

    static int findDist(int a, int b) {
        int dist = 0;

        if (depth[a] < depth[b]) {
            return findDist(b, a);
        }

        for (int d = (maxDepth - 1); d >= 0; d --) {
            if (depth[parent[d][a]] >= depth[b]) {
                a = parent[d][a];
                dist += depthDist[d];
            }
        }

        if (a == b) return dist;

        for (int d = (maxDepth - 1); d >= 0; d --) {
            if (parent[d][a] != parent[d][b]) {
                a = parent[d][a];
                b = parent[d][b];
                dist += (depthDist[d] * 2);
            }
        }

        return dist + 2;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        int logN = 1;
        while (logN <= N) {
            logN *= 2; maxDepth ++;
        }

        depthDist = new int[maxDepth + 1];
        depth = new int[N + 1];
        map = new ArrayList[N + 1];
        parent = new int[maxDepth + 1][N + 1];

        // make dist per depth
        int num = 1;
        for (int i = 0; i <= maxDepth; i ++) {
            depthDist[i] = num;
            num *= 2;
        }

        for (int i = 0; i <= N; i ++) {
            map[i] = new ArrayList<>();
        }

        int A, B;

        for (int i = 0; i < (N - 1); i ++) {
            st = new StringTokenizer(br.readLine());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());
            map[A].add(B); map[B].add(A);
        }

        findDepth();
        findParent();

        M = Integer.parseInt(br.readLine());

        int from = 1, to;

        for (int i = 0; i < M; i ++) {
            to = Integer.parseInt(br.readLine());
            answer += findDist(from, to);
            from = to;
        }

        System.out.println(answer);
    }
}
