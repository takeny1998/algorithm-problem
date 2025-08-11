package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class p6588 {
    static final int N = 100001;
    static final int PRIME_SIZE = 1000010;
    static int[] prime_arr = new int[PRIME_SIZE];

    static void make() {
        for (int i = 2; i < PRIME_SIZE; i ++) {
            if (prime_arr[i] < 0) continue;
            prime_arr[i] = 1;

            for (int r = (i + i); r < PRIME_SIZE; r += i) {
                prime_arr[r] = -1;
            }
        }
    }

    static String find(int num) {
        int left = 2, right = num;

        while (left <= right) {
            if (prime_arr[left] == 1 && prime_arr[right] == 1) {
                if (left + right == num)  {
                    return num + " = " + left + " + " + right + "\n";
                }
                else if (left + right < num) left ++;
                else right --;
            }

            if (prime_arr[left] == -1) left ++;
            if (prime_arr[right] == -1) right --;
        }

        return "Goldbach's conjecture is wrong.\n";
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        make();

        for (int i = 0; i < N; i ++) {
            int n = Integer.parseInt(br.readLine());
            if (n == 0) break;
            sb.append(find(n));
        }

        System.out.println(sb);
    }
}
