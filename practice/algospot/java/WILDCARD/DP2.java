package algospot.java.WILDCARD;

import java.io.*;
import java.util.*;

/**
 * 개선된 DP 버전
 * match 함수 내의 반복문을 '부분 문제'로 분할한 재귀 형태로 변경한다.
 * O(N^3) 에서 match 함수를 O(1)로 개선해 O(N^2)로 개선되었다.
 */
public class DP2 {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static int C, N, dp[][];

    private static char w[], s[];

    private static boolean match(int wi, int si) {
        if (dp[wi][si] != -1) return dp[wi][si] != 0;

        if (wi < w.length && si < s.length && (w[wi] == '?' || w[wi] == s[si])) {
            // 반복문을 다음 원소로 재귀 호출해 변경한다.
            return match(wi + 1, si + 1);
        }
        if (wi == w.length) return si == s.length;

        if (w[wi] == '*') {
            // 와일드카드(*)일 때, 대조 문자열 s를 더 포함할지 검사한다.
            // 포함하지 않음 || 하나 더 포함함
            return match(wi + 1, si) || (si < s.length && match(wi, si + 1));
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
