package baekjoon;

import java.io.*;
import java.util.*;

public class p11724 {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringTokenizer st;
    private static boolean[] isVisited;
    private static ArrayList<Integer>[] graphs;

    private static void bfs(int start) {
        Queue<Integer> queue = new LinkedList<>();
        isVisited[start] = true;

        queue.add(start);

        while (!queue.isEmpty()) {
            int curt = queue.poll();

            for (int next : graphs[curt]) {
                if (isVisited[next]) continue;
                isVisited[next] = true;
                queue.add(next);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        isVisited = new boolean[N + 1];
        graphs = new ArrayList[N + 1];

        for (int i = 0; i <= N; i ++) {
            graphs[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i ++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            graphs[u].add(v);
            graphs[v].add(u);
        }
        int result = 0;

        for (int i = 1; i <= N; i ++) {
            if (isVisited[i]) continue;
            bfs(i);
            result ++;
        }

        bw.write(result + "");
        bw.flush();
        bw.close();
    }
}
