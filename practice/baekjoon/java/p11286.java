
import java.io.*;
import java.util.*;

public class p11286 {

    private static class Element implements Comparable<Element> {

        private int value, abs;

        Element(int value) {
            this.value = value;
            this.abs = Math.abs(value);
        }

        @Override
        public int compareTo(Element element) {
            if (this.abs == element.abs) {
                return Integer.compare(value, element.value);
            }
            return Integer.compare(abs, element.abs);
        }

    }

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static int N, x;

    private static Queue<Element> queue;

    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(br.readLine());
        queue = new PriorityQueue<>();

        for (int n = 0; n < N; n ++) {
            x = Integer.parseInt(br.readLine());

            if (x == 0) {
                if (queue.isEmpty()) bw.write("0\n");
                else bw.write(queue.poll().value + "\n");
                continue;
            }
            queue.add(new Element(x));
        }

        bw.flush();
    }
}
