package baekjoon;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class p1764 {
    private static int N, M;
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringTokenizer st;

    private static Set<String> nSet, mSet;

    public static void main(String[] args) throws IOException {

        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        nSet = IntStream.range(0, N)
                .mapToObj(i -> readLine())
                .collect(Collectors.toSet());

        mSet = IntStream.range(0, M)
                .mapToObj(i -> readLine())
                .collect(Collectors.toSet());

        List<String> intersect = nSet.stream()
                        .filter(mSet::contains)
                        .sorted()
                        .collect(Collectors.toList());

        bw.write(intersect.size() + "\n");

        for (String s : intersect) {
            bw.write(s + "\n");
        }

        bw.flush();
        bw.close();
    }

    private static String readLine() {
        try {
            return br.readLine();
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}