
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class p1292 {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static StringTokenizer st;

    private static int A, B;

    private static final List<Integer> list = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine());

        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());


        for (int n = 1; n <= B; n ++) {
            for (int r = 0; r < n; r ++) {
                list.add(n);
            }
        }

        int answer = 0;
        for (int i = A - 1; i < B; i ++) {
            answer += list.get(i);
        }

        bw.write(answer + "\n");
        bw.flush();

    }

}
