package baekjoon;

import java.util.*;

public class p2 {
    public static String solution(String[] preferences) {
        StringBuilder answer = new StringBuilder();

        Set<String> flavorSet = new HashSet<>();

        for (String preference : preferences) {
            String[] flavors = preference.split("");
            flavorSet.addAll(Arrays.asList(flavors));
        }

        ArrayList<String> flavorArr = new ArrayList<>(flavorSet);
        Collections.sort(flavorArr);

        for (String flavor : flavorArr) {
            answer.append(flavor).append(flavor);
        }

        System.out.println(answer.toString());

        return answer.toString();
    }

    public static void main(String[] args) {

        String[][] preferences = { {"CS", "SV"}, {"SV", "VS", "SV", "VS"}, {"CM", "MS", "SC"} };

        solution(preferences[0]);
        solution(preferences[1]);
        solution(preferences[2]);
    }
}
