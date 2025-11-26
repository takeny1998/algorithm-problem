
import java.io.*;
import java.util.*;

public class p1874 {

    private static final BufferedReader br =
            new BufferedReader(new InputStreamReader(System.in));

    private static final BufferedWriter bw =
            new BufferedWriter(new OutputStreamWriter(System.out));

    private static Stack<Integer> stack;

    private static int N, ptr;

    private static int[] array;

    private static List<Character> answers;

    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(br.readLine());

        answers = new ArrayList<>();

        array = new int[N];
        for (int i = 0; i < N; i ++) {
            array[i] = Integer.parseInt(br.readLine());
        }

        stack = new Stack<>();

        int n = 1;

        while (n <= N) {
            if (!stack.isEmpty() && stack.peek() == array[ptr]) {
                stack.pop();
                answers.add('-');
                ptr ++;
                continue;
            }
            stack.push(n ++);
            answers.add('+');
        }

        while (!stack.isEmpty()) {
            if (stack.peek() != array[ptr]) break;
            stack.pop();
            answers.add('-');
            ptr ++;
        }

        if (ptr < (N - 1)) {
            bw.write("NO\n");
        } else {
            for (char oper : answers) bw.write(oper + "\n");
        }
        bw.flush();
    }

}
