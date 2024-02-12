import java.io.*;

public class p2579 {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static int N;
    private static int[] dp, numbers;

    public static void main(String[] args) throws IOException {

        N = Integer.parseInt(br.readLine());

        dp = new int[N + 1];
        numbers = new int[N + 1];


        for (int i = 1; i <= N; i++) {
            numbers[i] = Integer.parseInt(br.readLine());
        }

        dp[1] = numbers[1];

        // N 이 1이 입력된 경우 예외처리
        if (N >= 2) {
            dp[2] = numbers[1] + numbers[2];
        }

        for (int i = 3; i <= N; i++) {
            dp[i] = Math.max(dp[i - 2] , dp[i - 3] + numbers[i - 1]) + numbers[i];
        }

        bw.write(dp[N] +"");
        bw.flush();
        bw.close();

    }

}