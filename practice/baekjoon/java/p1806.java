
import java.io.*;
import java.util.StringTokenizer;

public class p1806 {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringTokenizer st;
    private static int[] numbers;
    private static int N, S, result = Integer.MAX_VALUE;

    private static void twoPointer () {
        int left = 0, right = 0;
        int sum = numbers[0];

        while (left <= right && right < N) {
            int n = right - left + 1;

            if (sum < S) {
                sum += numbers[++ right];
            } else {
                sum -= numbers[left ++];

                result = Math.min(result, n);
            }
        }
    }


    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        numbers = new int[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i ++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }


        twoPointer();

        if (result == Integer.MAX_VALUE) bw.write("0");
        else bw.write(result + " ");
        bw.flush();
        bw.close();
    }
}
