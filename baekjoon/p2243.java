import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class p2243 {
    static int[] candy;
    static int N, leaf_ptr = 1;

    static void put(int index, int value) {
        int ptr = leaf_ptr + index - 1;
        candy[ptr] += value;
        ptr /= 2;

        while (ptr >= 1) {
            candy[ptr] = candy[ptr * 2] + candy[ptr * 2 + 1];
            ptr /= 2;
        }
    }

    static int take_out(int order) {
        int ptr = 1;

        while (true) {
            if (ptr >= leaf_ptr) break;
            if (order <= candy[ptr * 2]) {
                ptr *= 2;
            } else {
                order -= candy[ptr * 2];
                ptr = (ptr * 2) + 1;
            }
        }
        int index = (ptr - leaf_ptr) + 1;
        put(index, -1);
        return index;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        while (leaf_ptr < 1000000) leaf_ptr *= 2;
        candy = new int[leaf_ptr * 2];
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < N; i ++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            if (A == 1) {
                sb.append(take_out(B) + "\n");
            } else {
                int C = Integer.parseInt(st.nextToken());
                put(B, C);
            }
        }

        System.out.println(sb);
    }
}
