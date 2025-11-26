
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class p1620 {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;
    private static StringBuilder sb = new StringBuilder();
    private static int N, M;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        HashMap<Integer, String> numberMap = new HashMap<>();
        HashMap<String, Integer> stringMap = new HashMap<>();

        for(int i = 1; i <= N; i++) {
            String S = br.readLine();
            numberMap.put(i, S);
            stringMap.put(S, i);
        }

        for(int i = 0; i < M; i++) {
            String S = br.readLine();
            if(isNumeric(S)) {
                sb.append(numberMap.get(Integer.parseInt(S))).append("\n");
                continue;
            }
            sb.append(stringMap.get(S)).append("\n");
        }
        System.out.print(sb);
    }

    private static boolean isNumeric(String S) {
        return 49 <= S.charAt(0) && S.charAt(0) <= 57;
    }

}
