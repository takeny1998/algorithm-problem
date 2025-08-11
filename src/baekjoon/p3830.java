package baekjoon;

import java.io.*;
import java.util.StringTokenizer;

public class p3830 {
    static int N, M;
    static int[] parent;
    static long[] diff;

    private static void union(int a, int b, int w) {
        int parentA = find(a);
        int parentB = find(b);

        if (parentA == parentB) {
            return;
        }

        diff[parentA] = diff[b] - diff[a] + w;
        parent[parentA] = parentB;
    }

    private static int find(int a) {
        if (parent[a] == a) {
            return a;
        } else {
            int parentA = find(parent[a]);
            diff[a] += diff[parent[a]];
            return parent[a] = parentA;
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        while(true) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            if (N == 0 && M == 0) break;

            parent = new int[N + 1];
            diff = new long[N + 1];

            for (int i = 1; i <= N; i ++) {
                parent[i] = i;
            }

            int a, b, w;
            String command;

            for (int i = 0; i < M; i ++) {
                st = new StringTokenizer(br.readLine());

                command = st.nextToken();
                a = Integer.parseInt(st.nextToken());
                b = Integer.parseInt(st.nextToken());

                if (command.equals("!")) {
                    w = Integer.parseInt(st.nextToken());
                    union(a, b, w);
                } else {
                    // union find 가능
                    if (find(a) == find(b)) {
                        bw.write(diff[a] - diff[b] + "\n");
                    } else {
                        bw.write("UNKNOWN\n");
                    }
                }
            }
        }

        bw.flush();
        bw.close();
    }
}
