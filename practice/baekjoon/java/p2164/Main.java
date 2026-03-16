package p2164;

import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static int N, answer;

    private static Queue<Integer> deque = new ArrayDeque<>();

    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(br.readLine());
        
        for (int n = 1; n <= N; n ++) {
            deque.add(n);
        }

        boolean reuseElm = false;
        while(!deque.isEmpty()) {
            answer = deque.poll();

            if (reuseElm) {
                deque.add(answer);
                reuseElm = false;
                continue;
            }
            reuseElm = true;
        }
        
        bw.write(answer + "\n");
        bw.flush();
    }
}
