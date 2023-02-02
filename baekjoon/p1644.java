import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class p1644 {
    static final int PRIME_SIZE = 4000010;
    static int[] is_prime = new int[PRIME_SIZE];
    static int[] prime_sum = new int[PRIME_SIZE];
    static int N, prime_ptr = 0;

    static void make_prime() {
        for (int i = 2; i <= N; i ++) {
            if (is_prime[i] < 0) continue;
            is_prime[i] = 1;

            for (int r = (i + i); r < PRIME_SIZE; r += i) {
                is_prime[r] = -1;
            }

            if (i > 2) prime_sum[prime_ptr] = i + prime_sum[prime_ptr ++ - 1];
            else prime_sum[prime_ptr ++] = i;

        }
    }

    static int find_subtotal() {
        int left = -1, right = 0;
        int count = 0, sum;

        while (right < prime_ptr && left <= right) {
            if (left < 0) sum = prime_sum[right];
            else sum = prime_sum[right] - prime_sum[left];

            if (sum == N) {
                count ++;
                right ++;
            }
            else if (sum > N) left ++;
            else right ++;
        }

        return  count;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        make_prime();
        System.out.println(find_subtotal());
    }
}
