package p2015;

import java.io.*;
import java.util.*;

public class Main {
    
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static StringTokenizer st;

    private static long N, K, answer = 0;

    private static long[] A, sum;

    private static Map<Long, Integer> map = new HashMap<>();

    public static void main(String[] args) throws Exception {

        st = new StringTokenizer(br.readLine());

        N = Long.parseLong(st.nextToken());
        K = Long.parseLong(st.nextToken());
        
        A = new long[(int) N + 1];
        sum = new long[(int) N + 1];

        st = new StringTokenizer(br.readLine());

        for (int i = 1; i <= N; i ++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <= N; i ++) {
            sum[i] = sum[i - 1] + A[i];
        }

        map.put(0L, 1);

        for (int j = 1; j <= N; j ++) {
            long t = sum[j] - K;
            if (map.containsKey(t)) answer += map.get(t);
            map.put(sum[j], map.getOrDefault(sum[j], 0) + 1);
        }
        
        bw.write(answer + "\n");
        bw.flush();
    }
}
