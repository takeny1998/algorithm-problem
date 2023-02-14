import java.util.PriorityQueue;
import java.util.Scanner;

public class p1927 {
    public static void main(String[] args) {
        PriorityQueue<Integer> mnq = new PriorityQueue<>();

        Scanner sc = new Scanner(System.in);

        final int N = sc.nextInt();

        for (int i = 0; i < N; i ++) {
            int num = sc.nextInt();
            if (num == 0) {
                if (mnq.isEmpty()) System.out.println(0);
                else System.out.println(mnq.poll());
            } else {
                mnq.add(num);
            }
        }
    }
}
