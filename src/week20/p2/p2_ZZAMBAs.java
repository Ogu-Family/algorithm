package src.week20.p2;

/**
 * 문제 링크: https://school.programmers.co.kr/learn/courses/30/lessons/62048
 * 시간 복잡도: O(gcd(W, H)) - 아마 O(1)만큼 짧을 것
 * 공간 복잡도: O(1)
 */

/**
 * 수학
 *
 * 0. answer = W * H;
 * 1. W, H 중 큰 값을 big, 작은 값을 small로 두고 gcd 값을 계산한다.
 * 2. gcd 값으로 big, small을 나누어 최소 단위로 만든다.
 * 3. big % small 로 small 단위 길이 1 당 big이 길이 1보다 더 이동하는지 체크한다.
 * 3-1. 더 이동한다면 (small - 1) 만큼 옆으로 선이 삐져나가므로, big 값에다가 (small - 1)를 더한 값을 answer에 뺀다.
 * 3-2. 더 이동하지 않는다면 (즉, big % small == 0이면) 삐져나가는 것이 없으므로 big 값만 answer에서 뺀다.
 */

public class p2_ZZAMBAs {
    public long solution(int w, int h) {
        long answer = (long)w * h;

        int big = Math.max(w, h), small = Math.min(w, h);

        int gcdV = gcd(big, small);

        System.out.println("gcd: " + gcdV);

        big /= gcdV; small /= gcdV;

        int r = big % small;
        int filledPerOne = r > 0 ? big + small - 1 : big;

        System.out.println(filledPerOne * gcdV);

        answer -= filledPerOne * gcdV;
        return answer;
    }

    int gcd(int big, int small) {
        if (big % small == 0)
            return small;
        return gcd(small, big % small);
    }
}
