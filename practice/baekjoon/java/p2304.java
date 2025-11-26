
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class p2304 {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static StringTokenizer st;

    private static int N;

    private static final int[] ys = new int[1500];

    private static final boolean[] visisted = new boolean[1500];

    private static int leftMax = 0, leftMaxSize = 0, rightMax = 0, rightMaxSize = 0;

    private static int leftSize = 0, rightSize = 0, leftPtr = 0;

    public static void main(String[] args) throws Exception {

        N = Integer.parseInt(br.readLine());

        Arrays.fill(ys, 0);
        Arrays.fill(visisted, false);

        for (int n = 0; n < N; n ++) {
            st = new StringTokenizer(br.readLine());

            ys[Integer.parseInt(st.nextToken())] = Integer.parseInt(st.nextToken());
        }


        for (int i = 0; i < 1500; i ++) {
            int y = ys[i];

            leftMaxSize ++;

            if (y < leftMax) {
                continue;
            }

            leftSize += (leftMax * leftMaxSize);

            leftMaxSize = 0;
            leftMax = y;
            leftPtr = i;
        }

        for (int i = 1499; i >= leftPtr; i --) {

            int y = ys[i];

            rightMaxSize ++;

            if (y < rightMax || visisted[i]) {
                continue;
            }

            rightSize += (rightMax * rightMaxSize);

            rightMaxSize = 0;
            rightMax = y;
        }


        bw.write((leftSize + rightSize + leftMax) + "\n");

        bw.flush();
    }

}
