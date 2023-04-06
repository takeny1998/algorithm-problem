import java.io.*;
import java.util.*;

public class p5419 {
    static class Island implements Comparable<Island> {
        int y, x;
        Island (int y, int x) {
            this.y = y;
            this.x = x;
        }
        @Override
        public int compareTo(Island island) {
            if (this.y == island.y) {
                return Integer.compare(this.x, island.x) * -1;
            }
            return Integer.compare(this.y, island.y);
        }
    }

    static PriorityQueue<Island> maxQueue;
    static ArrayList<Integer> xList;
    static HashMap<Integer, Integer> xDict;
    static int[] segmentTree;
    static int N, T, leafPtr;

    static void initSegmentTree() {
        leafPtr = 1;

        while (leafPtr < xDict.size()) {
            leafPtr <<= 1;
        }

        segmentTree = new int[leafPtr * 2];
    }

    static void putSegmentTree(int index, int value) {
        int ptr = (leafPtr + index) - 1;
        segmentTree[ptr] += value;
        ptr /= 2;

        while (ptr >= 1) {
            segmentTree[ptr] = segmentTree[ptr * 2] + segmentTree[(ptr * 2) + 1];
            ptr /= 2;
        }
    }

    static int getSegmentTree(int ptr, int left, int right, int targetLeft, int targetRight) {
        if (left > targetRight || right < targetLeft) {
            return 0;
        }

        if (targetLeft <= left && right <= targetRight) {
            return segmentTree[ptr];
        }

        int mid = (left + right) / 2;

        return getSegmentTree(ptr * 2, left, mid, targetLeft, targetRight)
                + getSegmentTree(ptr * 2 + 1, mid + 1, right, targetLeft, targetRight);
    }

    static long count() {
        long count = 0;
        while (!maxQueue.isEmpty()) {
            Island island = maxQueue.poll();
            int zipX = xDict.get(island.x);
            count += getSegmentTree(1, 1, leafPtr, 1, zipX);
            putSegmentTree(zipX, 1);
        }

        return count;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t ++) {
            N = Integer.parseInt(br.readLine());

            maxQueue = new PriorityQueue<>(Comparator.reverseOrder());
            xList = new ArrayList<>();
            xDict = new HashMap<>();

            for (int i = 0; i < N; i ++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());

                maxQueue.add(new Island(y, x));
                xList.add(x);
            }

            Collections.sort(xList);
            int order = 1;
            for (int x : xList) {
                if (!xDict.containsKey(x)) {
                    xDict.put(x, order ++);
                }
            }

            initSegmentTree();

            bw.write(count() + "\n");
        }
        bw.flush();
        bw.close();
    }
}
