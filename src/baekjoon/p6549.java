package baekjoon;

import java.io.*;
import java.util.StringTokenizer;

public class p6549 {
    static int[] tree, num; // 구간의 최소값을 가지는 segment tree
    static long maxSize;
    static int n, leafPtr;

    static void initSegmentTree() {
        leafPtr = 1;
        while (leafPtr < n) {
            leafPtr <<= 1;
        }
        tree = new int[leafPtr * 2];
    }

    static int getMinHeightIndex(int index, int targetLeft, int targetRight, int left, int right) {
        if (left > targetRight || right < targetLeft) return 0;
        if (targetLeft <= left && right <= targetRight) return tree[index];

        int mid = (left + right) / 2;

        int leftNode = getMinHeightIndex(
                index * 2, targetLeft, targetRight, left, mid);
        int rightNode = getMinHeightIndex(
                (index * 2) + 1, targetLeft, targetRight, mid + 1, right);

        if (num[leftNode] <= num[rightNode]) return leftNode;
        else return rightNode;
    }

    static void findMaxSizeByRecursion(int left, int right) {
        int minHeightIndex = getMinHeightIndex(1, left, right, 1, leafPtr);

        long size = (long) num[minHeightIndex] * (right - left + 1);
        maxSize = Math.max(maxSize, size);

        if (left == right || left > right) return;

        // 최솟값 인덱스 기준으로, 왼쪽 사각형이 존재할 때
        if (minHeightIndex > 1)
            findMaxSizeByRecursion(left, minHeightIndex - 1);

        // 오른쪽 사각형이 존재할 때
        if (minHeightIndex < n)
            findMaxSizeByRecursion(minHeightIndex + 1, right);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());

            if (n == 0) break;

            maxSize = 0;
            initSegmentTree();
            
            // 히스토그램의 높이 배열 채우기
            num = new int[n + 1];
            num[0] = Integer.MAX_VALUE;
            for (int i = 1; i <= n; i ++) {
                num[i] = Integer.parseInt(st.nextToken());
            }
            
            // leaf node에 각 인덱스를 할당
            for (int i = leafPtr; i < (leafPtr + n); i ++) {
                tree[i] = (i - leafPtr) + 1;
            }

            // internal node에 각 구간의 "최솟값의 인덱스" 를 할당
            for (int i = (leafPtr - 1); i >= 1; i --) {
                int left = tree[i * 2];
                int right = tree[(i * 2) + 1];

                if (num[left] <= num[right]) tree[i] = left;
                 else tree[i] = right;
            }

            findMaxSizeByRecursion(1, n);
            bw.write(maxSize + "\n");
        }

        bw.flush();
        bw.close();
    }
}