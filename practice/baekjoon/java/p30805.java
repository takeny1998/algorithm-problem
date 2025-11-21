package baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class p30805 {
    
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static StringTokenizer st;

    private static int N, M;

    private static int[] A, B;

    private static int[][] dp;

    private static final Set<List<Integer>> answers = new HashSet<>();


    private static void dfs(int y, int x, List<Integer> lcs) {
        
        if (y == 0 || x == 0) {
            List<Integer> answer = new ArrayList<>(lcs);
            Collections.reverse(answer);
            answers.add(answer);
            return;
        }

        if (A[y] == B[x]) {
            lcs.add(A[y]);
            dfs(y - 1, x - 1, lcs);
            lcs.remove(lcs.size() - 1);
        }

        dfs(y - 1, x, lcs);
        dfs(y, x - 1, lcs);
    }

    public static void main(String[] args) throws Exception {
        
        N = Integer.parseInt(br.readLine());
        A = new int[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i ++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
        
        M = Integer.parseInt(br.readLine());
        B = new int[M + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= M; i ++) {
            B[i] = Integer.parseInt(st.nextToken());
        }

        dp = new int[N + 1][M + 1];

        for (int y = 1; y <= N; y ++) {
            for (int x = 1; x <= M; x ++) {
                if (A[y] == B[x]) {
                    dp[y][x] = dp[y - 1][x - 1] + 1;
                    continue;
                }
                dp[y][x] = Math.max(dp[y - 1][x], dp[y][x - 1]);
            }
        }

        dfs(N, M, new ArrayList<>());

        
        List<List<Integer>> list = new ArrayList<>(answers);

        list.sort(new Comparator<>() {
           @Override
           public int compare(List<Integer> a, List<Integer> b) {
                for (int i = 0; i < Math.min(a.size(), b.size()); i ++) {
                    if (a.get(i) != b.get(i)) {
                        return b.get(i) - a.get(i);
                    }
                }

                return b.size() - a.size();
           } 
        });

        bw.write(list.get(0).size() + "\n");
        
        for (int elm : list.get(0)) {
            bw.write(elm + " ");
        }
        bw.write("\n");
        bw.flush();
    }

}
