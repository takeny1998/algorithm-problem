import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class P1697 {
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, K, MAX = 100001;
    static int[] field = new int[MAX];
    static boolean[] visited = new boolean[MAX];

    private static int bfs() {
        Queue<Integer> queue = new LinkedList<>();

        field[N] = 0;
        visited[N] = true;
        queue.add(N);

        while (!queue.isEmpty()) {
            int curt = queue.poll();
            int sec = field[curt] + 1;

            // 오른쪽으로 걷는 경우
            if (curt + 1 < MAX && !visited[curt + 1]) {
                visited[curt + 1] = true;
                field[curt + 1] = sec;
                queue.add(curt + 1);
            }

            // 왼쪽으로 걷는 경우
            if (curt - 1 >= 0 && !visited[curt - 1]) {
                visited[curt - 1] = true;
                field[curt - 1] = sec;
                queue.add(curt - 1);
            }

            // 순간이동 하는 경우
            if (curt * 2 < MAX && !visited[curt * 2]) {
                visited[curt * 2] = true;
                field[curt * 2] = sec;
                queue.add(curt * 2);
            }

            // K 위치에 방문 했는지 검사
            if (field[K] != -1) break;
        }

        return field[K];
    }

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        Arrays.fill(field, -1);
        System.out.println(bfs());
    }
}
