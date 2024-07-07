package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * 문제 링크: https://www.acmicpc.net/problem/30892
 * 메모리: 51908 KB
 * 시간: 720 ms
 * 시간 복잡도: O(NlogN)
 * 공간 복잡도: O(N)
 */

/**
 * 시간초과를 해결할 수 없을 것 같아서 힌트를 보니까 스택이라 스택으로 구현해보았습니다..
 *
 * 오름차순 정렬
 * 1. 현재 상어 사이즈보다 작으면 스택에 넣기
 * 2. 작지 않은 상어에 도달했을 때, 스택에 있는 상어 먹기
 * 3. 끝까지 진행 or 먹을 수 있는 수에 도달할 때 까지 진행
 *
 * 로직은 for 문 사용할 때랑 같지만 stack을 쓰냐 안쓰냐로 시간 복잡도 문제를 해결할 수 있는 문제네요..
 */

public class p2_GiHoo {

    static int sharkCount, canEatCount, sharkSize;
    static int[] sharks;

    public static void main(String[] args) throws IOException {
        input();

        System.out.print(solution());
    }

    private static long solution() {
        long nextSharkSize = sharkSize;

        Arrays.sort(sharks);

        Stack<Integer> stack = new Stack<>();
        int eatCount = 0;
        int index = 0;

        while (eatCount < canEatCount) {
            if (index < sharkCount && sharks[index] < nextSharkSize) { // 자기보다 작은 상어들은 스택에 저장
                stack.push(sharks[index]);
                index++;
            } else if (!stack.isEmpty()) { // 자기보다 작은 얘들이 없을 때, 스택에 있는 상어들을 먹기 시작
                nextSharkSize += stack.pop();
                eatCount += 1;
            } else {
                break;
            }
        }

        return nextSharkSize;
    }

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        sharkCount = Integer.parseInt(st.nextToken());
        canEatCount = Integer.parseInt(st.nextToken());
        sharkSize = Integer.parseInt(st.nextToken());
        sharks = new int[sharkCount];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < sharkCount; i++) {
            sharks[i] = Integer.parseInt(st.nextToken());
        }
    }
}
