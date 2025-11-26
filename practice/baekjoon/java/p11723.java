
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class p11723 {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringBuilder sb = new StringBuilder();
    private static StringTokenizer st;
    private static int M, S;

    public static void main(String[] args) throws IOException {
        M = Integer.parseInt(br.readLine());
        S = 0;

        for (int i = 0; i < M; i ++) {
            st = new StringTokenizer(br.readLine());

            String command = st.nextToken();

            if (command.equals("all")) {
                S = ~0; // 1 ~ 20의 모든 원소에 1을 할당
                continue;
            }
            if (command.equals("empty")) {
                S = 0; // 1 ~ 20의 모든 원소에 0을 할당
                continue;
            }

            int x = Integer.parseInt(st.nextToken());

            if (command.equals("add")) {
                S |= (1 << x); // x번째 자리에 1을 할당
                continue;
            }
            if (command.equals("remove")) {
                S &= ~(1 << x); // x번째 자리에 0을 할당
                continue;
            }
            if (command.equals("check")) {
                sb.append((S & (1 << x)) == 0 ? 0 : 1).append("\n"); // x번째 자리 값을 출력
                continue;
            }
            if (command.equals("toggle")) {
                S ^= (1 << x); // x번째 자리에 XOR 연산
            }
        }

        System.out.print(sb);
    }
}
