
import java.io.*;

public class p20442 {

    private static final BufferedReader br =
            new BufferedReader(new InputStreamReader(System.in));

    private static final BufferedWriter bw =
            new BufferedWriter(new OutputStreamWriter(System.out));

    private static char[] chars;

    private static int[] ksums, rsums;

    private static int answer = 0, N;

    private static void twoPointer() {
        int left = 1, right = N;

        while (left <= right) {
            int lk = ksums[left] - ksums[0];
            int rk = ksums[N] - ksums[right];

            int mr = rsums[right] - rsums[left - 1];

            if (mr > 0) answer = Math.max(answer, mr + (Math.min(lk, rk) * 2));

            if (lk <= rk && left < right) {
                left ++;
            } else {
                right --;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        chars = br.readLine().toCharArray();

        N = chars.length;

        ksums = new int[N + 1];
        rsums = new int[N + 1];

        for (int i = 1; i <= N; i ++) {
            ksums[i] += ksums[i - 1];
            rsums[i] += rsums[i - 1];

            if (chars[i - 1] == 'K') ksums[i] ++;
            else rsums[i] ++;
        }

        twoPointer();

        bw.write(answer + "\n");
        bw.flush();


    }

}
