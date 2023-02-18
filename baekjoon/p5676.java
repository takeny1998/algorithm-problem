import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class p5676 {
    static int N, K, leafIndex;
    static int[] tree;

    static void put(int index, int value) {
        int ptr = leafIndex + (index - 1);
        tree[ptr] = value;
        ptr /= 2;

        while (ptr >= 1) {
            tree[ptr] = tree[ptr * 2] * tree[(ptr * 2) + 1];
            ptr /= 2;
        }
    }

    static int get(int index, int left, int right, int targetLeft, int targetRight) {
        if (right < targetLeft || left > targetRight) {
            return 1;
        }

        if (targetLeft <= left && right <= targetRight) {
            return tree[index];
        }

        int mid = (left + right) / 2;

        return get(index * 2, left, mid, targetLeft, targetRight) *
                get((index * 2) + 1, mid + 1, right, targetLeft, targetRight);
    }

    static int transNum(int num) {
        if (num > 0) return 1;
        else if (num < 0) return -1;
        else return 0;
    }

    public static void main (String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String line = "";


        while ((line = br.readLine()) != null) {
            StringTokenizer st = new StringTokenizer(line);
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            // calculate segment tree size
            leafIndex = 1;
            while (leafIndex < N) {
                leafIndex *= 2;
            }
            tree = new int[leafIndex * 2];

            // input leaf node
            st = new StringTokenizer(br.readLine());

            for (int i = 0; i < (leafIndex * 2); i ++) {
                tree[i] = 1;
            }

            for (int i = 0; i < N; i ++) {
                tree[leafIndex + i] = transNum(Integer.parseInt(st.nextToken()));
            }

            // init segment tree
            for (int i = (leafIndex - 1); i >= 1; i --) {
                tree[i] = tree[i * 2] * tree[(i * 2) + 1];
            }

            String C;
            int I, V, J;

            for (int i = 0; i < K; i ++) {
                st = new StringTokenizer(br.readLine());

                C = st.nextToken();

                if (C.equals("C")) {
                    I = Integer.parseInt(st.nextToken());
                    V = transNum(Integer.parseInt(st.nextToken()));
                    put(I, V);
                } else if (C.equals("P")) {
                    I = Integer.parseInt(st.nextToken());
                    J = Integer.parseInt(st.nextToken());
                    int result = get(1, 1, leafIndex, I, J);
                    if (result > 0) {
                        sb.append("+");
                    } else if (result < 0) {
                        sb.append("-");
                    } else {
                        sb.append("0");
                    }
                }

            }

            sb.append("\n");
        }
        System.out.println(sb);
    }
}
