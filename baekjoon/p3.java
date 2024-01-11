import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class p3 {

    private static class Node implements  Comparable<Node> {
        int index;
        long cost;

        public Node(int index, long cost) {
            this.index = index;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return Long.compare(this.cost, o.cost);
        }
    }
    static long INF = Long.MAX_VALUE;

    static int N, K;
    static boolean[] isCapitalArea;
    static ArrayList<Node>[] graph;

    private static void dijkstra(int start) {
        boolean[] isChecked = new boolean[N + 1];
        long[] dist = new long[N + 1];

        Arrays.fill(dist, INF);
        dist[start] = 0;

        PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
        priorityQueue.offer(new Node(start, 0));

        while (!priorityQueue.isEmpty()) {
            Node curtCity = priorityQueue.poll();

            if (isChecked[curtCity.index]
                // cutting 1 : 이미 다른 수도에서 영역권에 도달했으면 Skip.
                || isCapitalArea[curtCity.index]) continue;
            isChecked[curtCity.index] = true;

            for (Node nextCity : graph[curtCity.index]) {
                long newDist = dist[curtCity.index] + nextCity.cost;

                // cutting 2 : 새 거리가 K 이상이면 Skip.
                if (newDist > K) continue;

                if (newDist < dist[nextCity.index]) {
                    dist[nextCity.index] = newDist;
                    priorityQueue.offer(new Node(nextCity.index, dist[nextCity.index]));
                }
            }
        }

        for (int i = 1; i < (N + 1); i ++) {
            long minDist = dist[i];

            if (minDist <= K && i != start) {
                isCapitalArea[i] = true;
            }
        }
    }

    public static int[] solution(int n, int k, int[] capitals, int[][] edges) {
        N = n;
        K = k;

        isCapitalArea = new boolean[N + 1];

        graph = new ArrayList[N + 1];

        for (int i = 0; i <= N; i ++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];
            int cost = edge[2];

            graph[from].add(new Node(to, cost));
            graph[to].add(new Node(from, cost));
        }


        for (int start : capitals) {
            dijkstra(start);
        }

        ArrayList<Integer> arrayList = new ArrayList<>();

        for (int i = 1; i < (N + 1); i ++) {
            if (isCapitalArea[i])
                arrayList.add(i);
        }

        int[] answer = new int[arrayList.size()];

        for (int i = 0; i < arrayList.size(); i ++) {
            answer[i] = arrayList.get(i);
        }

        return answer;
    }

    public static void main(String[] args) {

        int[][] capitals = {
                {1, 9},
                {2}
        };
        int[][][] edges = {
                {
                    {1,2,3}, {2,4,4}, {3,2,1}, {1,6,6}, {1,5,6}, {1,7,6},
                    {6,7,2}, {5,7,5}, {7,8,2}, {9,7,3}, {9,10,6}, {9,11,3},
                    {11,12,2}, {11,13,4}
                },
                {
                    {1,2,11}, {1,5,1}, {2,4,5}, {5,4,4}, {4,3,7}, {4,6,8},
                    {4,6,8}, {4,7,3}, {6,7,3}
                }
        };

        System.out.println(Arrays.toString(solution(13, 5, capitals[0], edges[0])));
        System.out.println(Arrays.toString(solution(7, 10, capitals[1], edges[1])));

    }
}
