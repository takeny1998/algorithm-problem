
import java.io.*;
import java.util.*;

public class p1181 {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static int N;
    private static String[] words;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());

        words = new String[N];

        for (int i = 0; i < N; i ++) {
            words[i] = br.readLine();
        }

        // Comparator<T> 를 선언해도 되지만, 람다 식으로 하는게 깔끔하다.
        Arrays.sort(words, (word1, word2) -> {
            if (word1.length() == word2.length()) {
                // CompareTo()로 String을 사전 순으로 비교 가능하다.
                return word1.compareTo(word2);
            }
            // 정렬할 때 보통 word1 - word2으로 구현한다
            // - 양수면 word1이 크고, 음수면 word2가 크다는 것
            // - 뺄셈이 0이면 두 수가 같다는 것
            return word1.length() - word2.length();
        });

        String prevWord = words[0];
        bw.write(words[0] + "\n");

        for (int i = 1; i < N; i ++) {
            if (words[i].equals(prevWord)) continue;
            bw.write(words[i] + "\n");
            prevWord = words[i];
        }

        bw.flush();
        bw.close();
    }
}
