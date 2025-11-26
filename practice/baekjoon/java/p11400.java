
import java.io.*;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class p11400 {
    static class Edge implements Comparable<Edge> {
        int start, end;
        Edge (int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Edge edge) {
            int compare = Integer.compare(this.start, edge.start);
            if (compare == 0) {
                return Integer.compare(this.end, edge.end);
            }
            return compare;
        }
    }
    static int V, E, order;
    static ArrayList<Integer>[] map;
    static PriorityQueue<Edge> answer;
    static int[] visitOrder;


    static int findArticulationVertex(int curt, int parent) {
        visitOrder[curt] = order ++;

        int minOrder = visitOrder[curt];

        for (int next : map[curt]) {
            // 다음에 이동할 간선이 부모로 갈 간선이라면 건너뛰기
            if (next == parent) {
                continue;
            }
            // 방문하지 않은 정점 탐색하기
            if (visitOrder[next] == 0) {
                // 재귀적으로 탐색해 연결된 정점의 방문 순서 최솟값을 구하기
                int nextMinOrder = findArticulationVertex(next, curt);

                // 탐색한 방문 순서의 최솟값이 현재 정점의 방문 순서보다 크면 단절선임
                if (nextMinOrder > visitOrder[curt]) {
                    if (curt < next) {
                        answer.add(new Edge(curt, next));
                    } else {
                        answer.add(new Edge(next, curt));
                    }
                }
                minOrder = Math.min(minOrder, nextMinOrder);
            } else {
                minOrder = Math.min(minOrder, visitOrder[next]);
            }
        }

        return minOrder;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        answer = new PriorityQueue<>();
        visitOrder = new int[V + 1];
        order = 1;

        // initialize edge array
        map = new ArrayList[V + 1];
        for (int i = 0; i <= V; i ++) {
            map[i] = new ArrayList<>();
        }

        // input edge
        int A, B;
        for (int i = 0; i < E; i ++) {
            st = new StringTokenizer(br.readLine());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());

            // edge hasn't direction.
            map[A].add(B);
            map[B].add(A);
        }

        for (int i = 1; i <= V; i ++) {
            if (visitOrder[i] == 0) {
                findArticulationVertex(i, 0);
            }
        }

        bw.write(answer.size() + "\n");
        while (!answer.isEmpty()) {
            Edge edge = answer.poll();
            bw.write(edge.start + " " + edge.end + "\n");
        }

        bw.flush();
        bw.close();
    }
}
