package baekjoon;

import java.io.*;
import java.util.*;

public class p17352 {

    private static final BufferedReader br = new BufferedReader(
        new InputStreamReader(System.in)
    );

    private static final BufferedWriter bw = new BufferedWriter(
        new OutputStreamWriter(System.out)
    );

    private static StringTokenizer st;

    private static int N, groupNum = 1;

    private static int[] groups;

    private static List<List<Integer>> graph;

    private static void bfs(int start) {
        
        Queue<Integer> queue = new ArrayDeque<>();

        queue.add(start);
        groups[start] = groupNum;

        while (!queue.isEmpty()) {
            int curt = queue.poll();

            for (int next : graph.get(curt)) {
                if (groups[next] != 0) continue;
                groups[next] = groupNum;
                
                queue.add(next);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        
        N = Integer.parseInt(br.readLine());
        graph = new ArrayList<>();
        groups = new int[N + 1];

        for (int n = 0; n <= N; n ++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < (N - 2); i ++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken()),
                b  = Integer.parseInt(st.nextToken());

            graph.get(a).add(b);
            graph.get(b).add(a);
        }


        for (int i = 1; i <= N; i ++) {
            if (groups[i] != 0) continue;
            bfs(i);
            groupNum ++;
        }

        int prev = groups[1];
        bw.write(1 + " ");

        for (int i = 1; i <= N; i ++) {
            int curt = groups[i];

            if (prev != curt) {
                bw.write(i + "\n");
                break;
            }
            prev = curt;
        }
        bw.flush();
    }
    
}
