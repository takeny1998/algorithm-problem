package baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class p3079 {
    
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static StringTokenizer st;

    private static int[] T;

    private static int N, M;
    
    private static long start = Long.MAX_VALUE, end = 0;

    private static long parametricSearch() {
        while (start < end) {
            long mid = (start + end) / 2;

            int m = 0;

            for (int i = 0; i < N; i ++) {
                if (m > M) break;
                m += mid / T[i];
            }

            if (m < M) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }

        return end;
    }

    public static void main(String[] args) throws Exception {

        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        T = new int[N];
        

        for (int i = 0; i < N; i ++) {
            T[i] = Integer.parseInt(br.readLine());
            start = Math.min(start, T[i]);
        }

        end = start * M;

        bw.write(parametricSearch() + "\n");
        bw.flush();
    }

}
