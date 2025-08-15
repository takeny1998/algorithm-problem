package baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class p1283 {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static StringTokenizer st;

    private static List<List<String>> options;

    private static int N;

    private static final Map<String, List<Integer>> bindKey = new HashMap<>();

    public static void main(String[] args) throws Exception{

        N = Integer.parseInt(br.readLine());
        options = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            final List<String> option = new ArrayList<>();

            while (st.hasMoreTokens()) {
                option.add(st.nextToken());
            }

            options.add(option);
        }

        for (int i = 0; i < N; i ++) {

            final List<String> option = options.get(i);

            boolean isBind = false;

            // 1. 첫 문자 확인
            for (int r = 0; r < option.size(); r ++) {
                if (isBind) break;

                final String word = option.get(r);
                final String fistChar = String.valueOf(word.charAt(0)).toUpperCase();

                if (!bindKey.containsKey(fistChar)) {
                    bindKey.put(fistChar, List.of(i, r, 0));
                    isBind = true;
                }
            }

            // 2. 모든 문자 확인
            if (isBind) continue;

            for (int r = 0; r < option.size(); r ++) {
                final String word = option.get(r);

                for (int c = 0; c < word.length(); c ++) {
                    if (isBind) break;

                    final String curtChar = String.valueOf(word.charAt(c)).toUpperCase();

                    if (!bindKey.containsKey(curtChar)) {
                        bindKey.put(curtChar, List.of(i, r, c));
                        isBind = true;
                    }
                }
            }
        }

        for (int i = 0; i < N; i ++) {
            final List<String> option = options.get(i);

            for (int r = 0; r < option.size(); r ++) {
                final String word = option.get(r);

                for (int c = 0; c < word.length(); c ++) {

                    final String curtChar = String.valueOf(word.charAt(c)).toUpperCase();

                    if (bindKey.containsKey(curtChar)) {
                        List<Integer> list = bindKey.get(curtChar);
                        if ((list.get(0) == i && list.get(1) == r && list.get(2) == c)) {
                            bw.write("[");
                            bw.write(String.valueOf(word.charAt(c)));
                            bw.write("]");
                        } else {
                            bw.write(String.valueOf(word.charAt(c)));
                        }
                    } else {
                        bw.write(String.valueOf(word.charAt(c)));
                    }
                }
                bw.write(" ");
            }
            bw.write("\n");
        }

        bw.flush();
    }
}
