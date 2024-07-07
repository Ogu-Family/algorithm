package src.week31.p3;

/**
 * 문제 링크: https://www.acmicpc.net/problem/26216
 * 메모리: 318524 KB
 * 시간: 2152 ms
 * 시간 복잡도: O(Q * (H + log_{K + 1}A) )
 * 공간 복잡도: O(log_{K + 1}A)
 */

/**
 * 수학, LCA
 * 일단 기업용 코테에서는 못본 것 같은 유형입니다... 단순 트리 문제인 줄 알고 출제했는데 실수했네요. 풀이 참조했습니다.
 *
 * 1. (K + 1)진법으로 A와 B를 표기한다. 표기할 때, H만큼 자릿 수가 있어야 한다. 이후, 각 자릿 수를 List에 담는다.
 * 2. 뒷 자리부터 보면서 맨 처음 0이 아닌 자리 값을 음수화한다. (예: 0, 1, 1, 0 -> 0, 1, -1, 0)
 * 3. 음이 아닌 정수는 흰색 노드를 의미하고, 음수는 파란색 노드(자기 자신)를 의미한다.
 * 4. 앞자리부터 가장 가까운 공통 흰색 노드 자릿 수를 찾는다.
 * 5. 그 공통 흰색 노드에서 A와 B 사이 깊이 값을 각각 구한 뒤 서로 더한다.
 *
 * 예제 그림의 K = 2, H = 3인 경우, 19와 24는 각각 {2, 0, -1}, {2, -2, 0}로 표현된다.
 * - 즉, 19는 루트 노드에서 2번째 흰색 노드, 0번째 흰색노드, 첫 번째 파란색 노드(0을 쓸 수 없어서 인덱스가 1부터 시작) 위치에 있다.
 * 맨 앞 자리부터 공통 흰색 노드를 찾아간다. 첫 자리가 2로 같다. 따라서 다음 자릿 수를 본다.
 * 다음 자릿 수는 0과 -2로, 서로 다르다. 즉, 가장 가까운 공통 조상 흰색 노드는 첫번째 노드다. 깊이 차이는 각각 2, 1이다.
 * 따라서 19와 24는 3의 거리만큼 떨어져 있다.
 */

import java.util.*;
import java.util.stream.*;

public class p3_ZZAMBAs {
    static Scanner sc = new Scanner(System.in);
    static int K, H, Q;
    static long key = 1;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        K = sc.nextInt(); H = sc.nextInt(); Q = sc.nextInt();
        for (int i = 0; i < H; i++)
            key *= (K + 1);
        key -= 1;
        IntStream.range(0, Q).forEach(i -> {
            long A = sc.nextLong(), B = sc.nextLong();

            sb.append(calculate(A, B)).append('\n');
        });

        System.out.print(sb);
    }

    static int calculate(long A, long B) {
        if (A <= 0 || B <= 0 || A > key || B > key)
            return -1;

        if (A == B)
            return 0;

        List<Integer> aPar = divide(A), bPar = divide(B);

        int aDepth = depth(aPar), bDepth = depth(bPar);
        int lcaDepth = findLcaDepth(aPar, bPar);

        return aDepth - lcaDepth + bDepth - lcaDepth;
    }

    static List<Integer> divide(long val) {
        boolean sw = true;
        List<Integer> ret = new ArrayList<>();
        for (int i = 0; i < H; i++) {
            int rest = (int) (val % (K + 1));

            if (rest != 0 && sw) {
                rest *= -1;
                sw = false;
            }

            ret.add(rest);

            val /= (K + 1);
        }

        return ret;
    }

    static int depth(List<Integer> num) {
        for (int i = 0; i < H; i++)
            if (num.get(i) != 0)
                return H - i;
        return 0;
    }

    static int findLcaDepth(List<Integer> par1, List<Integer> par2) {
        for (int i = H - 1; i >= 0; i--)
            if (!par1.get(i).equals(par2.get(i)))
                return H - 1 - i;

        return 0;
    }
}
