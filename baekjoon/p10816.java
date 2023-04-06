import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class p10816 {
    static int[] cards;
    static int N, M;

    static int upperBound(int target) {
        int low = 0, high = N;

        while (low < high) {
            int mid = (low + high) / 2;

            if (target < cards[mid]) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }

        return low;
    }

    static int lowerBound(int target) {
        int low = 0, high = N;

        while (low < high) {
            int mid = (low + high) / 2;

            if (target <= cards[mid]) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }

        return low;
    }

    static int getNumOfCard(int cardNum) {
        return upperBound(cardNum) - lowerBound(cardNum);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        cards = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i ++) {
            cards[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(cards);

        M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i ++) {
            int numOfCard = getNumOfCard(Integer.parseInt(st.nextToken()));
            bw.write( numOfCard + " ");
        }

        bw.flush();
        bw.close();
    }
}
