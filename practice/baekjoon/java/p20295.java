
import java.io.*;
import java.util.*;

public class p20295 {
    static int N, M, logN;
    static int[] depth, store, isCandyExist = new int[6];
    static int[][] parent;
    static int[][] candy;
    static ArrayList<Integer>[] map;


    static void findLogN() {
        int check = 1;

        while (check <= N) {
            check <<= 1;
            logN ++;
        }
    }

    static void doBfs() {
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        queue.add(1);
        depth[1] = 1;

        while (!queue.isEmpty()) {
            int curt = queue.poll();

            for (int next : map[curt]) {
                if (depth[next] == 0) {
                    depth[next] = depth[curt] + 1;
                    parent[0][next] = curt;
                    candy[0][next] |= store[curt];
                    queue.add(next);
                }
            }
        }
    }

    static void findSparseTable() {
        for (int n = 1; n < logN; n ++) {
            for (int i = 1; i <= N; i ++) {
                parent[n][i] = parent[n - 1][parent[n - 1][i]];
                candy[n][i] = candy[n - 1][i] | candy[n - 1][parent[n - 1][i]];
            }
        }
    }

    static int lca(int a, int b, int want) {
        if (depth[a] < depth[b]) {
            return lca(b, a, want);
        }

        int canBuyCandy = store[a] | store[b];
        int depthDiff = (depth[a] - depth[b]);

        for (int n = 0; n <= logN; n ++) {
            if ((depthDiff & (1 << n)) > 0) {
                canBuyCandy |= candy[n][a];
                a = parent[n][a];
            }
        }

        if (a == b) {
            return canBuyCandy & (1 << want);
        }

        for (int n = (logN - 1); n >= 0; n --) {
            if (parent[n][a] != parent[n][b]) {
                canBuyCandy |= candy[n][a];
                canBuyCandy |= candy[n][b];
                a = parent[n][a];
                b = parent[n][b];
            }
        }

        canBuyCandy |= candy[0][a];
        canBuyCandy |= candy[0][b];

        return canBuyCandy & (1 << want);
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        map = new ArrayList[N + 1];
        findLogN();

        parent = new int[logN + 1][N + 1];
        candy = new int[logN + 1][N + 1];
        depth = new int[N + 1];
        store = new int[N + 1];

        st = new StringTokenizer(br.readLine());
        int candyNum;

        for (int i = 1; i <= N; i ++) {
            map[i] = new ArrayList<>();
            candyNum = Integer.parseInt(st.nextToken());
            store[i] = 1 << candyNum;
            isCandyExist[candyNum] = 1;
        }

        int u, v;

        for (int i = 0; i < (N - 1); i ++) {
            st = new StringTokenizer(br.readLine());
            u = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());
            map[u].add(v);
            map[v].add(u);
        }

        doBfs();
        findSparseTable();


//        for (int[][] matrix : candy) {
//            for (int[] line : matrix)System.out.println(Arrays.toString(line));
//            System.out.println();
//        }

        M = Integer.parseInt(br.readLine());

        int loc;
        st = new StringTokenizer(br.readLine());
        int curtLoc = Integer.parseInt(st.nextToken());
        int want = Integer.parseInt(st.nextToken());

        if (isCandyExist[want] == 0) {
            bw.write("CRY\n");
        } else {
            bw.write("PLAY\n");
        }

        for (int i = 1; i < M; i ++) {
            st = new StringTokenizer(br.readLine());
            loc = Integer.parseInt(st.nextToken());
            want = Integer.parseInt(st.nextToken());

            if (lca(curtLoc, loc, want) > 0) {
                bw.write("PLAY\n");
            } else {
                bw.write("CRY\n");
            }
            curtLoc = loc;
        }

        bw.flush();
        bw.close();
    }
}
