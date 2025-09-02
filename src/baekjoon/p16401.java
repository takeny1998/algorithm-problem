package baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class p16401 {
    
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static StringTokenizer st;

    private static int M, N;

    private static long max = 0, answer = 0;

    private static long[] L;

    private static long binarySearch() {
        long start = 1, end = max;

        while (start <= end) {
            long mid = (start + end) / 2;

            long count = 0;

            for (int i = 0; i < N; i ++) {
                count += L[i] / mid;
            }

            if (count >= M) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        return end;
    }

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        L = new long[N];
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i ++) {
            L[i] = Integer.parseInt(st.nextToken());
            max = Math.max(max, L[i]);
        }

        answer = binarySearch();

        bw.write(answer + "\n");
        bw.flush();

    }

}
