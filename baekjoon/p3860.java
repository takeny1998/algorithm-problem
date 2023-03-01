import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class p3860 {
    static class Point {
        int y, x;
        Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    static class Edge {
        Point p1, p2;
        int weight;

        Edge(Point p1, Point p2, int weight) {
            this.p1 = p1;
            this.p2 = p2;
            this.weight = weight;
        }
    }

    static int W, H, G, E;
    static final int INF = Integer.MAX_VALUE;
    static ArrayList<Edge> map;
    static int[][] status, dist;

    static void bellmanFord() {
        dist[0][0] = 0;

        // 귀신 구멍은 정점이 아니므로 뺴주기
        for (int i = 0; i < ((W * H) - G) - 1; i ++) {
            for (Edge edge : map) {
                if (dist[edge.p1.y][edge.p1.x] != INF) {
                    int nextDist = dist[edge.p1.y][edge.p1.x] + edge.weight;
                    if (nextDist < dist[edge.p2.y][edge.p2.x]) {
                        dist[edge.p2.y][edge.p2.x] = nextDist;
                    }
                }
            }
        }
    }

    static boolean checkNegativeCycle() {
        for (Edge edge : map) {
            if (dist[edge.p1.y][edge.p1.x] != INF) {
                int nextDist = dist[edge.p1.y][edge.p1.x] + edge.weight;
                if (nextDist < dist[edge.p2.y][edge.p2.x]) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());
            if (W == 0 && H == 0) break;

            map = new ArrayList<>();
            status = new int[H][W];
            dist = new int[H][W];

            G = Integer.parseInt(br.readLine());
            for (int i = 0; i < G; i ++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                // -1 = 귀신 구멍, 0 = 이동 가능한 칸, 1 = 묘지
                status[y][x] = 1;
            }

            E = Integer.parseInt(br.readLine());

            for (int i = 0; i < E; i ++) {
                st = new StringTokenizer(br.readLine());
                int x1 = Integer.parseInt(st.nextToken());
                int y1 = Integer.parseInt(st.nextToken());
                int x2 = Integer.parseInt(st.nextToken());
                int y2 = Integer.parseInt(st.nextToken());
                int T = Integer.parseInt(st.nextToken());

                map.add(new Edge(new Point(y1, x1), new Point(y2, x2), T));
                status[y1][x1] = -1;
            }


            for (int y = 0; y < H; y ++) {
                for (int x = 0; x < W; x ++) {
                    dist[y][x] = INF;

                    // 도착점, 귀신 구멍이면 continue
                    if (y == (H - 1) && x == (W - 1)) continue;
                    if (status[y][x] == -1) continue;

                    // 현재 위치에서 묘지를 제외한 인접점 연결
                    Point curtPoint = new Point(y, x);
                    if (y > 0 && status[y - 1][x] != 1)
                        map.add(new Edge(curtPoint, new Point(y - 1, x), 1));
                    if (y < (H - 1) && status[y + 1][x] != 1)
                        map.add(new Edge(curtPoint, new Point(y + 1, x), 1));
                    if (x > 0 && status[y][x - 1] != 1)
                        map.add(new Edge(curtPoint, new Point(y, x - 1), 1));
                    if (x < (W - 1) && status[y][x + 1] != 1)
                        map.add(new Edge(curtPoint, new Point(y, x + 1), 1));
                }
            }

            bellmanFord();
            if (checkNegativeCycle()) {
                System.out.println("Never");
            } else if (dist[H - 1][W - 1] == INF) {
                System.out.println("Impossible");
            } else {
                System.out.println(dist[H - 1][W - 1]);
            }
        }
    }
}
