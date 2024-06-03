/**
 * 문제 링크: https://www.acmicpc.net/problem/1713
 * 메모리: 15620 KB
 * 시간: 200 ms
 * 시간 복잡도: O(NlogN)
 * 공간 복잡도: O(N)
 */

/**
 * PriorityQueue를 이용하여 시간 / 득표 수에 따라 후보자를 갱신
 *
 * 1. 이미 후보자에 있는지 확인
 * 2. 있는 경우 득표 수 증가
 * 3. 없는데 후보자가 꽉 찼다면 가장 오래된 후보자 제거
 * 4. 후보자 추가
 * 5. 후보자 시간 갱신
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;
import java.util.PriorityQueue;

public class NominateCandidates {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int target = Integer.parseInt(bufferedReader.readLine());
        bufferedReader.readLine();
        int[] votes = Arrays.stream(bufferedReader.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        System.out.print(solution(target, votes));
    }

    private static String solution(int target, int[] votes) {
        PriorityQueue<Candidate> priorityQueue = new PriorityQueue<>(
                (o1, o2) -> {
                    if (o1.recommendCount == o2.recommendCount) {
                        return o2.time - o1.time;
                    }
                    return o1.recommendCount - o2.recommendCount;
                }
        );

        for (int vote : votes) {
            Optional<Candidate> candidate = findCandidate(priorityQueue, vote);

            if (candidate.isPresent()) {
                candidate.get().recommend();
            } else {
                if (priorityQueue.size() == target) {
                    priorityQueue.poll();
                }
                priorityQueue.add(new Candidate(vote));
            }

            updateCandidateTimes(priorityQueue);
        }

        return getSortedCandidate(priorityQueue);
    }

    private static void updateCandidateTimes(PriorityQueue<Candidate> priorityQueue) {
        priorityQueue.forEach(Candidate::updateTime); // 업데이트 만으론 pq가 재정렬되지 않음
        // pq 재정렬을 위해 임시 pq에 복사 후 다시 넣어줌
        PriorityQueue<Candidate> temp = new PriorityQueue<>(priorityQueue);
        priorityQueue.clear();
        priorityQueue.addAll(temp);
    }

    private static String getSortedCandidate(PriorityQueue<Candidate> priorityQueue) {
        StringBuilder stringBuilder = new StringBuilder();

        priorityQueue.stream()
                .sorted(Comparator.comparingInt(Candidate::getNumber))
                .forEach(candidate -> stringBuilder.append(candidate.getNumber()).append(" "));

        return stringBuilder.toString().trim();
    }

    private static Optional<Candidate> findCandidate(
            PriorityQueue<Candidate> priorityQueue,
            int vote
    ) {
        return priorityQueue.stream()
                .filter(candidate -> candidate.getNumber() == vote)
                .findFirst();
    }

    static class Candidate {

        private final int number;
        private int recommendCount;
        private int time;

        public Candidate(int number) {
            this.number = number;
            this.recommendCount = 1;
            this.time = 0;
        }

        public void recommend() {
            recommendCount++;
        }

        public void updateTime() {
            time++;
        }

        public int getNumber() {
            return number;
        }
    }
}
