package baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.StringTokenizer;

public class p2493 {

    static class Node implements Comparable<Node> {

        private final int index, num;

        public Node(int index, int num) {
            this.index = index;
            this.num = num;
        }

        public int compareTo(Node o) {
            return Integer.compare(this.num, o.num);
        }

    }

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringTokenizer st;

    private static final Stack<Node> stack = new Stack<>();

    private static int N;

    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i ++) {
            final int elm = Integer.parseInt(st.nextToken());
            final Node node = new Node(i + 1, elm);

            while (!stack.isEmpty() && stack.peek().compareTo(node) < 0) {
                stack.pop();
            }
            bw.write((!stack.isEmpty() ? stack.peek().index : 0) + " ");

            stack.push(node);
        }

        bw.flush();
    }

}
