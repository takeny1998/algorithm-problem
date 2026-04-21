package algospot.java.QUARDTREE;

import java.io.*;
import java.util.*;

public class Main {

    public static class Node {

        private char elm;

        private List<Node> children;
        
        Node(char elm) {
            this.elm = elm;
            this.children = new ArrayList<>();
        }

        void addChild(Node child) {
            this.children.add(child);
        }

    }
    
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static int C, D[] = { 2, 3, 0, 1 };

    private static Node root;

    private static String answer;

    private static char[] treeElms;

    private static int index;
    
    private static void parseTree(Node parent, Node curt) {
        if (index == treeElms.length) return;

        int left = 4;
    
        while (left > 0 && index < treeElms.length) {
            Node child = new Node(treeElms[index ++]);
            curt.addChild(child);
            
            if (child.elm == 'x') {
                parseTree(curt, child);
            }
            left --;
        }
    
    }

    private static void dfs(Node curt) {
        if (curt.children.isEmpty()) return;

        for (int d = 0; d < 4; d ++) {
            if (D[d] >= curt.children.size()) continue;
            
            Node child = curt.children.get(D[d]);
            answer += child.elm;

            if (child.elm == 'x') {
                dfs(child);
            }
        }
    }

    public static void main(String[] args) throws Exception {

        C = Integer.parseInt(br.readLine());

        for (int c = 0; c < C; c++) {
            
            treeElms = br.readLine().toCharArray();

            root = new Node(treeElms[0]);

            if (treeElms[0] == 'x') {
                index = 1;
                answer = String.valueOf(treeElms[0]);
            } else {
                index = 0;
                answer = "";
            }

            parseTree(null, root);

            dfs(root);
            bw.write(answer + "\n");
        }

        bw.flush();

    }
}
