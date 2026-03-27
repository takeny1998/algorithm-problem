package p10815;

import java.io.*;
import java.util.*;

public class Main {
    
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static StringTokenizer st;

    private static int N, M;

    private static int[] array;
    
    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(br.readLine());        
        st = new StringTokenizer(br.readLine());
        
        array = new int[N];
        for (int n = 0; n < N; n ++) {
            array[n] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(array);

        M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        for (int m = 0; m < M; m ++) {
            int target = Integer.parseInt(st.nextToken());
            int left = 0, right = N - 1;

            int answer = 0;
            
            while (left <= right) {
                int mid = (left + right) / 2;
                if (array[mid] == target) {
                    answer = 1;
                    break;
                }
                if (array[mid] < target) left = mid + 1;
                else right = mid - 1;
            }
            bw.write(answer + " ");
        }
        bw.write("\n");
        bw.flush();
    }
}
