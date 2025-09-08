package baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class p7795 {
    

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static StringTokenizer st;

    private static int T, N, M;

    private static int[] A, B;

    public static int binarySearch(int key) {
        int start = 0, end = M - 1;

        while (start < end) {
            int mid = (start + end) / 2;

            if (B[mid] < key) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }

        return end;
    }

    public static void main(String[] args) throws Exception {
        T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t ++) {
            int answer = 0;

            st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            A = new int[N];
            
            M = Integer.parseInt(st.nextToken());
            B = new int[M];

            st = new StringTokenizer(br.readLine());
            for (int n = 0; n < N; n ++) {
                A[n] = Integer.parseInt(st.nextToken());
            }


            st = new StringTokenizer(br.readLine());
            for (int m = 0; m < M; m ++) {
                B[m] = Integer.parseInt(st.nextToken());
            }

            Arrays.sort(B);

            for (int n = 0; n < N; n ++) {
                int end = binarySearch(A[n]);

                if (A[n] > B[end]) {
                    answer += (end + 1);
                } else {
                    answer += end;
                }
            
            }
            bw.write(answer + "\n");
        }
        bw.flush();
    }

}
