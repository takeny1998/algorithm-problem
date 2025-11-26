
import java.io.*;
import java.util.*;

public class p22862 {

    private static final BufferedReader br = new BufferedReader(
        new InputStreamReader(System.in)
    );

    private static final BufferedWriter bw = new BufferedWriter(
        new OutputStreamWriter(System.out)
    );

    private static StringTokenizer st;

    private static int N, K, answer = 0;

    private static int[] nums;

    private static void twoPointer() {
        int left = 0, right = 0, odds = 0, evens = 0;

        if (nums[0] % 2 == 0) evens ++;
        else odds ++;

        while (left <= right) {
            answer = Math.max(answer, evens);

            if (odds <= K && right < (N - 1)) {
                if (nums[++ right] % 2 == 0) evens ++;
                else odds ++;
            } else {
                if (nums[left ++] % 2 == 0) evens --;
                else odds --;
            }
        }

    }

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        nums = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i ++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        twoPointer();

        bw.write(answer + "\n");
        bw.flush();
    }


}
