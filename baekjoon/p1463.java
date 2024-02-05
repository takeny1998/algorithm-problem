import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class p1463 {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static int N;
    private static final int MAX = Integer.MAX_VALUE;
    private static int[] weights;

    private static void bfs() {
        Queue<Integer> queue = new LinkedList<>();

        queue.add(N);
        weights[N] = 0;

        while (!queue.isEmpty()) {
            int curt = queue.poll();
            int weight = weights[curt];

            // 1에 도달한 경우
            if (weights[1] != MAX) return;

            // 3으로 나누는 경우
            if (curt % 3 == 0 && weights[curt / 3] == MAX) {
                weights[curt / 3] = weight + 1;
                queue.add(curt / 3);
            }

            // 2로 나누는 경우
            if (curt % 2 == 0 && weights[curt / 2] == MAX) {
                weights[curt / 2] = weight + 1;
                queue.add(curt / 2);
            }

            // 1을 빼는 경우
            if (weights[curt - 1] == MAX) {
                weights[curt - 1] = weight + 1;
                queue.add(curt - 1);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        weights = new int[N + 1];

        Arrays.fill(weights, MAX);
        bfs();

        bw.write(weights[1] + "");
        bw.flush();
        bw.close();
    }
}
