import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class p1837 {
    static int K;
    static final int PRIME_NUM = 1000010;
    static int[] is_prime = new int[PRIME_NUM];
    static BigInteger P;

    static void make_prime() {
        for (int i = 2; i < PRIME_NUM; i ++) {
            if (is_prime[i] < 0) continue;
            is_prime[i] = 1;

            for (int ni = (i + i); ni < PRIME_NUM; ni += i) {
                is_prime[ni] = -1;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        P = new BigInteger(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        make_prime();

        for (int i = 2; i < K; i ++) {
            if (is_prime[i] == 1) {
                BigInteger prime = new BigInteger(Integer.toString(i));
                if (P.mod(prime).equals(BigInteger.ZERO)) {
                    System.out.printf("BAD %d", prime);
                    return;
                }
            }
        }
        System.out.println("GOOD");
    }
}
