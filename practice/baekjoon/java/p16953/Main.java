package p16953;

import java.io.*;
import java.util.*;

public class Main {    
        
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static StringTokenizer st;

    private static int A, B, answer = Integer.MAX_VALUE;

    private static void dfs(long a, int depth) {
        if (a > B) return;
        if (a == B && depth < answer) answer = depth;

        dfs(a * 2, depth + 1);
        dfs(Long.parseLong(String.valueOf(a) + "1"), depth + 1);
    }

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());

        dfs(A, 1);

        if (answer == Integer.MAX_VALUE) answer = -1;
        bw.write(answer + "\n");
        bw.flush();
    }
}
