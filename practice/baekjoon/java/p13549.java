package baekjoon;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class p13549 {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringTokenizer st;
    private static int N, K;
    private static int[] times;

    private static void bfs(int start) {
        Deque<Integer> deque = new ArrayDeque<>();
        deque.add(start);

        times[start] = 0;

        while (!deque.isEmpty()) {
            int curt = deque.poll();

            if (curt == K) {
                return;
            }

            // 앞 뒤로 걷기
            int next = walk(curt, true);
            if (next >= 0) {
                if (times[curt] + 1 < times[next]) {
                    times[next] = times[curt] + 1;
                    deque.add(next);
                }
            }

            next = walk(curt, false);
            if (next >= 0) {
                if (times[curt] + 1 < times[next]) {
                    times[next] = times[curt] + 1;
                    deque.add(next);
                }
            }

            // 순간이동 하기
            next = curt * 2;
            if (next >= 0 && next < 100001) {
                if (times[curt] < times[next]) {
                    times[next] = times[curt];
                    deque.addFirst(next);
                }
            }
        }
    }

    private static int walk(int start, boolean isForward) {
        int end = isForward ? start + 1 : start - 1;
        return (end >= 0 && end < 100001) ? end : -1;
    }

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        times = new int[100001];
        Arrays.fill(times, Integer.MAX_VALUE);

        bfs(N);

        bw.write(times[K] + "");
        bw.flush();
        bw.close();
    }
}
