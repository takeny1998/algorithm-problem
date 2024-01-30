import java.io.*;
import java.util.*;

public class p16928 {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringTokenizer st;
    private static Map<Integer, Integer> warpMap = new HashMap<>();
    private static int[] rollCounts;
    private static boolean[] isVisited;
    private static int N, M;

    public static void bfs() {
        Queue<Integer> queue = new LinkedList<>();

        queue.add(1);

        while (!queue.isEmpty()) {
            int curt = queue.poll();
            int nextRoll = rollCounts[curt] + 1;

            for (int dice = 1; dice <= 6; dice ++) {
                int next = curt + dice;

                if (next > 100 || isVisited[next]) continue;
                isVisited[next] = true;
                rollCounts[next] = nextRoll;

                if (warpMap.containsKey(next)) {
                    int warpNext = warpMap.get(next);
                    if (!isVisited[warpNext]) {
                        isVisited[warpNext] = true;
                        rollCounts[warpNext] = nextRoll;
                        queue.add(warpNext);
                    }

                } else {
                    queue.add(next);
                }

            }
        }
    }

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        isVisited = new boolean[101];
        rollCounts = new int[101];

        for (int i = 0; i < N; i ++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            warpMap.put(x, y);
        }

        for (int i = 0; i < M; i ++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            warpMap.put(x, y);
        }

        bfs();

        bw.write(rollCounts[100] + "");
        bw.flush();
        bw.close();
    }
}
