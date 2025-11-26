
import java.io.*;

public class p2839 {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static int N;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());

        // 분수 배낭 문제
        // 3kg 5kg 봉지를 마음 껏 쪼갤 수 있으니
        // 5KG를 최대한 많이 쓰는 것에서 1씩 줄여 나가면서 계산한다
        int fiveNum = N / 5;
        int minNum = -1;

        while (fiveNum >= 0) {
            int weight = N - (fiveNum * 5);

            if (weight % 3 == 0) {
                int threeNum = weight / 3;
                minNum = fiveNum + threeNum;
                break;
            }

            fiveNum --;
        }

        bw.write(minNum + "");
        bw.flush();
        bw.close();
    }
}
