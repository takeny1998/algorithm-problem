package baekjoon;

import java.io.*;

public class p1541 {
    
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
    public static void main(String[] args) throws Exception {
        
        String[] exprs = br.readLine().split("-");
        
        int answer = 0;

        for (int i = 0; i < exprs.length; i ++) {
            
            String expr = exprs[i];

            int num = 0;

            if (expr.contains("+")) {
                String[] hands = expr.split("\\+");
                for (int r = 0; r < hands.length; r ++) {
                    num += Integer.parseInt(hands[r]);
                }
            } else {
                num = Integer.parseInt(expr);
            }
            
            if (i == 0) answer += num;
            else answer -= num;
        }

        bw.write(answer + "\n");
        bw.flush();
    }
}
