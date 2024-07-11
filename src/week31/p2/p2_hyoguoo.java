/**
 * 문제 링크: https://www.acmicpc.net/problem/30892
 * 메모리: 57524 KB
 * 시간: 764 ms
 * 시간 복잡도: O(NlogN)
 * 공간 복잡도: O(N)
 */

/**
 * Deque 두 개를 사용해서 구현
 * biggerDeque: 현재 크기보다 큰 것 저장(먹을 수 없는 것)
 * smallerDeque: 현재 크기보다 작은 것 저장(먹을 수 있는 것)
 * 1. 각 Deque에 상어를 넣음(맨 위에 현재 사이즈와 가장 근접한 상어가 들어가도록)
 * 2. 먹을 수 있는 상어 중 가장 큰 상어를 먹음
 * 3. 먹은 뒤 사이즈를 체크하여 먹을 수 없는 상어(biggerDeque) 중 먹을 수 있는 상어(smallerDeque)로 옮김
 * 4. 2~3을 반복
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class FeedingSharks {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int[] info = Arrays.stream(bufferedReader.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        int eatingLimit = info[1];
        int currentSize = info[2];
        int[] feed = Arrays.stream(bufferedReader.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        Shark shark = new Shark(currentSize, eatingLimit);

        System.out.print(solution(shark, feed));
    }

    private static long solution(Shark shark, int[] feed) {
        Arrays.sort(feed);
        Deque<Integer> biggerDeque = new ArrayDeque<>();
        Deque<Integer> smallerDeque = new ArrayDeque<>();
        // 1. 각 Deque에 상어를 넣음(맨 위에 현재 사이즈와 가장 근접한 상어가 들어가도록)
        for (int f : feed) {
            if (!shark.isEatableSize(f)) {
                biggerDeque.addLast(f);
            } else {
                smallerDeque.addFirst(f);
            }
        }

        // 4. 2~3을 반복
        while (shark.isEatingLimitRemaining() && !smallerDeque.isEmpty()) {
            // 2. 먹을 수 있는 상어 중 가장 큰 상어를 먹음
            shark.eat(smallerDeque.pollFirst());

            // 3. 먹은 뒤 사이즈를 체크하여 먹을 수 없는 상어(biggerDeque) 중 먹을 수 있는 상어(smallerDeque)로 옮김
            while (!biggerDeque.isEmpty() && shark.isEatableSize(biggerDeque.peekFirst())) {
                smallerDeque.addFirst(biggerDeque.pollFirst());
            }
        }

        return shark.getSize();
    }

    static class Shark {

        private long size;
        private int eatingLimit;

        public Shark(long size, int eatingLimit) {
            this.size = size;
            this.eatingLimit = eatingLimit;
        }

        public boolean isEatingLimitRemaining() {
            return this.eatingLimit > 0;
        }

        public boolean isEatableSize(int feed) {
            return this.size > feed;
        }

        public void eat(int feed) {
            if (this.isEatingLimitRemaining() && this.isEatableSize(feed)) {
                this.size += feed;
                this.eatingLimit--;
            }
        }

        public long getSize() {
            return size;
        }
    }
}
