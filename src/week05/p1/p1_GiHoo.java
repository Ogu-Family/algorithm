package src.week05.p1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class p1_GiHoo {

    static int alreadyWire;
    static int requiredWire;
    static int[] wires;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        alreadyWire = Integer.parseInt(st.nextToken());
        requiredWire = Integer.parseInt(st.nextToken());

        wires = new int[alreadyWire];
        for (int i = 0; i < wires.length; i++) {
            wires[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(wires);

        System.out.print(solution());
    }

    private static long solution() {
        long min = 1;
        long max = wires[wires.length - 1];
        long mid;

        while (min <= max) {
            mid = (min + max) / 2;

            long count = 0;
            for (int wire : wires) {
                count += wire / mid;
            }

            if (count < requiredWire) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        return max;
    }

}

/**
 * 문제 링크: https://www.acmicpc.net/problem/1654
 * 메모리: 17456 KB
 * 시간: 192 ms
 * 시간 복잡도: O(NlogN)
 * 공간 복잡도: O(N)
 *
 * 풀이
 * 현재 가지고 있는 랜선, 필요한 랜선 입력 받기 -> already, required
 * 현재 가지고 있는 랜선 중 가장 긴 길이의 랜선을 기준으로 자르기를 수행
 * 랜선의 최소 길이는 1, 최대 길이는 wires[length-1] -> 이분 탐색 수행
 * 문제에서는 가질 수 있는 최대의 랜선 길이를 반환 -> max 값 반환
 *
 * 실수.
 * 관성적으로 이분 탐색 문제라
 * mid 값을 반환하는 실수를 해서 오답이 많이 생김..
 * 문제를 잘 읽습니다.
 */
