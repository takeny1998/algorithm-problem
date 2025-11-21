package baekjoon;

import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

public class p9202 {
    private static class Node {
        Map<Character, Node> childNode = new HashMap<>();
        boolean isLeaf;
    }

    private static class Trie {
        Node rootNode = new Node();

        void insert(String str) {
            Node node = this.rootNode;

            for (int i = 0; i < str.length(); i ++) {
                node = node.childNode.computeIfAbsent(str.charAt(i), key -> new Node());
            }

            node.isLeaf = true;
        }

        void print(Node node) {
            for (char c : node.childNode.keySet()) {
                System.out.print(c + " ");
                print(node.childNode.get(c));
            }
            System.out.println();
        }

        int search(String str) {
            Node node = this.rootNode;

            for (int i = 0; i < str.length(); i ++) {
                node = node.childNode.getOrDefault(str.charAt(i), null);

                if (node == null) return -1;
            }

            return (node.isLeaf) ? 1 : 0;
        }
    }

    static HashSet<String> findWord;
    static Trie trie;

    static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dx = {0, 1, 1, 1, 0, -1, -1, -1};
    static int W, B, score, findWordCount;
    static String longestWord;

    static char[][] board;
    static boolean[][] visited;

    static int getScore(int length) {
        switch (length) {
            case 3: return 1;
            case 4: return 1;
            case 5: return 2;
            case 6: return 3;
            case 7: return 5;
            case 8: return 11;
            default: return 0;
        }
    }

    static void doDfs(int y, int x, String word) {
        word += board[y][x];
        int status = trie.search(word);

        if (status == -1) return;

        if (status == 1) {
            findWord.add(word);
        }

        for (int d = 0; d < 8; d ++) {
            int ny = y + dy[d];
            int nx = x + dx[d];

            if ((0 <= ny && ny < 4) && (0 <= nx && nx < 4)) {
                if (!visited[ny][nx]) {
                    visited[ny][nx] = true;
                    doDfs(ny, nx, word);
                    visited[ny][nx] = false;
                }
            }
        }
    }

    static void getResult() {
        Iterator iter = findWord.iterator();

        while (iter.hasNext()) {
            String word = String.valueOf(iter.next());
            findWordCount ++;
            score += getScore(word.length());


            if (word.length() > longestWord.length()) {
                longestWord = word;
            } else if (word.length() == longestWord.length()) {
                if (word.compareTo(longestWord) < 0) longestWord = word;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        trie = new Trie();
        W = Integer.parseInt(br.readLine());

        for (int i = 0; i < W; i ++) {
            trie.insert(br.readLine());
        }
        br.readLine();

        B = Integer.parseInt(br.readLine());

        for (int i = 0; i < B; i ++) {
            score = 0;
            longestWord = "";
            findWordCount = 0;
            findWord = new HashSet<>();
            board = new char[4][4];

            for (int y = 0; y < 4; y ++) {
                char[] line = br.readLine().toCharArray();
                for (int x = 0; x < 4; x ++) {
                    board[y][x] = line[x];
                }
            }


            for (int y = 0; y < 4; y ++) {
                for (int x = 0; x < 4; x ++) {
                    visited = new boolean[4][4];
                    visited[y][x] = true;
                    doDfs(y, x, "");
                }
            }

            getResult();
            bw.write(score + " " + longestWord + " " + findWordCount + "\n");

            if (i < (B - 1)) br.readLine();
        }

        bw.flush();
        bw.close();
    }
}
