package src.week34.p1;

/**
 * 문제 링크: https://www.acmicpc.net/problem/2565
 * 메모리: 18264 KB
 * 시간: 212 ms
 * 시간 복잡도: O(NlogN)
 * 공간 복잡도: O(N)
 */

/**
 * LIS
 * 그리디로 푸는데 도저히 답이 없길래 풀이 참조. 생각해내기가 까다로운 듯..
 * 가장 빠른 LIS 구하는 알고리즘으로 작성했습니다.
 * 풀이: https://rebro.kr/33
 */

import java.util.*;
import java.util.stream.*;

public class p1_ZZAMBAs {
    static Scanner sc = new Scanner(System.in);
    static int N;
    static List<Pair> pairs;
    static List<Integer> lis = new ArrayList<>(101);

    public static void main(String[] args) {
        N = sc.nextInt();
        pairs = IntStream.range(0, N).mapToObj(i -> new Pair(sc.nextInt(), sc.nextInt())).toList();
        pairs.stream().sorted(Comparator.comparing(Pair::getX))
            .map(Pair::getY)
            .forEach(p1_ZZAMBAs::calLis);

        System.out.println(N - lis.size());
    }

    static void calLis(int val) {
        int s = 0, e = lis.size() - 1;

        while (s <= e) {
            int mid = (s + e) / 2;

            if (lis.get(mid) >= val)
                e = mid - 1;
            else
                s = mid + 1;
        }

        if (s >= lis.size())
            lis.add(val);
        else
            lis.set(s, val);
    }

    static class Pair {
        int x;
        int y;

        Pair (int x, int y) {
            this.x = x;
            this.y = y;
        }

        int getX() {
            return x;
        }

        int getY() {
            return y;
        }
    }
}
