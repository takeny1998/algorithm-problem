package baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class p1717 {
    
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static StringTokenizer st;

    private static int[] parent, rank;

    private static int N, M;

    private static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    private static void union(int a, int b) {
        a = find(a); b = find(b);

        if (a == b) return;

        // 두 집합 A, B의 크기(rank)가 높은 쪽을 부모로 선택
        // 만약 A가 B보다 낮다면 A와 B를 Swap함
        if (rank[a] < rank[b]) {
            int t = a;
            a = b;
            b = t;
        }
        parent[b] = a;

        if (rank[a] == rank[b]) {
            rank[a] ++;
        }
    }

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        parent = new int[N + 1];
        rank = new int[N + 1];

        for (int i = 1; i < N + 1; i ++) {
            parent[i] = i;
        }

        for (int m = 0; m < M; m ++) {
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (x == 0) {
                union(a, b);
                continue;
            }
            
            if (find(a) == find(b)) {
                bw.write("YES\n");
            } else {
                bw.write("NO\n");
            }
        }
        bw.flush();
    }
}
