package src.week01.p3;

/**
 * 문제 링크: https://www.acmicpc.net/problem/27375
 * 메모리: 18672 KB
 * 시간: 300 ms
 * 시간 복잡도: O(2^n)으로 추정
 * 공간 복잡도: O(n)
 */

/**
 * 백트래킹으로 모든 경우의 수를 찾는 문제.
 * 중요한 부분
 * - 같은 요일, 같은 교시에 열리는 두 수업은 동시에 수강할 수 없다.
 * - 정확히 k 학점을 들으면서 금요일에 수업이 하나도 없는 시간표의 가짓수
 */

import java.util.Scanner;
import java.util.ArrayList;

public class p3_ZZAMBAs {
    public static int n, k, res;
    public static boolean[] visited = new boolean[20]; // dfs시 Lecture 방문 여부
    public static ArrayList<Lecture> lectures = new ArrayList<>(); // 강의들 저장

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        k = sc.nextInt();
        res = 0;

        for(int i = 0; i < n; i++) {
            int w = sc.nextInt(), s = sc.nextInt(), e = sc.nextInt();
            if (w == 5) // 금요일 것은 고려조차 하지 않는다.
                continue;

            lectures.add(new Lecture(w, s, e));
        }

        n = lectures.size();

        for(int i = 0; i < n; i++) {
            visited[i] = true;

            dfs(i, lectures.get(i).calTime());

            visited[i] = false;
        }

        System.out.println(res);
    }

    public static class Lecture {
        int day;
        int startTime;
        int endTime;

        public Lecture(int day, int startTime, int endTime) {
            this.day = day;
            this.startTime = startTime;
            this.endTime = endTime;
        }

        public int calTime() { // 학점 계산
            return endTime - startTime + 1;
        }

        public boolean canEnroll(Lecture another) { // 두 Lecture를 동시 수강 가능한지 체크.
            Lecture large = this, small = another; // large는 강의 시간이 더 많은 강의를 말한다.
            if (this.calTime() < another.calTime()) {
                large = another;
                small = this;
            }

            if (large.startTime <= small.startTime && large.endTime >= small.startTime)
                return false;
            if (large.startTime <= small.endTime && large.endTime >= small.endTime)
                return false;

            return true;
        }
    }

    public static void dfs(int idx, int curK) {
        if (curK >= k) {
            if (curK == k) { // k가 되면 바로 결과로 추가한다.
                res++;
            }
            return;
        }

        for(int i = idx + 1; i < n; i++) {
            if (!visited[i] && canEnroll(i)) {
                visited[i] = true;

                dfs(i, curK + lectures.get(i).calTime());

                visited[i] = false;
            }
        }
    }

    public static boolean canEnroll(int idx) {
        for(int i = 0; i < n; i++) { // O(n)
            if (!visited[i])
                continue;

            Lecture cmpLecture = lectures.get(i);
            Lecture curLecture = lectures.get(idx);

            if (curLecture.day == cmpLecture.day && !cmpLecture.canEnroll(curLecture))
                return false;
        }
        return true;
    }
}
