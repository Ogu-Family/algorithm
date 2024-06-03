package algorithm.src.week25.p1;

import java.io.*;
import java.util.*;

/**
 * 문제 링크: https://www.acmicpc.net/problem/20529
 * 메모리: 26628 KB
 * 시간: 348 ms
 * 시간 복잡도: O(N*16C3)
 * 공간 복잡도: O(1)
 */

/**
 * 구현
 * 1. mbti는 총 16가지이므로 mbti별로 사람 수를 저장한다.
 * 2. 16개의 mbti에 대해 총 3명을 뽑는다.
 * 3. 3개의 mbti의 거리를 구하고 가장 작은 값을 찾는다.
 */

public class p1_JeongeunChoi {

    private static int[] mbtiCnt;
    private static int minDistanceSum;
    private static String[] mbti = {
            "ISTJ", "ISFJ", "INFJ", "INTJ",
            "ISTP", "ISFP", "INFP", "INTP",
            "ESTP", "ESFP", "ENFP", "ENTP",
            "ESTJ", "ESFJ", "ENFJ", "ENTJ"
    };
    private static Map<String, Integer> mbtiMap = new HashMap<>() {{
        put("ISTJ", 0);
        put("ISFJ", 1);
        put("INFJ", 2);
        put("INTJ", 3);
        put("ISTP", 4);
        put("ISFP", 5);
        put("INFP", 6);
        put("INTP", 7);
        put("ESTP", 8);
        put("ESFP", 9);
        put("ENFP", 10);
        put("ENTP", 11);
        put("ESTJ", 12);
        put("ESFJ", 13);
        put("ENFJ", 14);
        put("ENTJ", 15);
    }};

    private static void select3NearestPerson(int cnt, List<String> selectedMbti) {
        if (cnt == 3) {
            minDistanceSum = Math.min(minDistanceSum, sumDistance(selectedMbti));
        } else {
            for (int i = 0; i < 16; i++) {
                if (mbtiCnt[i] > 0) {
                    mbtiCnt[i]--;
                    selectedMbti.add(mbti[i]);
                    select3NearestPerson(cnt + 1, selectedMbti);
                    selectedMbti.remove(selectedMbti.size() - 1);
                    mbtiCnt[i]++;
                }
            }
        }
    }

    private static int sumDistance(List<String> selectedMbti) {
        return calculateDistance(selectedMbti.get(0), selectedMbti.get(1))
                + calculateDistance(selectedMbti.get(0), selectedMbti.get(2))
                + calculateDistance(selectedMbti.get(1), selectedMbti.get(2));
    }

    private static int calculateDistance(String mbti1, String mbti2) {
        int distance = 0;
        for (int i = 0; i < 4; i++) {
            if (mbti1.charAt(i) != mbti2.charAt(i)) {
                distance++;
            }
        }

        return distance;
    }


    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            mbtiCnt = new int[16];
            minDistanceSum = Integer.MAX_VALUE;
            int N = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                Integer mbtiNum = mbtiMap.get(st.nextToken());
                mbtiCnt[mbtiNum]++;
            }
            select3NearestPerson(0, new ArrayList<>());
            System.out.println(minDistanceSum);
        }
    }
}
