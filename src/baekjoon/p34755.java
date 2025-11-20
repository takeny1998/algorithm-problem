package baekjoon;

import java.io.*;
import java.util.*;

public class p34755 {
    
    private static class Marine implements Comparable<Marine> {

        int rank, honor, serial;

        Marine(int rank, int honor, int serial) {
            this.rank = rank;
            this.honor = honor;
            this.serial = serial;
        }

        @Override
        public int compareTo(Marine marine) {
            if (marine == null) return 1;

            if (rank == marine.rank) {
                if (honor == marine.honor) {
                    return Integer.compare(marine.serial, serial);
                }
                return Integer.compare(honor, marine.honor);
            }
            return Integer.compare(rank, marine.rank);
        }

    }

    private static final BufferedReader br = new BufferedReader(
        new InputStreamReader(System.in)
    );

    private static final BufferedWriter bw = new BufferedWriter(
        new OutputStreamWriter(System.out)
    );

    private static StringTokenizer st;

    private static int N, Q;

    private static int[] ranks, honors;

    private static Marine[] marines;

    private static Map<Integer, Marine> marineIndex;

    private static int upperBound(int target) {
        
        int low = 1, high = N;

        while (low < high) {
            int mid = (low + high) / 2;
            if (mid <= target) low = mid + 1;
            else high = mid;
        }

        return high;
    }

    public static void main(String[] args) throws Exception {
        
        N = Integer.parseInt(br.readLine());

        ranks = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i ++) {
            ranks[i] = Integer.parseInt(st.nextToken());
        }

        honors = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i ++) {
            honors[i] = Integer.parseInt(st.nextToken());
        }
        
        marineIndex = new HashMap<>();
        marines = new Marine[N + 1];
        for (int i = 1; i <= N; i ++) {
            Marine marine = new Marine(ranks[i], honors[i], i);
            marines[i] = marine;
            marineIndex.put(i, marine);
        }
        Arrays.sort(marines);

        Q = Integer.parseInt(br.readLine());

        for (int q = 0; q < Q; q ++) {

            st = new StringTokenizer(br.readLine());
            
            int command = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            Marine marine = marineIndex.get(to);

            if (command == 1) {
                marine.rank = Integer.parseInt(st.nextToken());
                marine.honor = Integer.parseInt(st.nextToken());

            } else {
                Marine senior = marines[upperBound(to)];
                int diff = marine.honor / 2;

                if (marine.serial == senior.serial || (marine.rank == senior.rank && marine.honor == senior.honor)) {
                    marine.honor -= diff;
                    bw.write(marine.rank + " " + marine.honor + " -1 -1\n");
                } else {
                    marine.honor -= diff;
                    senior.honor += diff;
                    bw.write(marine.rank + " " + marine.honor + " " + senior.rank + " " + senior.honor + "\n");
                }
            }
            
            Arrays.sort(marines);
        }
        bw.flush();
    }
}
