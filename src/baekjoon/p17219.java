package baekjoon;

import java.io.*;
import java.util.*;

public class p17219 {
    
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static final Map<String, String> pwStore = new HashMap<>();

    private static StringTokenizer st;

    private static int N, M;

    public static void main(String[] args) throws Exception {
        
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i ++) {
            st = new StringTokenizer(br.readLine());

            pwStore.put(st.nextToken(), st.nextToken());
        }

        for (int m = 0; m < M; m ++) {
            bw.write(pwStore.get(br.readLine()) + "\n");
        }
        bw.flush();
    }

}
