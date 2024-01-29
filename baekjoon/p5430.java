import java.io.*;
import java.util.*;

public class P5430 {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringTokenizer st;
    private static int T, N;
    private static char[] commands;
    private static int[] numbers;

    private static boolean twoPointer() throws IOException {
        int low = 0, high = N;
        boolean isLow = true;

        for (char command : commands) {

            switch (command) {
                case 'R':
                    isLow = !isLow;
                    break;
                case 'D':
                    if (isLow) low ++;
                    else high --;
                    if (low > high) return true;
                    break;
            }
        }

        List<String> result = new ArrayList<>();

        for (int i = low; i < high; i ++) {
            result.add(String.valueOf(numbers[i]));
        }

        if(!isLow) Collections.reverse(result);

        bw.write("[" + String.join(",", result) + "]\n");

        return false;
    }


    public static void main(String[] args) throws IOException {

        T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i ++) {
            String command = br.readLine();
            commands = command.toCharArray();

            N = Integer.parseInt(br.readLine());
            numbers = new int[N];

            st = new StringTokenizer(br.readLine(), "[],");

            for (int r = 0; r < N; r ++) {
                numbers[r] = Integer.parseInt(st.nextToken());
            }

            boolean isError = twoPointer();
            if (isError) bw.write("error\n");
        }

        bw.flush();
        bw.close();
    }
}
