package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class p2014 {
    static PriorityQueue<Long> mnq = new PriorityQueue<>();
    static int K, N;
    static long[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        K = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        arr = new long[K];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i ++) {
            arr[i] = Long.parseLong(st.nextToken());
            mnq.add(arr[i]);
        }

        long num = 0;
        for (int i = 0; i < N; i ++) {
            num = mnq.poll();

            for (int r = 0; r < K; r ++) {
                mnq.add(arr[r] * num);
                if (num % arr[r] == 0) break;
            }
        }

        System.out.println(num);
    }
}
