package algorithm.src.week18;

import java.io.*;
import java.util.*;

/**
 * 문제 링크: https://www.acmicpc.net/problem/1339
 * 메모리: 14336 KB
 * 시간: 124 ms
 * 시간 복잡도: O(N)
 * 공간 복잡도: O(N)
 */

/**
 * 풀이 참고한 문제
 * 1. 각 문자에 대해, 가중치 값을 저장한다.
 * 2. 저장된 가중치 값 배열을 오름차순으로 정렬한다.
 * 3. 가중치가 큰 값 부터 9부터 시작하여 1씩 줄여가며 곱한 값을 더한다.
 */

public class p1_JeongeunChoi {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine()), sum = 0;
        String[] word = new String[N];
        int[] weightForChar = new int[26];

        for (int i = 0; i < N; i++) {
            word[i] = br.readLine();
            for (int j = 0; j < word[i].length(); j++) {
                weightForChar[word[i].charAt(j) - 'A'] += Math.pow(10, word[i].length() - j - 1);
            }
        }
        Arrays.sort(weightForChar);

        int num = 9;
        for (int i = weightForChar.length - 1; i >= 0; i--) {
            if (weightForChar[i] == 0) {
                break;
            }
            sum += weightForChar[i] * num--;
        }

        System.out.println(sum);
    }
}