
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class p2075 {

    private static final Queue<Integer> minQueue = new PriorityQueue<>();

    private static final Queue<Integer> maxQueue = new PriorityQueue<>(Collections.reverseOrder());

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static int N;


    private static StringTokenizer st;

    public static void main(String[] args) throws Exception {

        N = Integer.parseInt(br.readLine());


        for (int y = 0; y < N; y ++) {
            st = new StringTokenizer(br.readLine());

            for (int x = 0; x < N; x ++) {
                int n = Integer.parseInt(st.nextToken());

                if (minQueue.size() == N) {
                    if (minQueue.peek() < n) {
                        minQueue.poll();
                        minQueue.add(n);
                    }
                } else {
                   minQueue.add(n);
                }
            }
        }

        for (int i = 0; i < N; i ++) {
            maxQueue.add(minQueue.poll());
        }


        for (int i = 0; i < N - 1; i ++) {
            maxQueue.poll();
        }

        bw.write(String.valueOf(maxQueue.poll()) + "\n");
        bw.flush();
    }

}
