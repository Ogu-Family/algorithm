package src.week37.p2;

/**
 * 문제 링크: https://www.acmicpc.net/problem/28270
 * 메모리: 122704 KB
 * 시간: 640 ms
 * 시간 복잡도: O(N)
 * 공간 복잡도: O(N)
 */

/**
 * 구현
 *
 * 트리 특성 이용하는 줄 알았는데 그냥 for 문 돌리는 문제.
 * 일단 입력을 돌면서, 전 depth + 1보다 더 큰 수가 나오면 매핑을 할 수 없으므로, 잘못된 입력으로 처리.
 * 정상적 입력이면 count 배열로 현재 depth의 번호를 저장. 예를 들어 입력이 1 2 2 2 이면, 입력을 순서대로 돌면서
 * 최초 count[1] = 1, count[2]는 1, 2, 3으로 순서대로 변화하고 변화할 때마다 StringBuilder로 저장.
 * depth가 작아지면 해당 count[depth]를 1로 초기화.
 * 반복.
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class p2_ZZAMBAs {
    static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static final StringBuilder sb = new StringBuilder();

    static int C;
    static int[] inputs;
    static boolean sw = true;

    public static void main(String[] args) throws Exception{
        C = Integer.parseInt(br.readLine());

        int nowDepth = 0;
        int preDepth = 0;

        inputs = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        for (int i : inputs) {
            nowDepth = i;

            if (nowDepth - 1 > preDepth) {
                sw = false;
                break;
            }

            preDepth = nowDepth;
        }

        if (sw)
            preorder();

        System.out.print(sw ? sb : "-1");
    }

    static void preorder() {
        int preDepth = 0;
        int[] count = new int[C + 1];

        for (int i : inputs) {
            if (i > preDepth) {
                count[i] = 1;
            } else {
                count[i]++;
            }

            sb.append(count[i]).append(" ");

            preDepth = i;
        }
    }
}
