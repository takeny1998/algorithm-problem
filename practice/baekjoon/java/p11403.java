package baekjoon;

import java.io.*;
import java.util.*;

public class p11403 {
    
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static StringTokenizer st;

    private static int N;

    private static int[][] matrix;

    private static final int MAX = 10000000;

    public static void main(String[] args) throws Exception {
        
        N = Integer.parseInt(br.readLine());

        matrix = new int[N][N];

        // 정점 n > n 으로 가는 간선은 없다. INF로 해줘야 함
        for (int y = 0; y < N; y ++) {
            st = new StringTokenizer(br.readLine());

            for (int x = 0; x < N; x ++) {
                int value = Integer.parseInt(st.nextToken());
                matrix[y][x] = value == 0 ? MAX : value;
            }
        }


        for (int mid = 0; mid < N; mid ++) {
            for (int start = 0; start < N; start ++) {
                for (int end = 0; end < N; end ++) {
                    if (matrix[start][mid] == MAX || matrix[mid][end] == MAX) continue;

                    matrix[start][end] = Math.min(
                        matrix[start][end],
                        matrix[start][mid] + matrix[mid][end]
                    );
                }
            }
        }
        
        for (int[] arr : matrix) {
            for (int elm : arr) {
                bw.write((elm == MAX ? 0 : 1) + " ");
            }
            bw.write("\n");
        }
        bw.flush();
    }
}
