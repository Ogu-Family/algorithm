package week01.p2;

/**
 * 문제 링크: https://www.acmicpc.net/problem/1764
 * 메모리: 26308 KB
 * 시간: 356 ms
 * 시간 복잡도: O(NlogN)
 * 공간 복잡도: O(N)
 */

/*
1. 듣도 못한 사람의 이름을 입력 받아
2. 보도 못한 사람의 이름과 겹치는 경우를 arrayList에 저장
3. 오름차순으로 정렬 후 결과 출력
 */

import java.io.*;
import java.util.*;

public class p2_letskuku {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        Set<String> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            set.add(br.readLine());
        }

        List<String> list = new ArrayList<>();
        for (int i = 0 ; i < m; i++) {
            String tmp = br.readLine();
            if (set.contains(tmp)) {
                list.add(tmp);
            }
        }

        Collections.sort(list);
        System.out.println(list.size());
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }
}
