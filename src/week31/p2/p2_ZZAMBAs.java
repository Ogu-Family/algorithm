package src.week31.p2;

import java.util.*;
import java.util.stream.*;

/**
 * 문제 링크: https://www.acmicpc.net/problem/30892
 * 메모리: 194728 KB
 * 시간: 1280 ms
 * 시간 복잡도: O(NlogN) (정렬)
 * 공간 복잡도: O(N)
 */

/**
 * 스택, 그리디
 *
 * 1. 먹이 오름차순 정렬
 * 2. 먹이를 스택에 넣음에 있어서 현재 몸집보다 작으면 넣고, 같거나 크면 스택에 들어있는 가장 큰 먹이를 먹는다. 먹을 때 K 값이 감소한다.
 * 2-1. 먹이를 먹을 때, 스택이 비어있거나, K가 0이면 더 먹을 수 없으니 종료한다.
 * 3. 현재 몸집을 출력
 */

public class p2_ZZAMBAs{
    static Scanner sc = new Scanner(System.in);
    static long[] sharks;
    static Stack<Long> stk = new Stack<>(); // 비추천 문법으로, Deque를 추천한다고 하네요. https://kdhyo98.tistory.com/62
    static int N, K;
    static long T;

    public static void main(String[] args) {
        N = sc.nextInt(); K = sc.nextInt(); T = sc.nextLong();
        sharks = new long[N];
        IntStream.range(0, N).forEach(i -> sharks[i] = sc.nextLong());
        Arrays.sort(sharks);

        eat();

        System.out.print(T);
    }

    static void eat() {
        for (int i = 0; i < N; i++) {
            if (sharks[i] < T)
                stk.push(sharks[i]);
            else {
                if (K == 0 || stk.isEmpty())
                    return;

                K--;
                T += stk.pop();
                i--;
            }
        }

        while (K-- > 0)
            T += stk.pop();

    }
}
