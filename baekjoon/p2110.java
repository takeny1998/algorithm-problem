import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class p2110 {
    static int[] router;
    static int C, N;

    // 최소 거리를 지키면서 설치가능한 공유기의 수를 구함.
    static int getCanInstallWithMinimumDist(int minDist) {
        int canInstall = 1;
        int lastInstallLoc = router[0];

        for (int i = 1; i < N; i ++) {
            if ((router[i] - lastInstallLoc) >= minDist) {
                canInstall ++;
                lastInstallLoc = router[i];
            }
        }
        return canInstall;
    }

    static int findMaxDistBetweenRouter() {
        // 최소 거리는 1, 최대 거리는 공유기 간 최대 거리
        int low = 1, high = router[N - 1] - router[0] + 1;
        /*
            가장 인접한 두 공유기 사이의 "최대 거리"를 구하므로, upper-bound를 사용해야 함.

            거리의 범위가 dist = {1, 2, 3, 4, 5} 이고,
            설치가능한 공유기가 각각 router = {5, 3, 3, 2, 2, 2} 일 때

            C = 3 이면 C의 upper-bound 인 dist = 4와 router = 2를 구하면, 그 전 dist 값인 3이 정답임.
         */

        while (low < high) {
            int minDist = (low + high) / 2;
            int canInstalledRouter = getCanInstallWithMinimumDist(minDist);

            if (canInstalledRouter >= C) {
                low = minDist + 1;
            } else {
                high = minDist;
            }
        }
        return low - 1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());


        router = new int[N];

        for (int i = 0; i < N; i ++) {
            int loc = Integer.parseInt(br.readLine());
            router[i] = loc;
        }

        Arrays.sort(router);
        System.out.println(findMaxDistBetweenRouter());
    }
}
