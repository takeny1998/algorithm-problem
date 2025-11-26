
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class p7578 {
    static int[] num, indexTree, other;
    static HashMap<Integer, Integer> otherIndex = new HashMap<>();
    static int N, leafPtr;
    static long answer = 0;

    static void put(int index, int value) {
        int ptr = leafPtr + index - 1;
        indexTree[ptr] = value;
        ptr /= 2;

        while (ptr >= 1) {
            indexTree[ptr] = indexTree[ptr * 2] + indexTree[ptr * 2 + 1];
            ptr /= 2;
        }
    }

    static int get(int ptr, int l, int r, int tl, int tr) {
        if (l > tr || r < tl) {
            return 0;
        }

        if (tl <= l && r <= tr) {
            return indexTree[ptr];
        }
        int mid = (l + r) / 2;

        return get(ptr * 2, l, mid, tl, tr) +
                get(ptr * 2 + 1,mid + 1, r, tl, tr);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        num = new int[N + 1];

        leafPtr = 1;
        while (leafPtr < N) {
            leafPtr *= 2;
        }

        indexTree = new int[leafPtr * 2];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i ++) {
            num[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i ++) {
            otherIndex.put(Integer.parseInt(st.nextToken()), i);
        }

        for (int i = 1; i <= N; i ++) {
            int other = otherIndex.get(num[i]);
            put(other, 1);
            answer += get(1, 1, leafPtr, other + 1, leafPtr);
        }

        System.out.println(answer);
    }
}
