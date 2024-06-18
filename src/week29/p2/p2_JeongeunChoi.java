package algorithm.src.week29.p2;

import java.io.*;
import java.util.*;

/**
 * 문제 링크: https://www.acmicpc.net/problem/5179
 * 메모리: 16264 KB
 * 시간: 164 ms
 * 시간 복잡도: O(NlogN) N은 참가자의 수
 * 공간 복잡도: O(N*M) M은 문제의 개수
 */

/**
 * 구현
 * 1. 2차원 배열에 참가자별 문제에 대한 점수를 저장한다.
 * 2. 푼 문제 수 기준 내림차순 정렬한다. 푼 문제 수가 같으면 총점 기준 오름차순 정렬한다.
 * 3. 1위부터 한명씩 출력한다.
 */

public class p2_JeongeunChoi {

    private static BufferedReader br;

    public static ArrayList<ParticipantScore> organizeSubmission(int problemCnt, int submissionCnt, int participantCnt) throws Exception {
        int[][] score = new int[participantCnt + 1][problemCnt];
        boolean[][] corrected = new boolean[participantCnt + 1][problemCnt];
        for (int j = 0; j < submissionCnt; j++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int participantNum = Integer.parseInt(st.nextToken()), problemNum =
                    st.nextToken().charAt(0) - 'A', submitTime = Integer.parseInt(
                    st.nextToken()), isAnswer = Integer.parseInt(st.nextToken());
            if (!corrected[participantNum][problemNum]) {
                if (isAnswer == 1) {
                    score[participantNum][problemNum] += submitTime;
                    corrected[participantNum][problemNum] = true;
                } else {
                    score[participantNum][problemNum] += 20;
                }
            }
        }

        return rankParticipants(score, corrected);
    }

    private static ArrayList<ParticipantScore> rankParticipants(int[][] score, boolean[][] corrected) {
        ArrayList<ParticipantScore> participantScores = new ArrayList<>();
        for (int j = 1; j < score.length; j++) {
            ParticipantScore participantScore = new ParticipantScore(j);
            for (int k = 0; k < score[j].length; k++) {
                if (corrected[j][k]) {
                    participantScore.increaseSolvedCntAndScore(score[j][k]);
                }
            }
            participantScores.add(participantScore);
        }

        Collections.sort(participantScores);

        return participantScores;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));

        int K, problemCnt, submissionCnt, participantCnt;
        K = Integer.parseInt(br.readLine());
        for (int i = 1; i <= K; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            problemCnt = Integer.parseInt(st.nextToken());
            submissionCnt = Integer.parseInt(st.nextToken());
            participantCnt = Integer.parseInt(st.nextToken());

            ArrayList<ParticipantScore> participantScores = organizeSubmission(problemCnt,
                    submissionCnt, participantCnt);
            System.out.println("Data Set " + i + ":");
            for (int j = 0; j < participantScores.size(); j++) {
                ParticipantScore participantScore = participantScores.get(j);
                System.out.println(participantScore.id + " " + participantScore.solvedCnt + " "
                        + participantScore.score);
            }
            System.out.println();
        }


    }

    private static class ParticipantScore implements Comparable<ParticipantScore> {

        int id, solvedCnt, score;

        ParticipantScore(int id) {
            this.id = id;
        }

        public int compareTo(ParticipantScore other) {
            if (this.solvedCnt != other.solvedCnt) {
                return other.solvedCnt - this.solvedCnt;
            } else {
                return this.score - other.score;
            }
        }

        public void increaseSolvedCntAndScore(int score) {
            this.solvedCnt++;
            this.score += score;
        }
    }

}
