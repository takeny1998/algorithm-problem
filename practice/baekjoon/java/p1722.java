
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class p1722 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, C, K;
    static long[] permutation;

    static long findIndex () {
        long index = 0;

        for (int i = 0; i < permutation.length - 1; i ++) {
            long num = permutation[i];
           long rank = findRank(
                    Arrays.copyOfRange(permutation, i, permutation.length), num);
            index += rank * factorial(N - i - 1);
        }
        return index + 1;
    }

    static void printPerm (long index) {
        long[] nums = new long[N];

        for (int i = 0; i < N; i ++)
            nums[i] = i + 1;

        boolean[] picked = new boolean[N + 1];

        for (long i = 0; i < N; i ++) {
            long minIdx = factorial(N - i - 1);

            for (int num = N; num > 0; num --) {
                if (picked[num]) continue;
                long rank = findRank(nums, num);

                if (rank * minIdx < index) {
                    System.out.print(num + " ");
                    index -= rank * minIdx;
                    picked[num] = true;

                    final long finalNum = num;
                    nums = Arrays.stream(nums)
                            .filter(item -> item != finalNum)
                            .toArray();
                    break;
                }
            }
        }

    }

    static long findRank (long[] arr, long num) {
        long[] cloned = arr.clone();
        Arrays.sort(cloned);
        return Arrays.binarySearch(cloned, num);
    }

    static long factorial (long num) {
        if (num <= 1) return 1;
        return num * factorial(num - 1);
    }

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        C = Integer.parseInt(st.nextToken());

        if (C == 1) {
            printPerm(Long.parseLong(st.nextToken()));
        } else if (C == 2) {
            permutation = new long[N];
            for (int i = 0; i < N; i ++)
                permutation[i] = Long.parseLong(st.nextToken());
            System.out.println(findIndex());
        }
    }
}
