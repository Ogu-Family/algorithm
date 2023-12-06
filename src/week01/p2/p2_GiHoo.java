package src.week01.p2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class p2_GiHoo {
    static int N;
    static int M;
    static Map<String, Integer> map = new HashMap<>();
    static List<String> result = new ArrayList<>();

    private static String printAnswer() {
        StringBuilder sb = new StringBuilder();

        sb.append(result.size()).append(System.lineSeparator());
        for (String name : result) {
            sb.append(name).append(System.lineSeparator());
        }

        return sb.toString();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int count = 0;
        for (int i = 0; i < N + M; i++) {
            String input = br.readLine();
            if (count <= N - 1) {
                map.put(input, map.getOrDefault(input, 0) + 1);
            } else {
                if(map.containsKey(input)) result.add(input);
            }
            count++;
        }

        Collections.sort(result); // O(NlogN)

        System.out.println(printAnswer());
    }
}

/**
 * 문제 링크: https://www.acmicpc.net/problem/1764
 * 메모리: 26240 KB
 * 시간: 312 ms
 * 시간 복잡도: O(NlogN) -> Collections.sort() 연산
 * 공간 복잡도: O(N) -> Map과 List 초기화
 *
 * 풀이
 * 듣도 못한 N 명과 보도 못한 M 명
 * 0 ~ N-1에는 듣도 못한 사람이 입력되고
 * N-1 ~ N+M-1에는 보도 못한 사람이 입력된다.
 * N-1까지 구분을 하여 Map에 저장하고
 * 이후 입력에서 Map에 입력값이 존재한다면 듣도 보도 못한 사람으로 생각하여 List에 저장
 * 사전순으로 출력하기 위해 Collections.sort() 수행 후 출력
 */

