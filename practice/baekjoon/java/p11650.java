package baekjoon;

import java.io.*;
import java.util.*;

public class p11650 {
    
    private static class Coordinate implements Comparable<Coordinate> {

        private int y, x;

        Coordinate(int y, int x) {
            this.y = y; this.x = x;
        }

        @Override
        public int compareTo(Coordinate coordinate) {
            if (this.y == coordinate.y) {
                return Integer.compare(this.x, coordinate.x);
            }

            return Integer.compare(this.y, coordinate.y);
        }

    }

    private static final BufferedReader br = 
            new BufferedReader(new InputStreamReader(System.in));

    private static final BufferedWriter bw = 
            new BufferedWriter(new OutputStreamWriter(System.out));

    private static StringTokenizer st;

    private static int N;

    private static List<Coordinate> list;

    public static void main(String[] args) throws Exception {

        N = Integer.parseInt(br.readLine());
        list = new ArrayList<>();

        for (int i = 0; i < N; i ++) {
            st = new StringTokenizer(br.readLine());

            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());

            list.add(new Coordinate(y, x));
        }

        Collections.sort(list);

        for (Coordinate coordinate : list) {
            bw.write(coordinate.y + " " + coordinate.x + "\n");
        }
        bw.flush();
    }

}
