
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class p11266 {
    static int V, E, order = 0;
    static boolean[] isCutVertex;
    static int[] searchOrder;
    static ArrayList<Integer>[] map;

    static int dfs(int index, boolean isRootNode) {
        order ++;
        searchOrder[index] = order ;
        int lowOrder = order;
        int childNode = 0;
        for (int nextNode : map[index]) {
            if (searchOrder[nextNode] == 0) {
                childNode ++;
                int nextLow = dfs(nextNode, false);
                if (!isRootNode && searchOrder[index] <= nextLow) {
                    isCutVertex[index] = true;
                }
                lowOrder = Math.min(lowOrder, nextLow);
            } else {
                lowOrder = Math.min(lowOrder, searchOrder[nextNode]);
            }
        }

        if (isRootNode && childNode >= 2) {
            isCutVertex[index] = true;
        }

        return lowOrder;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        map = new ArrayList[V + 1];
        isCutVertex = new boolean[V + 1];
        searchOrder = new int[V + 1];

        for (int i = 1; i <= V; i ++) {
            map[i] = new ArrayList<>();
        }

        for (int i = 0; i < E; i ++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            map[A].add(B);
            map[B].add(A);
        }

        for (int i = 1; i <= V; i ++) {
            if (searchOrder[i] == 0) {
                dfs(i, true);
            }
        }

        int count = 0;
        StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= V; i ++) {
            if (isCutVertex[i]) {
                count ++;
                sb.append(i + " ");
            }
        }

        System.out.println(count);
        System.out.println(sb);
    }


}
