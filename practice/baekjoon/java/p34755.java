import java.io.*;
import java.util.*;

public class p34755 {

    private static class Marine implements Comparable<Marine> {

        long rank, honor, serial;

        Marine(long rank, long honor, long serial) {
            this.rank = rank;
            this.honor = honor;
            this.serial = serial;
        }

        @Override
        public int compareTo(Marine marine) {
            if (rank == marine.rank) {
                if (honor == marine.honor) {
                    return Long.compare(marine.serial, serial);
                }
                return Long.compare(honor, marine.honor);
            }
            return Long.compare(rank, marine.rank);
        }
    }

    private static final BufferedReader br = new BufferedReader(
        new InputStreamReader(System.in)
    );

    private static final BufferedWriter bw = new BufferedWriter(
        new OutputStreamWriter(System.out)
    );

    private static StringTokenizer st1, st2;

    private static int N, Q;

    private static TreeSet<Marine> marines = new TreeSet<>();

    private static TreeMap<Long, Marine> serialMap = new TreeMap<>();

    private static Marine findSenior(Marine target) {
        Marine key = new Marine(target.rank, target.honor, 0);
        return marines.higher(key);
    }

    public static void main(String[] args) throws Exception {

        N = Integer.parseInt(br.readLine());

        st1 = new StringTokenizer(br.readLine());
        st2 = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i ++) {
            long rank = Long.parseLong(st1.nextToken());
            long honor = Long.parseLong(st2.nextToken());
            long serial = i + 1;

            Marine marine = new Marine(rank, honor, serial);
            marines.add(marine);
            serialMap.put(serial, marine);
        }

        Q = Integer.parseInt(br.readLine());

        for (int q = 0; q < Q; q ++) {
            st1 = new StringTokenizer(br.readLine());

            int comm = Integer.parseInt(st1.nextToken());

            Marine marine = serialMap.get(Long.parseLong(st1.nextToken()));
            marines.remove(marine);

            if (comm == 1) {
                marine.rank = Long.parseLong(st1.nextToken());
                marine.honor = Long.parseLong(st1.nextToken());

            } else {
                Marine senior = findSenior(marine);

                long seniorRank = -1;
                long seniorHonor = -1;

                if (senior != null) {
                    marines.remove(senior);

                    senior.honor += marine.honor / 2;
                    seniorRank = senior.rank;
                    seniorHonor = senior.honor;

                    marines.add(senior);
                }

                marine.honor -= marine.honor / 2;
                bw.write(marine.rank + " " + marine.honor + " " + seniorRank + " " + seniorHonor + "\n");
            }

            marines.add(marine);
        }

        bw.flush();

    }
}
