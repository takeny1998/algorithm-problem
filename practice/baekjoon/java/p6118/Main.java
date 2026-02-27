package p6118;

import java.io.*;
import java.util.*;

public class Main {

    private static class Node {
        private int id;
        private int dist;

        Node(int id, int dist) {
            this.id = id;
            this.dist = dist;
        }
    }
    
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static StringTokenizer st;

    private static List<Integer>[] graph;

    private static int N, M;
    
    private static int[] minDists;

    private static boolean[] visited;

    private static Queue<Node> queue;

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N + 1];
        minDists = new int[N + 1];
        visited = new boolean[N + 1];

        for (int i = 1; i <= N; i ++) {
            graph[i] = new ArrayList<>();
            minDists[i] = Integer.MAX_VALUE;
        }

        for (int i = 1; i <= M; i ++) {
            st = new StringTokenizer(br.readLine());
            int A_i  = Integer.parseInt(st.nextToken());
            int B_i = Integer.parseInt(st.nextToken());
            
            graph[A_i].add(B_i);
            graph[B_i].add(A_i);
        }
        
        visited[1] = true;
        minDists[1] = 0;

        queue = new ArrayDeque<>();
        queue.add(new Node(1, 0));

        while (!queue.isEmpty()) {
            Node curt = queue.poll();

            for (int nextId : graph[curt.id]) {
                if (visited[nextId]) continue;
                visited[nextId] = true;
                Node next = new Node(nextId, curt.dist + 1);

                if (next.dist >= minDists[next.id]) continue;
                minDists[next.id] = next.dist;
                queue.add(next);
            }
        }

        int answerId = N;
        int maxDist = 0;
        int maxCount = 0;

        for (int i = 1; i <= N; i ++) {
            if (minDists[i] == Integer.MAX_VALUE || minDists[i] < maxDist) continue;
            if (minDists[i] == maxDist) {
                answerId = Math.min(answerId, i);
                maxCount ++;
                continue;
            }
            answerId = i;
            maxDist = minDists[i];
            maxCount = 1;
        }

        bw.write(answerId + " " + maxDist + " " + maxCount + "\n");
        bw.flush();
    }
}
