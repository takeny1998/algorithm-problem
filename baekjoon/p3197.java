import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class p3197 {
    static class Area {
        int y, x, dist;
        Area (int y, int x, int dist) {
            this.y = y; this.x = x;
            this.dist = dist;
        }
    }

    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};

    static int[][] field;
    static int[] parents, swanIndex;
    static int R, C;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());


        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        field = new int[R][C];
        swanIndex = new int[2];

        // union-find 위한 배열 생성 및 초기화
        parents = new int[R * C];
        for (int i = 0; i < (R * C); i ++) {
            parents[i] = i;
        }

        // field 배열 입력받기
        int ptr = 0;
        for (int y = 0; y < R; y ++) {
            String[] line = br.readLine().split("");
            for (int x = 0; x < C; x ++) {
                switch(line[x]) {
                    case "L": swanIndex[ptr ++] = getIndexByCoord(y, x);
                    case ".": field[y][x] = 0; break;
                    case "X": field[y][x] = -1;
                }
            }
        }
        initParents();

        melt();
    }

    static void melt() {
        boolean[][] visited = new boolean[R][C];
        ArrayDeque<Area> queue = new ArrayDeque<>();

        // 동시에 녹이기 위해, 물웅덩이 모두 queue에 넣기
        for (int y = 0; y < R; y ++) {
            for (int x = 0; x < C; x ++) {
                if (field[y][x] == 0)
                    queue.add(new Area(y, x, 0));
            }
        }

        while (!queue.isEmpty()) {
            Area curt = queue.poll();


            for (int d = 0; d < 4; d ++) {
                Area next = new Area(curt.y + dy[d], curt.x + dx[d], curt.dist + 1);
                if (next.y < 0 || next.y >= R || next.x < 0 || next.x >= C) continue;

                unionParent(getIndexByCoord(curt.y, curt.x),
                        getIndexByCoord(next.y, next.x));

                if (checkParent()) {
                    System.out.println(field[next.y][next.x]);
                    return;
                }
                if (visited[next.y][next.x] || field[next.y][next.x] != -1) continue;

                field[next.y][next.x] = curt.dist + 1;
                visited[next.y][next.x] = true;
                queue.add(next);
            }
        }
    }

    static void initParents() {
        boolean[][] visited = new boolean[R][C];

        for (int y = 0; y < R; y ++) {
            for (int x = 0; x < C; x ++) {
                if (visited[y][x] || field[y][x] == -1) continue;

                ArrayDeque<Area> queue = new ArrayDeque<>();
                queue.add(new Area(y, x, 0));

                while (!queue.isEmpty()) {
                    Area curt = queue.poll();

                    for (int d = 0; d < 4; d ++) {
                        Area next = new Area(curt.y + dy[d], curt.x + dx[d], 0);
                        if (next.y < 0 || next.y >= R || next.x < 0 || next.x >= C) continue;
                        if (visited[next.y][next.x]) continue;
                        if (field[next.y][next.x] == -1) continue;

                        visited[next.y][next.x] = true;
                        unionParent(getIndexByCoord(curt.y, curt.x),
                                getIndexByCoord(next.y, next.x));

                        queue.add(next);
                    }
                }
            }
        }
    }

    static boolean checkParent() {
        return findParent(swanIndex[0]) == findParent(swanIndex[1]);
    }
    static int findParent(int index) {
        if (parents[index] == index) {
            return index;
        }
        return parents[index] = findParent(parents[index]);
    }

    static void unionParent(int a, int b) {
        a = findParent(a);
        b = findParent(b);

        if (a == b) return;

        if (a < b) parents[a] = b;
        else parents[b] = a;
    }

    static int getIndexByCoord(int y, int x) {
        return (y * C) + x;
    }

}
