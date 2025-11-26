
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class p14003 {
    static int[] num,  dp, index;
    static int N, ptr = 1;

    static int binarySearch(int key) {
        int left = 1, right = ptr;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (key > dp[mid]) {
                left = mid + 1;
            } else if (key < dp[mid]){
                right = mid - 1;
            } else {
                return mid;
            }
        }

        return left;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        num = new int[N + 1];
        dp = new int[N + 1];
        index = new int[N + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i ++) {
            num[i] = Integer.parseInt(st.nextToken());
        }

        dp[1] = num[1];
        index[1] = 1;

        for (int i = 2; i <= N; i ++) {
            int result = binarySearch(num[i]);
            index[i] = result;
            if (result > ptr) {
                ptr ++;
                dp[ptr] = num[i];
            } else {
                dp[result] = num[i];
            }

        }

        int[] answer = new int[N + 1];
        int temp = ptr;

        for(int i = N ; i >= 1 ; i--) {
            if(ptr == index[i]) {
                answer[ptr --] = num[i];
            }
        }

        System.out.println(temp);
        for (int i = 1;i <= temp; i ++)
            System.out.print(answer[i] + " ");
        System.out.println();
    }
}
