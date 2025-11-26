
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class p1082 {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static StringTokenizer st;

    private static Number[] P;

    private static int N, M;

    private static List<Number> answer = new ArrayList<>();

    private static class Number implements Comparable<Number> {
        private final int index;
        private final int price;

        public Number(int index, int price) {
            this.index = index;
            this.price = price;
        }

        @Override
        public int compareTo(Number b) {
            if (price == b.price) {
                return b.index - index;
            }
            return price - b.price;
        }

    }

    public static void main(String[] args) throws Exception {

        N = Integer.parseInt(br.readLine());
        P = new Number[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i ++) {
            P[i] = new Number(i, Integer.parseInt(st.nextToken()));
        }

        M = Integer.parseInt(br.readLine());

        Arrays.sort(P);

        int m = M;

        if (P[0].index == 0) {
            if (N == 1 || m < P[1].price) {
                bw.write("0\n");
                bw.flush();
                return;
            }
            m -= P[1].price;
            answer.add(P[1]);
        }

        while (m >= P[0].price) {
            answer.add(P[0]);
            m -= P[0].price;
        }

        for (int i = 0; i < answer.size(); i ++) {

            Number curt = answer.get(i);
            m += curt.price;

            for (int r = 0; r < N; r ++) {
                if (P[r].index <= curt.index) continue;
                if (m < P[r].price) continue;

                curt = P[r];
            }

            m -= curt.price;
            bw.write(curt.index + "");
        }

        bw.write("\n");
        bw.flush();
    }

}
