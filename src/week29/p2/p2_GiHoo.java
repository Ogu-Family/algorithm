package src.week29.p2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * 문제 링크: https://www.acmicpc.net/problem/5179
 * 메모리: 16304 KB
 * 시간: 164 ms
 * 시간 복잡도: O(NlogN)
 * 공간 복잡도: O(N ^ 2)
 */

/**
 * 구현 문제
 * 주어진 조건을 고려하여 구현하면 되는 문제
 *
 * 유의했던 부분
 *  본인이 이전에 틀렸던 횟수만큼 추가되는 점수를 알기 위해 failCounts[][] 사용
 *  푼 문제 수가 같으면 점수가 낮은 순서대로 출력하는 부분
 *
 */

public class p2_GiHoo {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        int testCase = Integer.parseInt(br.readLine());

        for (int i = 0; i < testCase; i++) {
            solution(i + 1);
        }
    }

    private static void solution(int testCase) throws IOException {
        st = new StringTokenizer(br.readLine());

        int problemCount = Integer.parseInt(st.nextToken());
        int submitCount = Integer.parseInt(st.nextToken());
        int participantCount = Integer.parseInt(st.nextToken());

        int[][] correctMatrix = new int[participantCount + 1][problemCount];
        int[][] failCounts = new int[participantCount + 1][problemCount];
        Map<Integer, Participant> participantMap = new HashMap<>();

        for (int i = 0; i < submitCount; i++) {
            st = new StringTokenizer(br.readLine());

            int participantId = Integer.parseInt(st.nextToken());
            int problemId = st.nextToken().charAt(0) - 'A';
            int submitTime = Integer.parseInt(st.nextToken());
            int isCorrect = Integer.parseInt(st.nextToken());

            Participant participant; // 해당 참가자 뽑기
            if (participantMap.containsKey(participantId)) {
                participant = participantMap.get(participantId);
                participantMap.remove(participantId);
            } else {
                participant = new Participant(participantId);
            }

            if (isCorrect == 1) { // 맞았을 때
                if (correctMatrix[participantId][problemId] == 0) { // 이전에 정답이지 않을 때
                    int addScore = submitTime + (20 * failCounts[participantId][problemId]);
                    participant.addScore(addScore);

                    correctMatrix[participantId][problemId] = 1;
                }
            } else { // 틀렸을 때
                failCounts[participantId][problemId]++;
            }

            participantMap.put(participantId, participant);
        }

        List<Participant> participantList = new ArrayList<>(participantMap.values());

        participantList.sort((o1, o2) -> {
            if (o1.correctCount == o2.correctCount) {
                return o1.score - o2.score;
            } else {
                return o2.correctCount - o1.correctCount;
            }
        });

        System.out.println("Data Set " + testCase + ":");
        for (Participant participant : participantList) {
            System.out.println(participant.participantId + " " + participant.correctCount + " "
                    + participant.score);
        }
        System.out.println();
    }

    static class Participant {

        int participantId;
        int correctCount;
        int score;

        public Participant(int participantId) {
            this.participantId = participantId;
            this.correctCount = 0;
            this.score = 0;
        }

        public void addScore(int score) {
            this.correctCount++;
            this.score += score;
        }
    }
}
