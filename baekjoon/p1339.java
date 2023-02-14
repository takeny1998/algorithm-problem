import java.util.Arrays;
import java.util.Scanner;

public class baekjoon_1339 {
    static int N;
    static int[] alpha = new int[26];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();

        for (int i = 0; i < N; i ++) {
            String word = sc.next();
            int weight = (int) Math.pow(10, word.length() - 1);

            for (int r = 0; r < word.length(); r ++) {
                alpha[word.charAt(r) - 'A'] += weight;
                weight /= 10;
            }
        }

        Arrays.sort(alpha);
        int answer = 0;
        int num = 9;

        for (int i = 25; i >= 16; i --) {
            answer += alpha[i] * num;
            num --;
        }

        System.out.println(answer);
    }
}
