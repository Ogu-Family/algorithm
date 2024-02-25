package algorithm.src.week12.p3;

import java.io.*;
import java.util.*;

/**
 * 문제 링크: https://www.acmicpc.net/problem/1058
 * 메모리: 15312 KB
 * 시간: 148 ms
 * 시간 복잡도: O(N^3)
 * 공간 복잡도: O(N^2)
 */

/**
 * 2-친구를 구하기 위해 집합을 활용
 * 본인과 바로 친구인 친구A를 넣고
 * 친구A와 친구인 친구들을 넣는다.
 * 그리고 본인은 집합에서 제거한다.
 */

public class p3_JeongeunChoi {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine()), answer = 0;
        int[][] friendRelation = new int[50][50];
        for (int i = 0; i < N; i++) {
            String friends = br.readLine();
            for (int j = 0; j < friends.length(); j++) {
                if (friends.charAt(j) == 'Y') {
                    friendRelation[i][j] = 1;
                }
            }
        }
        for (int i = 0; i < N; i++) {
            Set<Integer> set = new HashSet<>();
            for (int j = 0; j < N; j++) {
                if (friendRelation[i][j] == 1) {
                    set.add(j);
                    for (int k = 0; k < N; k++) {
                        if (friendRelation[j][k] == 1) {
                            set.add(k);
                        }
                    }
                }
            }
            set.remove(i);
            answer = Math.max(answer, set.size());
        }

        System.out.println(answer);
    }
}