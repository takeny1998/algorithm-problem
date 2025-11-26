
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class p1863 {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static StringTokenizer st;

    private static final Stack<Integer> stack = new Stack<>();

    private static int N;

    private static int count = 0;

    public static void main(String[] args) throws Exception {

        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            final int x = Integer.parseInt(st.nextToken());
            final int y = Integer.parseInt(st.nextToken());

            while (!stack.isEmpty() && stack.peek() > y) {
                count ++;
                stack.pop();
            }

            if (!stack.isEmpty() && stack.peek() == y) continue;

            stack.push(y);
        }

        while (!stack.isEmpty() && stack.peek() > 0) {
            count ++;
            stack.pop();
        }
        bw.write(count + "\n");
        bw.flush();
    }


}
