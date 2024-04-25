package algorithm.src.week20.p2;

/**
 * 문제 링크: https://school.programmers.co.kr/learn/courses/30/lessons/62048
 * 시간 복잡도: O(W)
 * 공간 복잡도: O(1)
 */

/**
 * 가로, 세로를 x축 y축으로 생각하고
 * 기울기와 y절편에 따라 y = (-h/w)x + h
 * x 값에 따른 y 값을 구하여 그래프 밑으로 만들 수 있는 사각형을 갯수를 구하고 *2 한다.
 */

public class p2_JeongeunChoi {

    private double getY(double w, double h, int x) {
        return (-h / w) * x + h;
    }

    public long solution(int w, int h) {
        long answer = 0;

        for (int i = 1; i <= w - 1; i++) {
            answer += Math.floor(getY(w, h, i));
        }
        answer *= 2;

        return answer;
    }
}
