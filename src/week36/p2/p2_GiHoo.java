package src.week36.p2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * 문제 링크: https://www.acmicpc.net/problem/1759
 * 메모리: 18548 KB
 * 시간: 128 ms
 * 시간 복잡도: O(2^C * L)
 * 공간 복잡도: O(C + L)
 */

/**
 * 백트래킹 같은 완전탐색 문제, 중간에 조건을 주어 돌아가지 않고 일단 끝까지 탐색을 진행
 *
 * 모음을 구별하기 위에 map에 모음을 저장
 * 문제 조건을 맞추기 위해 정렬한 후 depth 0부터 dfs 진행
 * depth가 C가 되었을 때, 모음 1개 자음 2개를 체크하고 길이를 확인한 후 조건에 맞는다면 저장 후 출력
 */

public class p2_GiHoo {

    static int L, C;
    static String[] words;
    static Map<Character, Integer> map = new HashMap<>();
    static StringBuilder sb = new StringBuilder();


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map.put('a', 1);
        map.put('e', 1);
        map.put('i', 1);
        map.put('o', 1);
        map.put('u', 1);

        words = new String[C];
        words = br.readLine().split(" ");

        Arrays.sort(words);

        DFS(0, "");

        System.out.println(sb);
    }

    private static void DFS(int depth, String password) {
        if (depth == C) {
            if (password.length() == L && validatePassword(password)) {
                sb.append(password).append("\n");
            }
            return;
        }

        if (password.length() < L) {
            DFS(depth + 1, password + words[depth]);
        }

        DFS(depth + 1, password);
    }

    private static boolean validatePassword(String password) {
        int cnt = 0;

        for (int i = 0; i < password.length(); i++) {
            if (map.containsKey(password.charAt(i))) {
                cnt++;
            }
        }

        return (cnt >= 1) && ((password.length() - cnt) >= 2);
    }
}


