package src.week02.p1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class p1_GiHoo {
    static final char PEOPLE = 'P';
    static final char HAMBURGER = 'H';

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        String input = br.readLine();
        char[] hamburgersAndPeople = input.toCharArray();
        boolean[] hamburger = new boolean[N]; // 선점된 햄버거를 구분하기 위한

        int answer = 0;

        for (int i = 0; i < N; i++) {
            if (hamburgersAndPeople[i] == PEOPLE) {
                int left = i - K; // 왼쪽으로 먹을 수 있는 범위
                int right = i + K; // 오른쪽으로 먹을 수 있는 범위
                for (int j = Integer.max(0, left); j <= Integer.min(right, N - 1); j++) {
                    if (hamburgersAndPeople[j] == HAMBURGER && !hamburger[j]) {
                        hamburger[j] = true; // 주인 있는 햄버거로 처리
                        answer++;
                        break;
                    }
                }
            }
        }

        System.out.println(answer);

    }
}


/**
 * 문제 링크: https://www.acmicpc.net/problem/19941
 * 메모리: 14384 KB
 * 시간: 140 ms
 * 시간 복잡도: O(N * K)
 * 공간 복잡도: O(N)
 *
 * 풀이
 * 햄버거와 사람을 배열에 집어넣고
 * 배열을 순차적으로 루프한다.
 * 만약 배열의 인덱스가 사람이라면
 *  K(범위)를 기준으로 왼쪽과 오른쪽을 구분한다.
 *  인덱스 - K ~ 인덱스 + K를 순회하는데, 이때 좌우가 배열의 처음과 마지막이 되지 않도록 주의한다. (0, length - 1)
 */
