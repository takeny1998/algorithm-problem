package baekjoon;

import java.util.*;

public class p2143 {
    static int T;
    static List<Integer> sub_a, sub_b;

    static List<Integer> make_subtotal(int[] arr) {
        List<Integer> result = new ArrayList<>();
        int sum;

        for (int i = 0; i < arr.length; i ++) {
            sum = 0;
            for (int r = i; r < arr.length; r ++) {
               sum += arr[r];
               result.add(sum);
            }
        }

        Collections.sort(result);
        return result;
    }

    static long count() {
        long count = 0;
        int a_size = sub_a.size();
        int b_size = sub_b.size();
        int left = 0, right = b_size - 1;

        while (left < a_size && right >= 0) {
            long sum = sub_a.get(left) + sub_b.get(right);

            if (sum == T) {
                long left_cnt = 0, right_cnt = 0;
                int a = sub_a.get(left);
                int b = sub_b.get(right);

                while (left < a_size && sub_a.get(left) == a) {
                    left_cnt ++;
                    left ++;
                }
                while (right >= 0 && sub_b.get(right) == b) {
                    right_cnt ++;
                    right --;
                }

                count += left_cnt * right_cnt;
            } else if (sum < T) {
                left ++;
            } else {
                right --;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        T = sc.nextInt();

        int n = sc.nextInt();
        int[] arr_a = new int[n];
        for (int i = 0; i < n; i ++)
            arr_a[i] = sc.nextInt();

        n = sc.nextInt();;
        int[] arr_b = new int[n];
        for (int i = 0; i < n; i ++)
            arr_b[i] = sc.nextInt();

        sub_a = make_subtotal(arr_a);
        sub_b = make_subtotal(arr_b);

        System.out.println(count());
    }
}
