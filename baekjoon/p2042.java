import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class p2042 {
    static long[] index_tree = new long[3000000];
    static int N, M, K, leaf_ptr = 1;

    static long get_subtotal(int ptr, int left, int right, int start, int end) {
        // pointer out of range
        if (end < left || start > right) return 0;
        if (start <= left && right <= end) {
            return index_tree[ptr];
        }

        int next = (left + right) / 2;
        return get_subtotal(ptr * 2, left, next, start, end)
                + get_subtotal(ptr * 2 + 1, next + 1, right, start, end);
    }

    static void edit_tree(int index, long value) {
        int ptr = leaf_ptr + index - 1;
        index_tree[ptr] = value;
        ptr /= 2;

        while(ptr >= 1){
            index_tree[ptr] = index_tree[ptr * 2] + index_tree[ptr * 2 + 1];
            ptr /= 2;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        while (leaf_ptr < N) leaf_ptr *= 2;

        for (int i = 0 ; i < N; i ++) {
            index_tree[leaf_ptr + i] = Long.parseLong(br.readLine());
        }

        for (int i = (leaf_ptr - 1); i >= 1; i --) {
            index_tree[i] = index_tree[i * 2] + index_tree[(i * 2) + 1];
        }


        for (int i = 0; i < M + K; i ++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (a == 1) {
                long c = Long.parseLong(st.nextToken());
                edit_tree(b, c);
            } else {
                int c = Integer.parseInt(st.nextToken());
                System.out.println(get_subtotal(1, 1, leaf_ptr, b, c));
            }

        }


    }
}
