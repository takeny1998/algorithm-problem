
import java.io.*;
import java.util.StringTokenizer;

public class p14476 {
    static int N, max, answer;
    static int[] arr, leftToRight, rightToLeft;

    private static int getGCD(int a, int b) {
        if (a % b == 0) return b;

        return getGCD(b, (a % b));
    }

    private static void renewAnswer(int num, int value) {
        if (num % value == 0) return;

        if (value > max) {
            max = value;
            answer = num;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        answer = 0;
        max = 0;

        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        leftToRight = new int[N];
        rightToLeft = new int[N];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i ++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // get GCD (from left, to right)
        leftToRight[0] = arr[0];
        for (int i = 1; i < N; i ++) {
            leftToRight[i] = getGCD(leftToRight[i - 1], arr[i]);
        }

        // get GCD (from right, to left)
        rightToLeft[N - 1] = arr[N - 1];
        for (int i = (N - 2); i >= 0; i --) {
            rightToLeft[i] = getGCD(rightToLeft[i + 1], arr[i]);
        }

        // remove both ends element
        renewAnswer(arr[0], rightToLeft[1]);
        renewAnswer(arr[N - 2], rightToLeft[N - 2]);

        // remove inside element
        for (int i = 1; i < (N - 2); i ++) {
            int gcd = getGCD(leftToRight[i - 1], rightToLeft[i + 1]);
            renewAnswer(arr[i], gcd);
        }

        if (max == 0) System.out.println(-1);
        else System.out.println(max + " " + answer);
    }
}
