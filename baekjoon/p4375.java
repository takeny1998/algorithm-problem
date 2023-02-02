import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class p4375 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            String line = br.readLine();
            if (line == null) break;

            BigInteger num = new BigInteger(line);
            BigInteger div = new BigInteger("1");
            int answer = 0;

            while (true) {
                answer ++;
                if (div.mod(num).equals(BigInteger.ZERO)) break;
                div = new BigInteger(div.mod(num) + "1");
            }

            System.out.println(answer);
        }
    }
}
