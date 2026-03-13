package p9470;

import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static StringTokenizer st;

    private static int T, K, M, P;

    private static List<Integer>[] graph;

    private static int[] inDegree;

    private static int[] dp;

    private static boolean[] visited;

    private static Queue<Integer> queue;

    private static List<Integer> result;

    public static void main(String[] args) throws Exception {
        T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t ++) {
            st = new StringTokenizer(br.readLine());

            K = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            P = Integer.parseInt(st.nextToken());

            inDegree = new int[M + 1];
            dp = new int[M + 1];
            graph = new ArrayList[M + 1];
            result = new ArrayList<>();
            visited = new boolean[M + 1];

            for (int m = 1; m <= M; m ++) {
                graph[m] = new ArrayList<>();
            }

            for (int p = 0; p < P; p ++) {
                st = new StringTokenizer(br.readLine());
                int A = Integer.parseInt(st.nextToken());
                int B = Integer.parseInt(st.nextToken());
                
                graph[A].add(B);
                inDegree[B] ++;
            }

            queue = new ArrayDeque<>();

            for (int m = 1; m <= M; m ++) {
                if (inDegree[m] > 0) continue;
                queue.add(m);
                dp[m] = 1;
                visited[m] = true;
            }

            while (!queue.isEmpty()) {
                int curt = queue.poll();
                result.add(curt);
                
                for (int next : graph[curt]) {
                    inDegree[next] --;

                    if (inDegree[next] > 0) continue;
                    queue.add(next);
                }
            }

            for (int i = 1; i <= M; i ++) {
                int curt = result.get(i - 1);

                for (int next : graph[curt]) {
                    if (dp[curt] < dp[next]) continue;
                    if (dp[curt] == dp[next]) {
                        if (visited[next]) continue;
                        visited[next] = true;
                        dp[next] ++;
                        continue;
                    }
                    dp[next] = dp[curt];
                }
            }
            bw.write(K + " " + dp[M] + "\n");
        }

        bw.flush();
    }
}
