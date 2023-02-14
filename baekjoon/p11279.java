import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Scanner;

public class p11279 {
    public static void main(String[] args) {
        PriorityQueue<Integer> mxq = new PriorityQueue<>(Collections.reverseOrder());
        Scanner sc = new Scanner(System.in);

        final int N = sc.nextInt();

        for (int i = 0; i < N; i ++) {
            int num = sc.nextInt();
            if (num == 0) {
                if (mxq.isEmpty()) System.out.println(0);
                else System.out.println(mxq.poll());
            } else {
                mxq.add(num);
            }
        }
    }
}
