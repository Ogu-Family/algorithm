/**
 * 문제 링크: https://www.acmicpc.net/problem/5179
 * 메모리: 16676 KB
 * 시간: 172 ms
 * 시간 복잡도: O(logN)
 * 공간 복잡도: O(N)
 */

/**
 * 참가자 클래스 생성하여 적절한 메소드를 구현하여 문제 해결
 * 모든 쿼리 수행 후 Comparator를 이용하여 정렬하여 결과 출력
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class WhoGetPrizes {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int testCount = Integer.parseInt(bufferedReader.readLine());

        StringBuilder stringBuilder = new StringBuilder();

        for (int test = 0; test < testCount; test++) {
            int[] info = Arrays.stream(bufferedReader.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            int submitCount = info[1];
            int participantCount = info[2];

            Query[] queries = new Query[submitCount];
            for (int i = 0; i < submitCount; i++) {
                String[] queryInfo = bufferedReader.readLine().split(" ");
                queries[i] = new Query(
                        Integer.parseInt(queryInfo[0]),
                        queryInfo[1].charAt(0),
                        Integer.parseInt(queryInfo[2]),
                        Integer.parseInt(queryInfo[3])
                );
            }

            stringBuilder
                    .append("Data Set ")
                    .append(test + 1)
                    .append(":\n")
                    .append(solution(queries, participantCount))
                    .append("\n");
        }

        System.out.print(stringBuilder.toString().trim());
    }

    private static String solution(Query[] queries, int participantCount) {
        Participant[] participants = initializeParticipants(participantCount);

        for (Query query : queries) {
            participants[query.getParticipantIndex()].submit(
                    query.getProblemId(),
                    query.getSubmitTime(),
                    query.getCorrectness()
            );
        }

        Arrays.sort(participants, 1, participantCount + 1, prizeComparator());

        return resultToString(participantCount, participants);
    }

    private static Participant[] initializeParticipants(int participantCount) {
        Participant[] participants = new Participant[participantCount + 1];
        for (int id = 1; id <= participantCount; id++) {
            participants[id] = new Participant(id);
        }
        return participants;
    }

    private static Comparator<Participant> prizeComparator() {
        return (p1, p2) -> {
            if (p1.getSolvedProblemCount() == p2.getSolvedProblemCount()) {
                return Integer.compare(p1.getScore(), p2.getScore());
            }
            return Integer.compare(p2.getSolvedProblemCount(), p1.getSolvedProblemCount());
        };
    }

    private static String resultToString(int participantCount, Participant[] participants) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 1; i < participantCount + 1; i++) {
            stringBuilder.append(participants[i]).append("\n");
        }

        return stringBuilder.toString();
    }

    enum Correctness {
        CORRECT(1),
        INCORRECT(0);

        private final int value;

        Correctness(int value) {
            this.value = value;
        }

        public static Correctness of(int value) {
            return Arrays.stream(values())
                    .filter(correctness -> correctness.value == value)
                    .findFirst()
                    .orElseThrow(IllegalArgumentException::new);
        }
    }

    static class Participant {

        private static final int INCORRECT_SCORE_WEIGHT = 20;
        private final int participantId;
        private final Map<Character, Integer> problemFailCount;
        private final Map<Character, Integer> problemSubmitTime;
        private final Set<Character> solvedProblems;
        private int score;

        public Participant(int participantId) {
            this.participantId = participantId;
            this.problemFailCount = new HashMap<>();
            this.problemSubmitTime = new HashMap<>();
            this.solvedProblems = new HashSet<>();
            this.score = 0;
        }

        public void submit(char problemId, int submitTime, Correctness correctness) {
            switch (correctness) {
                case CORRECT:
                    correctProblem(problemId, submitTime);
                    break;
                case INCORRECT:
                    incorrectProblem(problemId);
                    break;
                default:
                    throw new IllegalArgumentException();
            }
        }

        public int getScore() {
            return score;
        }

        public int getSolvedProblemCount() {
            return solvedProblems.size();
        }

        private void correctProblem(char problemId, int submitTime) {
            if (solvedProblems.contains(problemId)) {
                return;
            }
            solvedProblems.add(problemId);
            problemSubmitTime.put(problemId, submitTime);
            addScore(problemId);
        }

        private void addScore(char problemId) {
            score += problemSubmitTime.get(problemId);
            if (problemFailCount.containsKey(problemId)) {
                score += problemFailCount.get(problemId) * INCORRECT_SCORE_WEIGHT;
            }
        }

        private void incorrectProblem(char problemId) {
            if (solvedProblems.contains(problemId)) {
                return;
            }
            problemFailCount.put(problemId, problemFailCount.getOrDefault(problemId, 0) + 1);
        }

        @Override
        public String toString() {
            return participantId + " " + solvedProblems.size() + " " + score;
        }
    }

    static class Query {

        private final int participantIndex;
        private final char problemId;
        private final int submitTime;
        private final Correctness correctness;

        public Query(int participantIndex, char problemId, int submitTime, int correctness) {
            this.participantIndex = participantIndex;
            this.problemId = problemId;
            this.submitTime = submitTime;
            this.correctness = Correctness.of(correctness);
        }

        public int getParticipantIndex() {
            return participantIndex;
        }

        public char getProblemId() {
            return problemId;
        }

        public int getSubmitTime() {
            return submitTime;
        }

        public Correctness getCorrectness() {
            return correctness;
        }
    }
}
