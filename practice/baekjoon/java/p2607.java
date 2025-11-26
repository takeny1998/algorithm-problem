
import java.io.*;

public class p2607 {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static int N;

    private static String word;

    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(br.readLine());

        word = br.readLine();

        int answer = 0;

        for (int i = 1; i < N; i++) {

            final String otherWord = br.readLine();

            int points = 0;
            int otherPoints = 0;

            boolean[] visited = new boolean[word.length()];
            boolean[] otherVisited = new boolean[otherWord.length()];

            for (int r = 0; r < word.length(); r++) {
                for (int c = 0; c < otherWord.length(); c++) {
                    if (otherVisited[c]) continue;
                    if (word.charAt(r) == otherWord.charAt(c)) {
                        otherVisited[c] = true;
                        points++;
                        break;
                    }
                }
            }

            for (int c = 0; c < otherWord.length(); c++) {
                for (int r = 0; r < word.length(); r++) {
                    if (visited[r]) continue;
                    if (otherWord.charAt(c) == word.charAt(r)) {
                        visited[r] = true;
                        otherPoints++;
                        break;
                    }
                }
            }

            if ((word.length() - points) <= 1 && (otherWord.length() - otherPoints) <= 1) {
                answer++;
            }
        }

        bw.write(answer + "\n");
        bw.flush();
    }

}
