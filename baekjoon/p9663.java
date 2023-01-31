import java.util.Scanner;

public class Baekjoon_9663 {
    static int N, answer = 0;
    static boolean[] checkX, checkL, checkR;

    static void recursion(int y) {
        if (y == N) {
            answer ++;
            return;
        }

        for (int x = 0; x < N; x ++) {
            if (!checkX[x] && !checkL[x + y] && !checkR[x - y + N]) {
                checkX[x] = true;
                checkL[x + y] = true;
                checkR[x - y + N] = true;
                recursion(y + 1);
                checkX[x] = false;
                checkL[x + y] = false;
                checkR[x - y + N] = false;
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();

        checkX = new boolean[N];
        checkL = new boolean[N * 2];
        checkR = new boolean[N * 2];
        recursion(0);

        System.out.println(answer);
    }
}
