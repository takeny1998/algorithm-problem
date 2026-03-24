package p13913;

import java.io.*;
import java.util.*;

public class Main {

    private static class Node {

        private int x, dist;

        Node(int x, int dist) {
            this.x = x;
            this.dist = dist;
        }

    }

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static StringTokenizer st;

    private static int N, K;

    private static int[] dists, prevs, answers;

    private static Queue<Node> queue = new ArrayDeque<>();

    private static final int MAX = Integer.MAX_VALUE;

    private static int getX(int d, int x) {
        if (d == 0) return x + 1;
        if (d == 1) return x - 1;
        return x * 2;
    }
    
    public static void main(String[] args) throws Exception {
        
        st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        dists = new int[200_001];
        prevs = new int[200_001];
        Arrays.fill(dists, MAX);
        
        dists[N] = 0;
        queue.add(new Node(N, 0));

        while (!queue.isEmpty()) {
            Node curt = queue.poll();

            for (int d = 0; d < 3; d ++) {
                int x = getX(d, curt.x);
                if (x < 0 || x >= 200_000) continue;
                if (dists[x] != MAX) continue;
                
                dists[x] = curt.dist + 1;
                prevs[x] = curt.x;
                queue.add(new Node(x, dists[x]));
            }
        }

        bw.write(dists[K] + "\n");

        answers = new int[dists[K] + 1];
        int answer = K;

        for (int ptr = dists[K]; ptr >= 0; ptr --) {
            answers[ptr] = answer;
            answer = prevs[answer];
        }

        for (int elm : answers) {
            bw.write(elm + " ");
        }
        bw.write("\n");
        bw.flush();
        
    }

}
