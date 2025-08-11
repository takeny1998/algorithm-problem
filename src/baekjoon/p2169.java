package baekjoon;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class p2169 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringTokenizer st;

    private static int[][] nums, dp;
    private static int[] left, right;
    private static int Y, X;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        Y = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        nums = new int[Y][X];
        dp = new int[Y][X];


        for (int y = 0; y < Y; y ++) {
            st = new StringTokenizer(br.readLine());

            for (int x = 0; x < X; x ++) {
                nums[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        // 중간에 방향을 틀 수 없다.
        // 1. 두 방향(왼쪽, 아래) (오른쪽, 아래)로 향하는 배열을 각각 만들고 비교한다.
        // - 원소의 크기, 위치에 따라 중간에 아래쪽으로 이동하거나 방향을 바꾸는 경우가 더 클 수 있음.

        // 첫째 행은 (1, 1)부터 시작함 -> 왼쪽 이동
        dp[0][0] = nums[0][0];
        for (int x = 1; x < X; x ++) {
            dp[0][x] = nums[0][x] + dp[0][x - 1];
        }

        System.out.println(Arrays.toString(dp[0]));
        // 둘째 행부터는 왼쪽 방향, 오른쪽 방향 두 경우를 생각해야 함
        for (int y = 1; y < Y; y ++) {

            left = new int[X];
            right = new int[X];

            left[0] = nums[y][0] + dp[y - 1][0];
            for (int x = 1; x < X; x ++) {
                // left 왼쪽 원소 (오른쪽으로 이동한 경우)와 dp 위쪽 원소 (아래쪽으로 이동한 것)를 비교
                left[x] = Math.max(dp[y - 1][x], left[x - 1]) + nums[y][x];
            }

            right[X - 1] = nums[y][X - 1] + dp[y - 1][X - 1];
            for (int x = (X - 2); x >= 0; x --) {
                right[x] = Math.max(dp[y - 1][x], right[x + 1]) + nums[y][x];
            }

            // 1. 왼쪽, 오른쪽의 최종 합산 값을 비교해 둘중 한 배열로 업데이트
            // 2. 각 원소를 비교해 무조건 큰 값으로 업데이트
            // -> 그 다음 줄에서 위쪽 DP 행을 탐색할 때, 무조건 최댓 값임을 보장해야 한다.
            for (int x = 0; x < X; x ++) {
                dp[y][x] = Math.max(left[x], right[x]);
            }

            System.out.println(Arrays.toString(left));
            System.out.println(Arrays.toString(right));
            System.out.println(Arrays.toString(dp[y]));
            System.out.println();
        }

        // 맨 아래 N, M 지점은 최댓값이 됨
        bw.write(dp[Y - 1][X - 1] + "");
        bw.flush();
        bw.close();
    }

}
