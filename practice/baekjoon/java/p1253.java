package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class p1253 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringBuilder sb = new StringBuilder();
    private static StringTokenizer st;

    private static int N;
    private static int[] nums;

    public static void main(String[] args) throws IOException {

        N = Integer.parseInt(br.readLine());

        nums = new int[N];
        st = new StringTokenizer(br.readLine());

        for (int n = 0; n < N; n ++) {
            nums[n] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(nums);

        int answer = 0;

        for (int i = 0; i < N; i ++) {

            int left = 0, right = N - 1;

            while (left <= right) {
                // 포인터가 현재 숫자를 가르킬 경우 Skip
                if (left == i) left ++;
                if (right == i) right --;

                if (left >= right) break;

                int sum = nums[left] + nums[right];

                if (sum == nums[i]) {
                    answer ++;
                    break;
                }

                if (sum < nums[i]) left ++;
                if (sum > nums[i]) right --;
            }

        }

        sb.append(answer);
        System.out.println(sb);
    }

}
