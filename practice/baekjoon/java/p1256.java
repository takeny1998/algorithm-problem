
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class p1256 {
    static int N, M, K;
    static long[][] pascal;

    static void make_pascal() {
        pascal[0][0] = 1;

        for (int y = 1; y <= (N + M); y ++) {
            pascal[y][0] = 1;

            for (int x = 1; x <= y; x ++) {
                pascal[y][x] = pascal[y - 1][x - 1] + pascal[y - 1][x] % 1000000001;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        String answer = "";

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());


        pascal = new long[N + M + 1][N + M + 1];
        make_pascal();

        if (K > pascal[(N + M) - 1][M] + pascal[(N + M) - 1][M - 1]) {
            System.out.println(-1);
            return;
        }

        for(int i = (N + M); i >= 1;  i--) {
            if (M > 0 && K > pascal[(N + M) - 1][M]) {
                answer += "z";
                K -= pascal[(N + M) - 1][M];
                M --;
            } else if (N > 0){
                answer += "a";
                N --;
            }
        }

        System.out.println(answer);
    }
}
