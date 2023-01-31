import java.util.Arrays;
import java.util.Scanner;

public class p1039 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        final int N = sc.nextInt();

        int[] input = new int[3], temp = new int[3],
                min = new int[3], max = new int[3];

        for (int i = 0; i < N; i ++) {
            for (int r = 0; r < 3; r ++)
                input[r] = sc.nextInt();;

            temp[0] = Math.min(min[0], min[1]) + input[0];
            temp[1] = Math.min(min[0], Math.min(min[1], min[2])) + input[1];
            temp[2] = Math.min(min[1], min[2]) + input[2];
            System.arraycopy(temp, 0, min, 0, 3);

            temp[0] = Math.max(max[0], max[1]) + input[0];
            temp[1] = Math.max(max[0], Math.max(max[1], max[2])) + input[1];
            temp[2] = Math.max(max[1], max[2]) + input[2];
            System.arraycopy(temp, 0, max, 0, 3);
        }

        System.out.printf("%d %d",
                Math.max(max[0], Math.max(max[1], max[2])),
                Math.min(min[0], Math.min(min[1], min[2])));
    }
}
