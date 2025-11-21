package baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class p2252 {
    
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static StringTokenizer st;

    private static List<Integer> answer;

    private static boolean[] visisted;

    private static int N, M, a, b;

    private static List<Integer>[] edges;

    private static void dfs(int curt) {
        visisted[curt] = true;

        for (int next : edges[curt]) {
            if (visisted[next]) continue;
            dfs(next);
        }
        
        answer.add(curt);
    }

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        answer = new ArrayList<>();
        visisted = new boolean[N + 1];

        edges = new ArrayList[N + 1];

        for (int i = 0; i <= N; i ++) {
            edges[i] = new ArrayList<>();
        }

        for (int m = 0; m < M; m ++) {
            st = new StringTokenizer(br.readLine());

            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            edges[a].add(b);
        }

        for (int i = 1; i <= N; i ++) {
            if (visisted[i]) continue;
            dfs(i);
        }

        for (int i = answer.size() - 1; i >= 0; i --) {
            bw.write(answer.get(i) + " ");
        }
        bw.write("\n");
        bw.flush();
    }
}
