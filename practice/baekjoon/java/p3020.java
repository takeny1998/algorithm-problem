package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class p3020 {
    static long[] a, sum;
    static int N, H;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        a = new long[H];
        sum = new long[H];

        for (int i = 0; i < N; i ++) {
            int h = Integer.parseInt(br.readLine());
            if (i % 2 == 0) { // 종유석일 때
                a[0] ++; a[h] --;
            } else { // 석순일 때
                a[H - h] ++;
            }
        }

        for (int i = 0; i < H; i ++) {
            if (i == 0) sum[i] = a[i];
            else sum[i] = (sum[i - 1] + a[i]);
        }
        Arrays.sort(sum);

        int answer = 0, ptr = 0;
        while (sum[ptr] == sum[0]) {
            answer ++;
            ptr ++;
        }

        System.out.printf("%d %d", sum[0], answer);
    }
}
