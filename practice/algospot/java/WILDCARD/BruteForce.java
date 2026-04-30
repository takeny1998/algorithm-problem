package algospot.java.WILDCARD;

import java.io.*;
import java.util.*;

public class BruteForce {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static int C, N;

    private static boolean match(char[] w, char[] s) {
        int i = 0;

        while (i < w.length && i < s.length && (w[i] == '?' || w[i] == s[i])) {
            i ++;
        }
        if (i == w.length) return i == s.length;

        if (w[i] == '*') {
            char[] nw = Arrays.copyOfRange(w, i + 1, w.length);
            for (int ni = i; ni <= s.length; ni ++) {
                char[] ns = Arrays.copyOfRange(s, ni, s.length);
                if (match(nw, ns)) return true;
            }
            return false;
        }
        return false;
    }

    public static void main(String[] args) throws Exception {
        
        C = Integer.parseInt(br.readLine());

        List<String> answers = new ArrayList<>();

        for (int c = 0; c < C; c ++) {
            char[] w = br.readLine().toCharArray();

            N = Integer.parseInt(br.readLine());
            for (int n = 0; n < N; n ++) {
                
                char[] s = br.readLine().toCharArray();
                
                if (match(w, s)) answers.add(String.valueOf(s));
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
