
import java.io.*;
import java.util.StringTokenizer;

public class p27172 {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringBuilder sb = new StringBuilder();
    private static StringTokenizer st;
    private static int N;
    private static int[] numbers, scores;
    private static boolean[] hasCard;

    private static final int MAX = 1000001;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        numbers = new int[N];
        scores = new int[MAX];
        hasCard = new boolean[MAX];

        st = new StringTokenizer(br.readLine());


        for (int i = 0; i < N; i ++) {
            numbers[i] = Integer.parseInt(st.nextToken());
            hasCard[numbers[i]] = true;
        }

        for (int i = 0; i < N; i ++) {
            int number = numbers[i];

            for (int r = number * 2; r < MAX; r += number) {
                if (!hasCard[r]) continue;

                scores[r] --;
                scores[number] ++;
            }
        }

        for (int i = 0; i < N; i ++) {
            sb.append(scores[numbers[i]]).append(" ");
        }

        System.out.println(sb);
    }
}
