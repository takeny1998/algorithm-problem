package baekjoon;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class p1654 {
    static long[] cables;
    static int K, N;

    static long getCanMakeSlicedCables(long sliceLength) {
        long canMake = 0;
        for (long cable : cables) {
            canMake += cable / sliceLength;
        }

        return canMake;
    }

    static long findCableMaxLength() {
        long low = 0, high = cables[K - 1] + 1;

        while (low < high) {
            long mid = (low + high) / 2;
            if (getCanMakeSlicedCables(mid) < N) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }

        return low - 1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        K = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        cables = new long[K];
        for (int i = 0; i < K; i ++) {
            cables[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(cables);

        bw.write(findCableMaxLength() + "\n");
        bw.flush();
        bw.close();
    }
}
