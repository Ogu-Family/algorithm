package algorithm.src.week21.p1;

import java.util.*;

/**
 * 문제 링크: https://school.programmers.co.kr/learn/courses/30/lessons/92342
 * 시간 복잡도: O(n*2^11)
 * 공간 복잡도: O(1)
 */

/**
 * dfs
 * 1. 라이언이 화살을 쏘는 경우의 수를 구한다.
 * 2. 어피치의 화살 점수와 비교하여 최종 점수를 구한다.
 * 3. 최종 점수에 대해 라이언이 가장 크게 이기는 경우를 찾는다. 이 때, 점수 차가 같은 경우, 낮은 점수를 더 많이 맞힌 경우를 정답으로 한다.
 */

public class p1_JeongeunChoi {

    private int[] lionArrows = new int[11], answer = new int[11], appeachArrows;
    private int appeachScore = 0, lionScore = 0, maxDifference = 0;

    private void divideArrow(int arrowIdx, int remainArrow) {
        if (remainArrow == 0) {
            calculateScore();
            if (appeachScore < lionScore) {
                int difference = lionScore - appeachScore;
                if (maxDifference < difference) {
                    maxDifference = difference;
                    answer = Arrays.copyOf(lionArrows, lionArrows.length);
                } else if (maxDifference == difference) {
                    for (int i = 10; i >= 0; i--) {
                        if (lionArrows[i] > answer[i]) {
                            answer = Arrays.copyOf(lionArrows, lionArrows.length);
                            break;
                        }
                    }
                }
            }
        } else if (arrowIdx >= 0) {
            for (int i = 0; i <= remainArrow; i++) {
                lionArrows[arrowIdx] = i;
                divideArrow(arrowIdx - 1, remainArrow - i);
                lionArrows[arrowIdx] = 0;
            }
        }
    }

    private void calculateScore() {
        appeachScore = 0;
        lionScore = 0;
        for (int i = 0; i <= 10; i++) {
            if (appeachArrows[i] == 0 && lionArrows[i] == 0) {
                continue;
            }
            if (appeachArrows[i] >= lionArrows[i]) {
                appeachScore += 10 - i;
            } else {
                lionScore += 10 - i;
            }
        }
    }


    public int[] solution(int n, int[] info) {
        appeachArrows = info;

        divideArrow(10, n);
        if (maxDifference == 0) {
            answer = new int[1];
            answer[0] = -1;
        }

        return answer;
    }

}
