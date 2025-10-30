package baekjoon;

import java.io.*;

public class p5525 {
    
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static int N, M, answer = 0;

    private static char[] sequence;

    private static void sildingWindow() {
        int ptr = 1;
        int matched = 0;

        while (ptr < (M - 1)) {
            if (sequence[ptr - 1] == 'I' && sequence[ptr] == 'O' && sequence[ptr + 1] == 'I') {
                if (matched == (N - 1)) answer ++;
                else matched ++;
                ptr += 2;
            } else {
                matched = 0;
                ptr ++;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        sequence = br.readLine().toCharArray();

        sildingWindow();
        bw.write(answer + "\n");
        bw.flush();
    }

}
