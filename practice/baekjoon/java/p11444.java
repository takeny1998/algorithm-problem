package baekjoon;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class p11444 {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final Map<Long, Long> fibonachi = new HashMap<>();

    private static final long MOD = 1000000007L;

    public static void main(String[] args) throws IOException {
        // 피보나치 값 초기화!
        fibonachi.put(0L, 0L);
        fibonachi.put(1L, 1L);
        fibonachi.put(2L, 1L);

        long n = Long.parseLong(br.readLine());

        bw.write(func(n) + "");
        bw.flush();
        bw.close();
    }

    private static long func(long number) {

        if (fibonachi.containsKey(number)) {
            return fibonachi.get(number);
        }

        long result = 0L;

        // 짝수일 경우 ?? f(k/2) * {f((k+2)/2) + f(k-2/2)}
        if (number % 2L == 0) {
            long left = func(number / 2) % MOD;
            long right = (func((number + 2) / 2) + func((number - 2) / 2)) % MOD;

            result = (left * right) % MOD;

        // 홀수일 경우 ?? f((k+1)/2)^2 + f((k-1)/2)^2
        } else {
            long left = func((number + 1) / 2) % MOD;
            long right = func((number - 1) / 2) % MOD;

            result = ((left * left) + (right * right)) % MOD;
        }

        fibonachi.put(number, result);
        return result;
    }
}
