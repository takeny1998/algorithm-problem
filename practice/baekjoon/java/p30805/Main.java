package p30805;
import java.io.*;
import java.util.*;

public class Main {
        
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static StringTokenizer st;

    private static int N, M;

    private static int[] A, B;

    private static List<Integer> answer = new ArrayList<>();

    public static void main(String[] args) throws Exception {

        N = Integer.parseInt(br.readLine());
        A = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int n = 0; n < N; n ++) {
            A[n] = Integer.parseInt(st.nextToken());
        }

        M = Integer.parseInt(br.readLine());
        B = new int[M];

        st = new StringTokenizer(br.readLine());
        for (int m = 0; m < M; m ++) {
            B[m] = Integer.parseInt(st.nextToken());
        }

        int sn = 0, sm = 0;

        while (sn < N && sm < M) {
            int max = 0;
            int cn = sn, cm = sm;
        
            for (int n = sn; n < N; n ++) {
                for (int m = sm; m < M; m ++) {
                    if (A[n] != B[m] || A[n] <= max) continue;
                    max = A[n];
                    cn = n; cm = m;
                }
            }
        
            if (max == 0) break;
            sn = cn + 1; sm = cm + 1;
            answer.add(max);
        }

        bw.write(answer.size() + "\n");
        for (int max : answer) {
            bw.write(max + " ");
        }
        bw.write("\n");
        bw.flush();
    }
}
