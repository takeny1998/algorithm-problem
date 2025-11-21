package baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class p13397 {
    
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static StringTokenizer st;

    private static int N, M, start = 0, end = 0;

    private static int[] nums;
    
    private static int parametricSearch() {

        while (start < end) {
            int mid = (start + end) / 2;

            int m = 1, max = nums[0], min = nums[0];

            for (int i = 1; i < N; i ++) {
                max = Math.max(max, nums[i]);
                min = Math.min(min, nums[i]);

                if ((max - min) > mid) {
                    m ++;
                    max = nums[i];
                    min = nums[i];
                }
            }

            if (m <= M) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }

        return end;
    }

    public static void main(String[] args) throws Exception {
    
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        nums = new int[N];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i ++) {
            nums[i] = Integer.parseInt(st.nextToken());
            end = Math.max(end, nums[i]);
        }

        int answer = parametricSearch();

        bw.write(answer + "\n");
        bw.flush();
    }
}
