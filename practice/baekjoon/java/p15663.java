package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class p15663 {
    static int N, M;
    static int[] arr_num, sequence;
    static boolean[] visited;
    static HashSet<String> dup_answer = new HashSet<>();

    static void backtrack(int depth) {
        if (depth == M) {
            String answer = "";
            for (int i : sequence) answer += (i + " ");

            if (!dup_answer.contains(answer)) {
                dup_answer.add(answer);
                System.out.println(answer);
            }
            return;
        }

        for (int i = 0; i < N; i ++) {
            if (!visited[i]) {
                sequence[depth] = arr_num[i];
                visited[i] = true;
                backtrack(depth + 1);
                visited[i] = false;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        arr_num = new int[N];
        visited = new boolean[N];
        sequence = new int[M];

        for (int i = 0; i < N; i ++) {
            arr_num[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr_num);
        backtrack(0);


    }
}
