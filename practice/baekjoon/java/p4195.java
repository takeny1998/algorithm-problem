
import java.io.*;
import java.util.*;

public class p4195 {

    private static final BufferedReader br = new BufferedReader(
        new InputStreamReader(System.in)
    );

    private static final BufferedWriter bw = new BufferedWriter(
        new OutputStreamWriter(System.out)
    );

    private static StringTokenizer st;

    private static Map<String, String> parents;

    private static Map<String, Integer> sizes;

    private static int T, F;

    private static String find(String x) {
        String p = parents.get(x);
        if (p.equals(x)) return x;

        String root = find(p);
        parents.put(x, root);
        return root;
    }

    private static void union(String a, String b) {
        a = find(a); b = find(b);

        if (a.equals(b)) return;

        if (sizes.get(a) < sizes.get(b)) {
            String t = a; a = b; b = t;
        }
        parents.put(b, a);
        sizes.put(a, sizes.get(a) + sizes.get(b));
    }

    private static void init(String a) {
        if (!parents.containsKey(a)) {
            parents.put(a, a);
            sizes.put(a, 1);
        }
    }

    public static void main(String[] args) throws Exception {

        T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t ++) {
            F = Integer.parseInt(br.readLine());

            parents = new HashMap<>();
            sizes = new HashMap<>();

            for (int f = 0; f < F; f ++) {
                st = new StringTokenizer(br.readLine());

                String a = st.nextToken();
                String b = st.nextToken();

                init(a); init(b);
                union(a, b);

                bw.write(sizes.get(find(a)) + "\n");
            }
        }

        bw.flush();
    }

}
