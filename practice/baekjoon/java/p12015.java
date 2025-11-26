
import java.io.*;
import java.util.StringTokenizer;

public class p12015 {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringTokenizer st;
    private static int[] numbers, lis;
    private static int N;

    private static int binarySearch(int target, int length) {
        int left = 0, right = length - 1;
        while (left < right) {
            int mid = (left + right) / 2;
            int number = lis[mid];

            if (number >= target) right = mid;
            else left = mid + 1;
        }

        return right;
    }

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        numbers = new int[N];
        lis = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i ++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        int length = 1;
        lis[0] = numbers[0];
        for (int i = 1; i < N; i ++) {
            if (numbers[i] > lis[length - 1]) {
                lis[length ++] = numbers[i];
                continue;
            }

            int result = binarySearch(numbers[i], length);
            lis[result] = numbers[i];
        }

        bw.write(length + "");
        bw.flush();
        bw.close();
    }
}
