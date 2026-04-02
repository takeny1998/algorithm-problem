package p1686;

import java.io.*;
import java.util.*;

public class Main {
    
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static StringTokenizer st;

    private static int V, M;
    
    private static double SY, SX, TY, TX;

    private static List<double[]> vertexs;

    private static List<double[]>[] edges;

    private static Queue<int[]> queue;

    private static boolean[] visited;

    private static int[] dist;

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken()) * 60;

        vertexs = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        SY = Double.parseDouble(st.nextToken());
        SX = Double.parseDouble(st.nextToken());
        vertexs.add(new double[] { SY, SX });

        st = new StringTokenizer(br.readLine());
        TY = Double.parseDouble(st.nextToken());
        TX = Double.parseDouble(st.nextToken());
        vertexs.add(new double[] { TY, TX });
        
        String line = null;

        while ((line = br.readLine()) != null) {
            st = new StringTokenizer(line);
            
            double y = Double.parseDouble(st.nextToken());
            double x = Double.parseDouble(st.nextToken());

            vertexs.add(new double[] { y, x });
        }

        edges = new ArrayList[vertexs.size()];
        for (int i = 0; i < vertexs.size(); i ++) {
            edges[i] = new ArrayList<>();
        }

        for (int i = 0; i < vertexs.size(); i ++) {
            for (int r = i + 1; r < vertexs.size(); r ++) {
                double[] a = vertexs.get(i);
                double[] b = vertexs.get(r);

                double dist = Math.sqrt(Math.pow(a[0] - b[0], 2) + Math.pow(a[1] - b[1], 2));
                edges[i].add(new double[] { r, dist });
                edges[r].add(new double[] { i, dist });
            }
        }

        queue = new LinkedList<>();
        visited = new boolean[vertexs.size()];
        dist = new int[vertexs.size()];

        queue.add(new int[] { 0, 0 });
        visited[0] = true;

        while (!queue.isEmpty()) {

            int[] curt = queue.poll();

            for (double[] next : edges[curt[0]]) {
                int ni = (int) next[0];
                if (visited[ni] || next[1] > (V * M)) continue;

                visited[ni] = true;
                dist[ni] = curt[1] + 1;
                
                queue.add(new int[] { ni, dist[ni] });
            }
        }

        if (visited[1]) {
            int ans = Math.max(0, dist[1] - 1);
            bw.write("Yes, visiting " + ans + " other holes.\n");
        } else {
            bw.write("No.");
        }



        bw.flush();


    }
}
