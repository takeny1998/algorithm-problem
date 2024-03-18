import java.io.*;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class p13144 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringTokenizer st;

    private static Set<Integer> set = new HashSet<>();

    private static int N;
    private static int[] nums;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());

        nums = new int[N];

        st = new StringTokenizer(br.readLine());

        for (int n = 0; n < N; n ++) {
            nums[n] = Integer.parseInt(st.nextToken());
        }

        int left = 0, right = 0;
        long answer = 0;

        while (right < N && left <= right) {
            int curt = nums[right];

            if (set.contains(curt)) {
                set.remove(nums[left]); // nums[right] -> nums[left]로 변경
                left ++;
                continue;
            }

            right ++;
            set.add(curt);
            answer += (right - left);
        }

        bw.write(answer + "");
        bw.flush();
        bw.close();
    }
}

