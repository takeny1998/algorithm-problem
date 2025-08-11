package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class p2357 {
	static int[] indexTree;
	static int N, M, leafPtr = 1;
	
	public static int find(int ptr, int l, int r, int tl, int tr) {
		if (tl > r || tr < l) {
			return 0;
		}
		
		if (tl <= l && r <= tr) {
			return indexTree[ptr];
		}
		
		int mid = (l + r) / 2;
		
		return find(ptr * 2, l, mid, tl, tr)
				+ find(ptr * 2 + 1, mid + 1, r, tl, tr);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		while (leafPtr < N) {
			leafPtr *= 2;
		}

		indexTree = new int[leafPtr * 2];
		
		for (int i = 0; i < N; i ++) {
			indexTree[leafPtr + i] = Integer.parseInt(br.readLine());
		}
		
		for (int i = (leafPtr - 1); i > 0; i --) {
			indexTree[i] = indexTree[i * 2] + indexTree[i * 2 + 1];
		}
		
		int a, b;
		
		for (int i = 0; i < M; i ++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			System.out.println(find(1, 1, leafPtr - 1, a, b));
		}
	}
}
