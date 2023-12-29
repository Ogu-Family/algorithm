package src.week04.p2;

/**
 * 문제 링크: https://www.acmicpc.net/problem/20115
 * 메모리: 111160 KB
 * 시간: 964 ms
 * 시간 복잡도: O(N)
 * 공간 복잡도: O(1) 이라 생각했으나 메모리 크기 엄청 먹는게 이상하네요. 혹시 이유 아시는 분은 첨삭 바랍니다.
 */

/**
 * 그리디 문제. 결국 모든 에너지 드링크를 하나로 합해야 하고, 합할 때의 흘리는 음료 양이 최소여야 한다.
 * 합하는 횟수는 반드시 N - 1 번이며 그 때동안 최소로 흘리려면 합할 때마다 최소 값을 포함하여 합해야 한다.
 * 그런데 합하면 합해진 에너지 드링크 양은 반드시 증가하므로, 이 증가한 에너지 드링크를 다른 에너지 드링크와 합한다면 흘리는 양은 늘어날 것이다.
 * 그래서 이 합하는 연산에서 피연산자 하나를 최대 양의 에너지 드링크로 고정하고, 나머지 피연산자는 그 외 나머지로 하면 흘리는 양이 최소가 된다.
 *
 * 아래 풀이는 두 번째 풀이다. 첫 번째 풀이 때는 정렬을 해서 O(NlogN)이 소요되었다. 그런데 사실 정렬할 필요가 없었다.
 *
 * 문제에서 중요한 부분
 * - a의 양을 x_a + (x_b / 2)로 만들고, b를 버리기 또는 b의 양을 x_b + (x_a / 2)로 만들고, a를 버리기
 * - N <= 10^5, x_i <= 10^9 (오버플로우 주의)
 * - 절대 / 상대 오차는 10-5까지 허용한다.
 */

import java.util.Scanner;

public class p2_ZZAMBAs {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        long maxV = 0, sum = 0;

        for(int i = 0; i < N; i++) {
            int val = sc.nextInt();

            sum += val;
            maxV = Math.max(maxV, val);
        }

        System.out.println(maxV + (sum - maxV) / 2.0);
    }
}

/* 첫번째 풀이 O(NlogN)
import java.util.Scanner;
import java.util.ArrayList;

public class p2_ZZAMBAs{
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		ArrayList<Integer> arr = new ArrayList<>();

		for(int i = 0; i < N; i++) {
		    arr.add(sc.nextInt());
		}

		arr.sort( (i1, i2) -> i1 - i2 );

		long q = arr.get(N - 1), r = 0; // 몫, 나머지

		for(int i = 0; i < N - 1; i++) {
		    int curDrink = arr.get(i);
		    if (curDrink % 2 == 1)
		        r++;
		    q += curDrink / 2 + r / 2;
		    r = r % 2;
		}

		double res = (double)r / 2 + q;

		System.out.println(res);
	}
}
 */
