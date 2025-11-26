
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class p1735 {
    static int a1, a2, b1, b2;

    static int gcd(int a, int b) {
        if (a % b == 0) {
            return b;
        }
        return gcd(b, a % b);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        a1 = Integer.parseInt(st.nextToken());
        a2 = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        b1 = Integer.parseInt(st.nextToken());
        b2 = Integer.parseInt(st.nextToken());

        int deno = (a1 * b2) + (b1 * a2);
        int numer = a2 * b2;
        // 두 분모의 최대공약수 구하기
        int gcd_num = gcd(deno, numer);

        System.out.printf("%d %d", deno / gcd_num, numer / gcd_num);
    }
}
