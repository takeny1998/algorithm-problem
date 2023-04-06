import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class p2473 {
    static long[] solution;
    static int N;
    static long minSum = Long.MAX_VALUE;
    static long[] answer = new long[3];

    static void mixThreeSolution(int fix) {
        int front = fix + 1;
        int rear = N - 1;

        while (front < rear) {
            long sum = solution[fix] + solution[front] + solution[rear];

            if (Math.abs(sum) < Math.abs(minSum)) {
                minSum = sum;
                answer[0] = solution[fix];
                answer[1] = solution[front];
                answer[2] = solution[rear];
            }

            if (sum == 0) return;
            if (sum < 0) front ++;
            if (sum > 0) rear --;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        solution = new long[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i ++) {
            solution[i] = Long.parseLong(st.nextToken());
        }

        Arrays.sort(solution);

        for (int i = 0; i < N - 2; i ++) {
            mixThreeSolution(i);
        }

        Arrays.sort(answer);
        for (long value : answer) {
            System.out.print(value + " ");
        }
    }
}
