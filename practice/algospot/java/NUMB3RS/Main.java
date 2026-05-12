package algospot.java.NUMB3RS;

import java.io.*;
import java.util.*;

public class Main {
    
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static StringTokenizer st;
    
    private static final int MAX_N = 51, MAX_D = 101; 

    private static int C, N, D, T, P, Q;

    private static int graph[][], degree[];

    private static double cache[][];

    private static double find(int d, int i) {
        if (d == D) {
            return i == Q ? 1.0 : 0.0;
        }

        if (cache[d][i] != -1) return cache[d][i];

        double ret = 0;

        for (int ni = 0; ni < N; ni ++) {
            if (i == ni || graph[i][ni] == 0) continue;
            ret += (find(d + 1, ni));
        }
        return cache[d][i] = (ret / degree[i]);
    }
    
    public static void main(String[] args) throws Exception {
        C = Integer.parseInt(br.readLine());

        for (int c = 0; c < C; c ++) {
            st = new StringTokenizer(br.readLine());
            
            N = Integer.parseInt(st.nextToken());
            D = Integer.parseInt(st.nextToken());
            P = Integer.parseInt(st.nextToken());

            graph = new int[N][N];
            degree = new int[N];

            for (int y = 0; y < N; y ++) {

                st = new StringTokenizer(br.readLine());
                for (int x = 0; x < N; x ++) {
                    graph[y][x] = Integer.parseInt(st.nextToken());
                    if (graph[y][x] == 1) degree[y] ++;
                }
            }

            T = Integer.parseInt(br.readLine());

            st = new StringTokenizer(br.readLine());

            for (int t = 0; t < T; t ++) {
                cache = new double[MAX_D][MAX_N];
                for (int d = 0; d < D; d ++)
                    Arrays.fill(cache[d], -1.0);
                
                Q = Integer.parseInt(st.nextToken());
                bw.write(String.format("%.8f", find(0, P)) + " ");
            }
            bw.write("\n");
        }
        bw.flush();
    }

}