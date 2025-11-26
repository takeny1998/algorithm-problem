
import java.io.*;
import java.util.*;

public class p1504 {

    private static class Node implements Comparable<Node> {

        private int index, dist;

        Node(int index, int dist) {
            this.index = index;
            this.dist = dist;
        }

        @Override
        public int compareTo(Node node) {
            return this.dist - node.dist;
        }

    }

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    // 실수: answer 도출 중 Integer.MAX_VALUE를 세개 더하면 오버풀로우 남
    private static final int MAX = (800 * 1000) * 10;

    private static StringTokenizer st;

    private static int N, E, a, b, c, v1, v2;

    private static List<Node>[] graph;

    private static int[] dijkstra(int start) {

        Queue<Node> queue = new PriorityQueue<>();
        queue.add(new Node(start, 0));

        int[] dists = new int[N + 1];
        Arrays.fill(dists, MAX);
        dists[start] = 0;

        while (!queue.isEmpty()) {
            Node curt = queue.poll();

            if (curt.dist > dists[curt.index]) continue;

            for (Node next : graph[curt.index]) {
                int dist = curt.dist + next.dist;

                if (dist >= dists[next.index]) continue;

                dists[next.index] = dist;
                queue.add(new Node(next.index, dist));
            }
        }

        return dists;
    }


    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N + 1];

        for (int n = 0; n <= N; n ++) {
            graph[n] = new ArrayList<>();
        }

        E = Integer.parseInt(st.nextToken());

        for (int e = 0; e < E; e ++) {
            st = new StringTokenizer(br.readLine());

            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            graph[a].add(new Node(b, c));
            graph[b].add(new Node(a, c));
        }

        st = new StringTokenizer(br.readLine());
        v1 = Integer.parseInt(st.nextToken());
        v2 = Integer.parseInt(st.nextToken());

        int[] distV1 = dijkstra(v1);
        int[] distV2 = dijkstra(v2);

        // 실수: v1과 v2 사이를 고려할 필요 없음
        // - 만약 사이에 시작점, 다른 정점이 존재해도 다 dist 배열에 반영되어있음
        // - 단순히 (시작 > v1 > v2 > 끝) 혹은 (시작 > v2 > v1 > 끝) 만 파악 OK
        int answer = Math.min(
            distV1[1] + distV1[v2] + distV2[N],
            distV2[1] + distV2[v1] + distV1[N]
        );

        if (answer >= MAX) {
            bw.write("-1\n");
        } else {
            bw.write(answer + "\n");
        }

        bw.flush();
    }

}
