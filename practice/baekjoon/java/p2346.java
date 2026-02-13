import java.util.*;
import java.io.*;

public class p2346 {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static StringTokenizer st;

    private static class Node {

        private int index;

        private int num;

        private Node left;

        private Node right;

        private boolean visisted = false;

    }

    private static int N;

    private static Node[] nodes;

    public static void main(String[] args) throws Exception {

        N = Integer.parseInt(br.readLine());
        nodes = new Node[N];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i ++) {
            nodes[i] = new Node();

            nodes[i].index = i + 1;
            nodes[i].num = Integer.parseInt(st.nextToken());

            if (i > 0) nodes[i].left = nodes[i - 1];
        }
        nodes[0].left = nodes[N - 1];

        nodes[N - 1].right = nodes[0];
        for (int i = N - 2; i >= 0; i --) {
            nodes[i].right = nodes[i + 1];
        }

        Node next = nodes[0];
        int found = 1;
        next.visisted = true;

        bw.write("1 ");

        while (found < N) {
            int num = next.num;

            int remaining = num;
            int prevIndex = next.index;

            while (remaining != 0) {
                if (remaining < 0) next = next.left;
                else next = next.right;

                if (next.visisted) continue;

                if (remaining < 0) remaining ++;
                else remaining --;
            }

            if (next.index == prevIndex) break;

            found ++;
            next.visisted = true;
            bw.write(next.index + " ");
        }

        bw.write("\n");
        bw.flush();
    }

}
