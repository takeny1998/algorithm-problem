package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class p1300 {
    static int N, K;

    static long binarySearch() {
        long low = 1, high = K;

        /*
            low-bound 이진 탐색
            1. 범위 내의 중간 수를 뽑고, 그 수 까지의 숫자 개수를 구한다.
            2. 숫자 개수가 K보다 크거나 같으면, 그 수는 mid 보다 작거나 같으므로 high = mid
            3. K보다 작으면, 그 수는 mid 보다 크므로 low = mid + 1
         */
        while (low < high) {
            long mid = (low + high) / 2;
            long prevElm = 0;

            /*
                임의의 숫자 mid 보다 작거나 같은 수의 합은
                1, 2, 2, 3, 3, ... 에서, 3까지의 숫자 갯수를 구구단을 활용해서 구할 수 있다.
                1단 : {1, 2, 3, 4, ...}   -> 3 / 1 = 3개
                2단 : {2, 4, 6, 8, ...}   -> 3 / 2 = 1개
                3단 : {3, 6, 9, 12, ...}  -> 3 / 3 = 1개
                4단 : {4, 8, 12, 16, ...} -> 3 / 4 = 0개
                ...

                즉, 3 + 1 + 1 = 5개가 된다.
             */
            for (int i = 1; i <= N; i ++) {
                prevElm += Math.min(mid / i, N);
            }
            if (prevElm >= K) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }

        return low;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());

        System.out.println(binarySearch());
    }
}
