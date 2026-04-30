import java.io.*;
import java.util.*;

public class DP1 {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static int C, N, dp[][];

    private static char w[], s[];

    private static boolean match(int wi, int si) {
        if (dp[wi][si] != -1) return dp[wi][si] != 0;

        while (wi < w.length && si < s.length && (w[wi] == '?' || w[wi] == s[si])) {
            wi ++; si ++;
        }
        if (wi == w.length) return si == s.length;

        if (w[wi] == '*') {
            for (int nsi = si; nsi <= s.length; nsi ++) {
                if (match(wi + 1, nsi)) return true;
            }
        }
        return false;
    }

    public static void main(String[] args) throws Exception {
        
        C = Integer.parseInt(br.readLine());

        List<String> answers = new ArrayList<>();

        for (int c = 0; c < C; c ++) {
            w = br.readLine().toCharArray();

            N = Integer.parseInt(br.readLine());
            for (int n = 0; n < N; n ++) {
                s = br.readLine().toCharArray();

                dp = new int[101][101];
                for (int i = 0; i < 101; i ++) {
                    Arrays.fill(dp[i], -1);
                }
                
                if (match(0, 0)) answers.add(String.valueOf(s));
            }
            Collections.sort(answers);

            for (int i = 0; i < answers.size(); i ++) {
                bw.write(answers.get(i) + "\n");
            }
            answers.clear();
        }
        bw.flush();

    }
}
