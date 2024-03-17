package src.week15.p1;

/**
 * 문제 링크: https://school.programmers.co.kr/learn/courses/30/lessons/150367
 * 최대 실행 시간: 147.89ms
 * 최대 사용 메모리: 114MB
 * 시간 복잡도: O(logN)
 * 공간 복잡도: O(N)
 */

/**
 * 분할 정복 구현 문제
 * 10^15가 Long 범위 내라서 사실 문자열로 변환 안하고 비트마스킹으로도 풀 수 있어 보인다.
 *
 * 최소 완전 트리 노드 수를 구한다. 그 수부터 0으로 앞을 패딩하여 채운 뒤, 루트부터 시작해서 이것이 제대로 된 이진트리인지 확인한다.
 * 분할 정복은 다음 과정을 거친다. 일단, 분할 정복 전에 64bit 변수 하나를 선언한다(아래 코드에서는 flags)
 * 1. 문자열 내 시작 인덱스와 끝 인덱스를 받는다.
 * 2-1. (시작 인덱스 + 끝 인덱스) / 2 = mid 는 루트 노드이다. 이 루트 노드가 0일 경우엔 더 이상 진행하지 않는다.
 * 2-2. 1이면 flags에서 해당 인덱스에 1을 넣고 다시 그 루트 노드 앞 뒤로 분할 정복을 돌린다. 즉, 분할정복(시작인덱스, mid - 1), 분할정복(mid + 1, 끝 인덱스)를 한다.
 * 3. 시작 인덱스보다 끝 인덱스가 더 앞에 있다면 모순이므로 종료한다.
 * 위 과정을 거치면 flags는 정상적인 이진 트리 값이 되어 있다. 이 flags 값과 패딩한 값을 비교해서 동일하면 포화 이진트리로 만들 수 있다는 의미.
 *
 * 트리에 분할 정복이 섞여 나오는 게 굉장히 신선했던 문제. 다른 사람들 풀이도 비슷한 것으로 보인다.
 */

import java.util.*;

class p1_ZZAMBAs {
    public int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];
        int idx = 0;

        for(long num : numbers) {
            String binary = convertToBinary(num);
            answer[idx++] = check(binary) ? 1 : 0;
        }

        return answer;
    }

    public String convertToBinary(long num) {
        Stack<String> stk = new Stack<>();
        StringBuilder sb = new StringBuilder();

        while(num > 0) {
            stk.push(num % 2 == 1 ? "1" : "0");
            num /= 2;
        }

        while(!stk.isEmpty()) {
            sb.append(stk.pop());
        }

        //System.out.println(sb.toString());

        return sb.toString();
    }

    public boolean check(String binary) {
        //System.out.println("binary = " + binary);

        int strSize = binary.length();
        int treeCnt = 2;
        for(int i = 1; i < 6; i++) {
            if (treeCnt - 1 <= strSize && strSize <= 2 * treeCnt - 1) {
                treeCnt = treeCnt - 1;
                break;
            }
            treeCnt *= 2;
        }

        // System.out.println("treeCnt = " + treeCnt);

        while(treeCnt < 64) {
            int padding = treeCnt - strSize;
            boolean[] flags = new boolean[treeCnt];

            StringBuilder sb = new StringBuilder();
            for(int j = 0; j < padding; j++)
                sb.append("0");
            sb.append(binary);

            //System.out.println("paddingStr = " + sb.toString());

            divideAndConquer(sb.toString(), 0, treeCnt - 1, flags);

            StringBuilder dacResBuilder = new StringBuilder();
            for(boolean flag : flags) {
                if(flag)
                    dacResBuilder.append("1");
                else
                    dacResBuilder.append("0");
            }

            String dacRes = dacResBuilder.toString();
            //System.out.println("dacRes = " + dacRes);
            if (sb.toString().equals(dacRes))
                return true;

            treeCnt = 2 * (treeCnt + 1) - 1;
        }
        return false;
    }

    public void divideAndConquer(String str, int startIdx, int endIdx, boolean[] flags) {
        if(startIdx > endIdx)
            return;

        int rootIdx = (startIdx + endIdx) / 2;

        if(str.charAt(rootIdx) == '1') {
            flags[rootIdx] = true;
            divideAndConquer(str, startIdx, rootIdx - 1, flags);
            divideAndConquer(str, rootIdx + 1, endIdx, flags);
        }
    }
}
