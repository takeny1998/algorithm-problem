import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class p10610 {
    static char[] N;
    static List<Integer> num = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = br.readLine().toCharArray();

        int sum = 0;

        for (int i = 0; i < N.length; i ++) {
            int elm = N[i] - '0';
            num.add(elm);
            sum += elm;
        }

        num.sort(Collections.reverseOrder());

        if (sum % 3 == 0 && num.contains(0)) {
            for (int i : num) System.out.print(i);
            return;
        }

        System.out.print(-1);
    }
}
