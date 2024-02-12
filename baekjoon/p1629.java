import java.io.*;
import java.util.StringTokenizer;

public class p1629 {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringTokenizer st;
    private static long A, B, C;

    private static long divPow(long a, long b) {
        if (b == 1) return a % C;

        // 홀수가 와도 나머지를 버리므로 (N - 1) 효과가 난다.
        // 만약 8이면 4, 7이면 3...
        long pow = divPow(a, b / 2) % C;

        // 짝수면 2^(n/2) * 2^(n/2)
        if (b % 2 == 0L) {
            return pow * pow % C;
        }

        // 홀수면 2^((n-1)/2) * 2^((n-1)/2)
        return (pow * pow % C) * a % C;
    }

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());

        A = Long.parseLong(st.nextToken());
        B = Long.parseLong(st.nextToken());
        C = Long.parseLong(st.nextToken());

        long result = divPow(A, B);

        bw.write(result + "");
        bw.flush();
        bw.close();
    }
}
