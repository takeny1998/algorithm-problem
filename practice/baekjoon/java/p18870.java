package baekjoon;

import java.io.*;
import java.util.*;

public class p18870 {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringTokenizer st;
    private static int[] numbers, sorted;
    private static Map<Integer, Integer> orderMap = new HashMap<>();
    private static int N;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());

        numbers = new int[N];
        sorted = new int[N];

        for (int i = 0; i < N; i ++) {
            int value = Integer.parseInt(st.nextToken());
            numbers[i] = value;
            sorted[i] = value;
        }

        Arrays.sort(sorted);

        int rank = 0;

        for (int i = 0; i < N; i ++) {
            if (orderMap.containsKey(sorted[i])) continue;
            orderMap.put(sorted[i], rank ++);
        }

        for (int number : numbers) {
            bw.write(orderMap.get(number) + " ");
        }

        bw.flush();
        bw.close();
    }
}
