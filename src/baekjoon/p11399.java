package baekjoon;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class p11399 {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringTokenizer st;

    private static int N;
    private static int[] numbers, sums;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        numbers = new int[N];
        sums = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i ++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(numbers);

        int result = numbers[0];
        sums[0] = numbers[0];

        for (int i = 1; i < N; i ++) {
            sums[i] = sums[i - 1] + numbers[i];
            result += sums[i];
        }

        bw.write(result + "");
        bw.flush();
        bw.close();
    }
}
