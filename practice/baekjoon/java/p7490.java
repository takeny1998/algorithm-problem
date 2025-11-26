
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class p7490 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringBuilder sb = new StringBuilder();

    private static List<String> answers = new ArrayList<>();

    private static int[] nums;
    private static int N, T;

    private static String[] opers = { "+", "-", " " };

    private static void dfs (int index, String formula) {
        if (index == N - 1) {
            if (calculate(formula) == 0) {
                answers.add(formula);
            }
            return;
        }

        for (int i = 0; i < 3; i ++) {
            dfs(index + 1, formula + opers[i] + nums[index + 1]);
        }
    }

    private static int calculate (String formula) {
        String trimFormula = formula.replaceAll(" ", "");

        StringTokenizer st = new StringTokenizer(trimFormula, "+|-", true);

        int sum = Integer.parseInt(st.nextToken());

        while (st.hasMoreTokens()) {
            String token = st.nextToken();

            if (token.equals("+")) {
                sum += Integer.parseInt(st.nextToken());
            } else if (token.equals("-")) {
                sum -= Integer.parseInt(st.nextToken());
            }

        }

        return sum;
    }

    public static void main(String[] args) throws IOException {

        T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t ++) {
            N = Integer.parseInt(br.readLine());
            nums = new int[N];
            answers = new ArrayList<>();

            for (int i = 0; i < N; i ++) {
                nums[i] = i + 1;
            }

            dfs(0, String.valueOf(nums[0]));

            Collections.sort(answers);

            for (String answer : answers) {
                sb.append(answer).append("\n");
            }
            sb.append("\n");
        }

        System.out.print(sb);
    }
}
