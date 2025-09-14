package baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Collections;

public class p1758 {
    
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static Integer[] tips;

    private static int N;

    private static long answer = 0L;

    public static void main(String[] args) throws Exception {
        
        N = Integer.parseInt(br.readLine());
        tips = new Integer[N];

        for (int i = 0; i < N; i ++) {
            tips[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(tips, Collections.reverseOrder());

        for (int i = 0; i < N; i ++) {
            answer += Math.max(0, tips[i] - i);
        }

        bw.write(answer + "\n");
        bw.flush();
    }
}
