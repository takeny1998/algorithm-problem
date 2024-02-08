import java.io.*;
import java.util.StringTokenizer;

public class p2467 {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringTokenizer st;
    private static long[] numbers;
    private static final long MAX = Long.MAX_VALUE;
    private static int N, resultA, resultB;
    private static long result = MAX;

    private static void twoPointer() {
        int left = 0, right = N - 1;

        while (left < right) {
            long sum = numbers[left] + numbers[right];

            if (Math.abs(sum) <= Math.abs(result)) {
                result = Math.abs(sum);
                resultA = left;
                resultB = right;
            }

            if (sum == 0) return;
            if (sum < 0) left ++;
            else right --;
        }
    }


    public static void main(String[] args)  throws IOException {
        N = Integer.parseInt(br.readLine());

        numbers = new long[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i ++) {
            numbers[i] = Long.parseLong(st.nextToken());
        }

        twoPointer();
        bw.write(numbers[resultA] + " " + numbers[resultB]);
        bw.flush();
        bw.close();
    }
}
