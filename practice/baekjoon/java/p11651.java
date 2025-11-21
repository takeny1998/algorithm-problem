package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.io.IOException;

public class p11651 {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());

        int[][] arr = new int[N][2];

        for(int i = 0; i < N; i++) {
            // 위치 주의
            st = new StringTokenizer(br.readLine());
            arr[i][1] = Integer.parseInt(st.nextToken());
            arr[i][0] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr, (e1, e2) -> {
            if(e1[0] == e2[0]) {
                return e1[1] - e2[1];
            } else {
                return e1[0] - e2[0];
            }
        });

        for(int i = 0 ; i< N ; i++) {
            // 위치 주의
            sb.append(arr[i][1]).append(" ").append(arr[i][0]).append('\n');
        }
        System.out.print(sb);
    }

}
