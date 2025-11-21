package baekjoon;

import java.io.*;
import java.util.*;

public class p30804 {
    
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static StringTokenizer st;

    private static int[] arr;

    private static int N, answer = 1;

    private static final Map<Integer, Integer> counter = new HashMap<>();

    private static void increase(int n) {
        counter.put(arr[n], counter.getOrDefault(arr[n], 0) + 1);
    }

    private static void decrease(int n) {
        int count = counter.get(arr[n]);
        
        if (count == 1) {
            counter.remove(arr[n]);
            return;
        }
        counter.put(arr[n], count - 1);
    }

    private static void twoPointer() {

        int left = 0, right = 0;
        increase(0);

        while (left <= right) {
            while(right < (N - 1)) {
                right ++;
                increase(right);

                if (counter.size() > 2) {
                    decrease(right --);
                    answer = Math.max(answer, (right - left) + 1);
                    break;
                }
                answer = Math.max(answer, (right - left) + 1);
            }
            decrease(left);
            left ++;
        }

    }


    public static void main(String[] args) throws Exception {
   
        N = Integer.parseInt(br.readLine());
        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int n = 0; n < N; n ++) {
            arr[n] = Integer.parseInt(st.nextToken());
        }


        twoPointer();

        bw.write(answer + "\n");
        bw.flush();
    }
}
