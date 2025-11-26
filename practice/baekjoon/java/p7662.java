
import java.io.*;
import java.util.*;

public class p7662 {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static StringTokenizer st;

    private static Queue<Integer> minQ, maxQ;

    private static Map<Integer, Integer> map;

    private static int T;

    private static void add(int num) {
        minQ.add(num);
        maxQ.add(num);

        map.put(num, map.getOrDefault(num, 0) + 1);
    }

    private static int poll(Queue<Integer> queue) {
        int num = -1;
        while (true) {
            num = queue.poll();

            int count = map.getOrDefault(num, 0);

            if (count == 0) continue;
            if (count == 1) map.remove(num);
            else map.put(num, count - 1);

            break;
        }

        return num;
    }


    public static void main(String[] args) throws Exception {
        T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t ++) {
            minQ = new PriorityQueue<>();
            maxQ = new PriorityQueue<>(Collections.reverseOrder());
            map = new HashMap<>();

            int k = Integer.parseInt(br.readLine());

            for (int i = 0; i < k; i ++) {
                st = new StringTokenizer(br.readLine());

                String oper = st.nextToken();
                int n = Integer.parseInt(st.nextToken());

                if (oper.equals("I")) add(n);
                else {
                    if (map.isEmpty()) continue;

                    if (n == -1) poll(minQ);
                    else if (n == 1)  poll(maxQ);
                }
            }

            if (map.isEmpty()) {
                bw.write("EMPTY\n");
            }
            else {
                int max = poll(maxQ);
                int min = map.isEmpty() ? max : poll(minQ);
                bw.write(max + " " + min + "\n");
            }
        }

        bw.flush();
    }

}
