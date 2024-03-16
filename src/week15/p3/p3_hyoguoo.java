/**
 * 문제 링크: https://www.acmicpc.net/problem/21944
 * 메모리: 75492 KB
 * 시간: 928 ms
 * 시간 복잡도: O(MlogN)
 * 공간 복잡도: O(N)
 */

/**
 * 여러 자료구조를 사용하면서 필요한 정보를 저장하고, 쿼리에 따라 처리하는 문제
 * TreeSet<Problem> problemTree: 문제를 난이도 순으로 저장
 * Map<String, TreeSet<Problem>> problemTreeByAlgorithmType: 알고리즘 분류 별 문제를 난이도 순으로 저장
 * Map<Integer, Problem> problemDataPK: 문제 번호를 키로 하여 문제를 저장
 *
 * TreeSet을 적극 활용하여 난이도 순으로 정렬된 문제를 가져오는 것이 핵심인 문제였다..
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.TreeSet;

public class ProblemRecommendSystemV2 {

    static final BufferedReader BUFFERED_READER =
            new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        ProblemStore problemStore = initProblemStore();

        System.out.print(solution(problemStore));
    }

    private static ProblemStore initProblemStore() throws IOException {
        int problemCount = Integer.parseInt(BUFFERED_READER.readLine());
        ProblemStore problemStore = new ProblemStore();

        for (int i = 0; i < problemCount; i++) {
            String[] input = BUFFERED_READER.readLine().split(" ");
            int problemNumber = Integer.parseInt(input[0]);
            int level = Integer.parseInt(input[1]);
            String algorithmType = input[2];

            problemStore.addProblem(problemNumber, level, algorithmType);
        }

        return problemStore;
    }

    private static String solution(ProblemStore problemStore) throws IOException {
        int queryCount = Integer.parseInt(BUFFERED_READER.readLine());

        StringBuilder stringBuilder = new StringBuilder();
        RecommendQueryExecutor recommendQueryExecutor = new RecommendQueryExecutor(problemStore);

        for (int i = 0; i < queryCount; i++) {
            String[] input = BUFFERED_READER.readLine().split(" ");
            String command = input[0];

            if (command.startsWith("recommend")) {
                stringBuilder.append(recommendQueryExecutor.execute(input)).append("\n");
            } else if (command.equals("add")) {
                int problemNumber = Integer.parseInt(input[1]);
                int problemLevel = Integer.parseInt(input[2]);
                String problemAlgorithmType = input[3];

                problemStore.addProblem(problemNumber, problemLevel, problemAlgorithmType);
            } else if (command.equals("solved")) {
                int solvedProblemNumber = Integer.parseInt(input[1]);

                problemStore.removeProblem(solvedProblemNumber);
            } else {
                throw new IllegalStateException();
            }
        }

        return stringBuilder.toString().trim();
    }

    static class RecommendQueryExecutor {

        private static final int NOT_FOUND = -1;
        private static final String SEARCH_TYPE_ONE = "1";
        private final ProblemStore problemStore;

        public RecommendQueryExecutor(ProblemStore problemStore) {
            this.problemStore = problemStore;
        }

        public int execute(String[] input) {
            switch (input[0]) {
                case "recommend":
                    return recommend(input[1], input[2]);
                case "recommend2":
                    return recommend2(input[1]);
                case "recommend3":
                    return recommend3(Integer.parseInt(input[2]), input[1]);
                default:
                    throw new IllegalStateException();
            }
        }

        private int recommend(String algorithmType, String searchType) {
            Problem problem = searchType.equals(SEARCH_TYPE_ONE)
                    ? problemStore.getHardestByAlgorithmType(algorithmType)
                    : problemStore.getEasiestByAlgorithmType(algorithmType);

            return problem.getProblemNumber();
        }

        private int recommend2(String searchType) {
            Problem problem = searchType.equals(SEARCH_TYPE_ONE)
                    ? problemStore.getHardest()
                    : problemStore.getEasiest();

            return problem.getProblemNumber();
        }

        private int recommend3(int level, String searchType) {
            Problem problem = searchType.equals(SEARCH_TYPE_ONE)
                    ? problemStore.getEasiestGreaterThan(level)
                    : problemStore.getHardestLessThan(level);

            return problem == null
                    ? NOT_FOUND
                    : problem.getProblemNumber();
        }
    }

    static class ProblemStore {

        private final TreeSet<Problem> problemTree;
        private final Map<String, TreeSet<Problem>> problemTreeByAlgorithmType;
        private final Map<Integer, Problem> problemDataPK;

        public ProblemStore() {
            this.problemTree = new TreeSet<>();
            this.problemTreeByAlgorithmType = new HashMap<>();
            this.problemDataPK = new HashMap<>();
        }

        public void addProblem(int problemNumber, int level, String algorithmType) {
            Problem problem = new Problem(problemNumber, level, algorithmType);

            problemTree.add(problem);
            problemTreeByAlgorithmType
                    .computeIfAbsent(algorithmType, v -> new TreeSet<>())
                    .add(problem);
            problemDataPK.put(problemNumber, problem);
        }

        public void removeProblem(int problemNumber) {
            Problem problem = new Problem(
                    problemNumber,
                    problemDataPK.get(problemNumber).getLevel(),
                    problemDataPK.get(problemNumber).getAlgorithmType()
            );

            problemTree.remove(problem);
            problemTreeByAlgorithmType.get(problem.getAlgorithmType()).remove(problem);
            problemDataPK.remove(problemNumber);
        }

        public Problem getHardest() {
            return problemTree.first();
        }

        public Problem getEasiest() {
            return problemTree.last();
        }

        public Problem getHardestByAlgorithmType(String algorithmType) {
            return problemTreeByAlgorithmType.get(algorithmType).first();
        }

        public Problem getEasiestByAlgorithmType(String algorithmType) {
            return problemTreeByAlgorithmType.get(algorithmType).last();
        }

        public Problem getEasiestGreaterThan(int level) {
            return problemTree.floor(new Problem(0, level, ""));
        }

        public Problem getHardestLessThan(int level) {
            return problemTree.ceiling(new Problem(0, level, ""));
        }
    }


    static class Problem implements Comparable<Problem> {

        private final int problemNumber;
        private final int level;
        private final String algorithmType;

        public Problem(int problemNumber, int level, String algorithmType) {
            this.problemNumber = problemNumber;
            this.level = level;
            this.algorithmType = algorithmType;
        }

        public int getProblemNumber() {
            return problemNumber;
        }

        public int getLevel() {
            return level;
        }

        public String getAlgorithmType() {
            return algorithmType;
        }

        @Override
        public int compareTo(Problem o) {
            if (this.level == o.level) {
                return Integer.compare(o.problemNumber, this.problemNumber);
            }
            return Integer.compare(o.level, this.level);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Problem problem = (Problem) o;
            return getProblemNumber() == problem.getProblemNumber();
        }

        @Override
        public int hashCode() {
            return Objects.hash(getProblemNumber());
        }
    }
}
