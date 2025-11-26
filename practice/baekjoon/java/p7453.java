
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class p7453 {
    static int[] A, B, C, D;
    static long[] caseAB, caseCD;
    static int N;

    static long[] makeCase(int[] arrA, int[] arrB) {
        long[] result = new long[N * N];
        int ptr = 0;

        for (int a : arrA) {
            for (int b : arrB) {
                result[ptr ++] = a + b;
            }
        }
        Arrays.sort(result);
        return result;
    }

    static long find(long[] caseAB, long[] caseCD) {
        long answer = 0;
        int left = 0, right = caseCD.length - 1;

        while (left < caseAB.length && right >= 0) {
            long sum = caseAB[left] + caseCD[right];
            if (sum == 0) {
                long leftVal = caseAB[left];
                long cntLeft = 0;
                while (left < caseAB.length && caseAB[left] == leftVal) {
                    left ++;
                    cntLeft ++;
                }

                long rightVal = caseCD[right];
                long cntRight = 0;
                while (right >= 0 && caseCD[right] == rightVal) {
                    right --;
                    cntRight ++;
                }
                answer += (cntLeft * cntRight);
            } else if (sum < 0) {
                left ++;
            } else {
                right --;
            }
        }

        return answer;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;


        N = Integer.parseInt(br.readLine());
        A = new int[N]; B = new int[N];
        C = new int[N]; D = new int[N];

        for (int i = 0; i < N; i ++) {
            st = new StringTokenizer(br.readLine());
            A[i] = Integer.parseInt(st.nextToken());
            B[i] = Integer.parseInt(st.nextToken());
            C[i] = Integer.parseInt(st.nextToken());
            D[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(A);
        Arrays.sort(B);
        Arrays.sort(C);
        Arrays.sort(D);

        caseAB = makeCase(A, B);
        caseCD = makeCase(C, D);

        long answer = find(caseAB, caseCD);

        System.out.println(answer);
    }
}
