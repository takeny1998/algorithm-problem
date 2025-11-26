
import java.util.Scanner;

public class p2748 {
    static long dp[] = new long[90];

    static long fibo(int n) {
        if (n <= 1) {
            return 1;
        } else if(dp[n] != 0) {
            return dp[n];
        }else {
            dp[n] = fibo(n - 1) + fibo(n - 2);
            return dp[n];
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println(fibo(sc.nextInt() - 1));
    }
}
