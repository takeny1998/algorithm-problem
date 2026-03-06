package p14226;

import java.io.*;
import java.util.*;

public class Main {

    private static class Element {

        private int index, size, depth;

        Element(int index, int size, int depth) {
            this.index = index;
            this.size = size;
            this.depth = depth;
        }

    }
    
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static int S, MAX = 1001 * 2;

    private static int[][] dist = new int[MAX][MAX];

    private static boolean[][] visited = new boolean[MAX][MAX];

    private static Queue<Element> queue = new ArrayDeque<>();

    public static void main(String[] args) throws Exception {
        S = Integer.parseInt(br.readLine());

        visited[1][0] = true;
        queue.add(new Element(1, 0, 0));

        while (!queue.isEmpty()) {
            Element curt = queue.poll();

            if (!visited[curt.index][curt.index]) {
                visited[curt.index][curt.index] = true;
                queue.add(new Element(curt.index, curt.index, curt.depth + 1));
            }

            if (curt.size > 0 && curt.index + curt.size < MAX) {
                if (!visited[curt.index + curt.size][curt.size]) {
                    visited[curt.index + curt.size][curt.size] = true;
                    dist[curt.index + curt.size][curt.size] = curt.depth + 1;
                    queue.add(new Element(curt.index + curt.size, curt.size, curt.depth + 1));

                }
            }
            if (curt.index - 1 >= 1) {
                if (!visited[curt.index - 1][curt.size]) {
                    visited[curt.index - 1][curt.size] = true;
                    dist[curt.index - 1][curt.size] = curt.depth + 1;
                    queue.add(new Element(curt.index - 1, curt.size, curt.depth + 1));
                }
            }

        }

        int answer = Integer.MAX_VALUE;
        for (int elm : dist[S]) {
            if (elm == 0) continue;
            answer = Math.min(answer, elm);
        }
        bw.write(answer + "\n");
        bw.flush();
    }

}
