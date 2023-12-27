package src.week04.p2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class p2_GiHoo {

    static int drinkCount;
    static double[] drinks;
    static double answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        drinkCount = Integer.parseInt(br.readLine());

        drinks = new double[drinkCount];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < drinkCount; i++) {
            drinks[i] = Double.parseDouble(st.nextToken());
        }

        Arrays.sort(drinks);

        for (int i = 0; i < drinkCount; i++) {
            if (i != drinkCount - 1) {
                drinks[i] /= 2;
            }
            answer += drinks[i];

        }
        System.out.println(answer);
    }
}

/**
 * 문제 링크: https://www.acmicpc.net/problem/20115
 * 메모리: 40488 KB
 * 시간: 520 ms
 * 시간 복잡도: O(NlogN) - Arrays.sort
 * 공간 복잡도: O(N)
 */

/**
 * 처음에는 pq 사용해서 풀어야 한다고 생각했는데 잘못된 생각이었음..
 * 임의의 서로 다른 드링크를 고른다고 했을 때
 * 남는 드링크의 양이 최대가 되려고 한다면
 * 절반을 흘리는 드링크는 상대적으로 적은 값으로 선택되어야 한다.
 *
 * 그렇기에 입력으로 받아온 값 중 가장 큰 값을 제외한 나머지 값을 /2 한 후에 더해주면 된다.
 */
