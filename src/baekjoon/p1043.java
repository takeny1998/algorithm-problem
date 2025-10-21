package baekjoon;

import java.io.*;
import java.util.*;

public class p1043 {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static StringTokenizer st;

    private static List<List<Integer>> parties = new ArrayList<>();

    private static int[] parents;

    private static int N, M, T;

    private static int find(int x) {
        if (parents[x] == x) return x;
        return (parents[x] = find(parents[x]));
    }

    private static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a == b) return;
        parents[b] = a;
    }

    public static void main(String[] args) throws Exception {

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        parents = new int[N + 1];

        for (int p = 1; p <= N; p ++) {
            parents[p] = p;
        }

        st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());

        for (int t = 0; t < T; t ++) {
            parents[Integer.parseInt(st.nextToken())] = 0;
        }

        for (int m = 0; m < M; m ++) {
            parties.add(new ArrayList<>());

            st = new StringTokenizer(br.readLine());
            
            int N = Integer.parseInt(st.nextToken());

            for (int n = 0; n < N; n ++) {
                parties.get(m).add(Integer.parseInt(st.nextToken()));
            }

            parties.get(m).sort((a, b) -> Integer.compare(find(a), find(b)));
            for (int i = 1; i < parties.get(m).size(); i ++) {
                union(parties.get(m).get(0), parties.get(m).get(i));
            }
        }

       
        int answer = 0;

        for (List<Integer> party : parties) {
            boolean hasTruth = false;

            for (int i = 0; i < party.size(); i ++) {
                if (find(party.get(i)) == 0) {
                    hasTruth = true;
                    break;
                }
            }

            if (!hasTruth) {
                answer ++;
            }
        }

        bw.write(answer + "\n");
        bw.flush();
    }
}
