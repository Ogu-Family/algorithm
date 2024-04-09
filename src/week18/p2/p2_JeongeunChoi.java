package algorithm.src.week18.p2;

import java.util.*;

/**
 * 문제 링크: https://school.programmers.co.kr/learn/courses/30/lessons/42586
 * 시간 복잡도: O(N^2)
 * 공간 복잡도: O(N)
 */

/**
 * 모든 기능이 배포되기 전까지, 하루 씩 늘려가며 개발을 진행한다.
 * 개발 진행 후 앞의 기능들이 모두 배포되어 배포가 가능한 기능들을 배포한다.
 * 배포된 기능이 있다면 갯수를 저장한다.
 */

public class p2_JeongeunChoi {

    public int[] solution(int[] progresses, int[] speeds) {
        int[] answer = {};
        int deployCnt = 0;
        boolean[] isDeployed = new boolean[progresses.length];
        ArrayList<Integer> deployDays = new ArrayList<>();

        while (deployCnt < progresses.length) {
            for (int i = 0; i < progresses.length; i++) {
                progresses[i] += speeds[i];
            }

            int canDeploy = 0;
            for (int i = 0; i < progresses.length; i++) {
                if (isDeployed[i]) {
                    continue;
                } else if (progresses[i] >= 100 && (i == 0 || isDeployed[i - 1])) {
                    canDeploy++;
                    isDeployed[i] = true;
                } else {
                    break;
                }
            }
            if (canDeploy > 0) {
                deployCnt += canDeploy;
                deployDays.add(canDeploy);
            }
        }
        answer = new int[deployDays.size()];
        for (int i = 0; i < deployDays.size(); i++) {
            answer[i] = deployDays.get(i);
        }

        return answer;
    }
}