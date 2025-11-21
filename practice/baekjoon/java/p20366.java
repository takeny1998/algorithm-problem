package baekjoon;

import java.io.*;
import java.util.*;

public class p20366 {
    
    private static final BufferedReader br = 
            new BufferedReader(new InputStreamReader(System.in));

    private static final BufferedWriter bw = 
            new BufferedWriter(new OutputStreamWriter(System.out));

    private static StringTokenizer st;

    private static int[] array;

    private static int N, answer = Integer.MAX_VALUE;

    private static void fourPointer() {

        for (int ol = 0; ol < (N - 3); ol ++) {
            for (int or = ol + 3; or < N; or ++) {
                int il = ol + 1, ir = or - 1;
                
                while (il < ir) {
                    int inner = array[il] + array[ir];
                    int outter = array[ol] + array[or];

                    answer = Math.min(answer, Math.abs(outter - inner));

                    if (inner <= outter) il ++;
                    else ir --;
                }
            }
        }

    }

    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(br.readLine());
        array = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int n = 0; n < N; n ++) {
            array[n] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(array);

        fourPointer();

        bw.write(answer + "\n");
        bw.flush();
    }
}
