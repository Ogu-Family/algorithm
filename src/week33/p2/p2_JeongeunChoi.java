package algorithm.src.week33.p2;

import java.io.*;
import java.util.*;

/**
 * 문제 링크: https://www.acmicpc.net/problem/20366
 * 메모리: 85236 KB
 * 시간: 728 ms
 * 시간 복잡도: O(N^2)
 * 공간 복잡도: O(N^2)
 */

/**
 * 완탐
 * 풀이 참고한 문제
 * 1. 눈사람은 눈덩이 2개로 만들 수 있으므로, 만들 수 있는 눈사람 다 만들기
 * 2. 눈사람 높이 기준 오름차순 정렬
 * 3. 연속된 두 눈사람을 선택하여, 사용한 눈덩이 4개가 중복되지 않는 경우 높이 차이를 구해 최소 값인 경우 업데이트한다.
 *
 * 투포인터도 가능하다는데, 개인적으로 이게 더 이해가 잘돼서 완탐으로 구현해봤습니다. 이분 탐색으로 푸신분 있으면 구경하러 갈게요~~
 */

public class p2_JeongeunChoi {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine()), answer = Integer.MAX_VALUE;
        int[] snowballs = new int[N];
        ArrayList<Snowman> snowmans = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            snowballs[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                snowmans.add(new Snowman(i, j, snowballs[i] + snowballs[j]));
            }
        }

        Collections.sort(snowmans);

        for (int i = 0; i < snowmans.size() - 1; i++) {
            Set<Integer> snowballSet = new HashSet<>();
            snowballSet.add(snowmans.get(i).s1);
            snowballSet.add(snowmans.get(i).s2);
            snowballSet.add(snowmans.get(i + 1).s1);
            snowballSet.add(snowmans.get(i + 1).s2);

            if (snowballSet.size() == 4) {
                int difference = snowmans.get(i + 1).height - snowmans.get(i).height;
                answer = Math.min(answer, difference);
            }

        }
        System.out.println(answer);
    }

    static class Snowman implements Comparable<Snowman> {

        int s1, s2, height;

        Snowman(int s1, int s2, int height) {
            this.s1 = s1;
            this.s2 = s2;
            this.height = height;
        }

        public int compareTo(Snowman other) {
            return this.height - other.height;
        }
    }

}