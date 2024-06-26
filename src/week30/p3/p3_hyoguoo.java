/**
 * 문제 링크: https://www.acmicpc.net/problem/14923
 * 메모리: 317668KB
 * 시간: 1484ms
 * 시간 복잡도: O(N*M)
 * 공간 복잡도: O(N*M)
 */

/**
 * BFS + 하나의 차원 추가
 *
 * 기존 BFS와 동일하게 접근하되, 벽인 경우 + 부술 수 있는 차원인 경우를 추가로 고려
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class EscapeMaze {

    public static void main(String[] args) throws IOException {
        System.out.print(solution(parseMap()));
    }

    private static Map parseMap() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int[] sizeInfo = Arrays.stream(bufferedReader.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        int sizeN = sizeInfo[0];
        int sizeM = sizeInfo[1];
        int[][] maze = new int[sizeN][sizeM];
        int[] startInfo = Arrays.stream(bufferedReader.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        Coordinate start = new Coordinate(startInfo[0] - 1, startInfo[1] - 1);
        int[] endInfo = Arrays.stream(bufferedReader.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        Coordinate end = new Coordinate(endInfo[0] - 1, endInfo[1] - 1);
        for (int n = 0; n < sizeN; n++) {
            maze[n] = Arrays.stream(bufferedReader.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        return new Map(maze, start, end);
    }

    private static int solution(Map map) {
        return map.simulate();
    }

    enum Status {
        WALL(1),
        EMPTY(0);

        private final int value;

        Status(int value) {
            this.value = value;
        }

        public static Status of(int value) {
            return Arrays.stream(Status.values())
                    .filter(status -> status.value == value)
                    .findFirst()
                    .orElseThrow(IllegalArgumentException::new);
        }
    }

    enum Direction {
        UP(-1, 0),
        DOWN(1, 0),
        LEFT(0, -1),
        RIGHT(0, 1);

        private final int n;
        private final int m;

        Direction(int n, int m) {
            this.n = n;
            this.m = m;
        }
    }

    static class Map {

        private static final int BREAK_LIMIT = 1;
        private final Status[][] maze;
        private final int[][][] visited;
        private final Coordinate end;
        private final Deque<CoordinateDepth> coordinateDeque;

        public Map(int[][] maze, Coordinate start, Coordinate end) {
            this.maze = new Status[maze.length][maze[0].length];
            for (int n = 0; n < maze.length; n++) {
                for (int m = 0; m < maze[0].length; m++) {
                    this.maze[n][m] = Status.of(maze[n][m]);
                }
            }
            this.coordinateDeque = new ArrayDeque<>();
            this.coordinateDeque.add(new CoordinateDepth(start, 0));
            this.end = end;

            this.visited = new int[maze.length][maze[0].length][BREAK_LIMIT + 1];
        }

        public int simulate() { // 핵심 로직
            while (!coordinateDeque.isEmpty()) { // BFS 시작
                CoordinateDepth current = coordinateDeque.poll();

                if (isArrived(current)) { // 도착 지점인 경우 반환
                    return visited[current.getN()][current.getM()][current.getDepth()];
                }

                for (Direction direction : Direction.values()) { // 상하좌우 이동
                    Coordinate next = current.move(direction); // 다음 좌표 계산
                    calculateNextDistance(next, current);
                }
            }

            return -1;
        }

        private void calculateNextDistance(Coordinate next, CoordinateDepth current) {
            if (!isInBound(next)) { // 범위를 벗어난 경우
                return;
            }

            if (isBreakableWall(next, current.getDepth())) { // 벽이면서 아직 한 번도 부수지 않은 경우(=depth 0)
                for (int depth = current.getDepth() + 1; depth <= BREAK_LIMIT; depth++) {
                    if (isNotVisited(next, depth)) {
                        visited[next.getN()][next.getM()][depth] = getDistance(current) + 1;
                        coordinateDeque.add(new CoordinateDepth(next, depth));
                    }
                }
            }

            if (maze[next.getN()][next.getM()] == Status.EMPTY) { // 벽이 아닌 경우
                for (int depth = current.getDepth(); depth <= BREAK_LIMIT; depth++) {
                    if (isNotVisited(next, depth)) {
                        visited[next.getN()][next.getM()][depth] = getDistance(current) + 1;
                        coordinateDeque.add(new CoordinateDepth(next, depth));
                    }
                }
            }
        }

        private int getDistance(CoordinateDepth current) {
            return visited[current.getN()][current.getM()][current.getDepth()];
        }

        private boolean isNotVisited(Coordinate next, int depth) {
            return visited[next.getN()][next.getM()][depth] == 0;
        }

        private boolean isBreakableWall(Coordinate next, int currentDepth) {
            return currentDepth < BREAK_LIMIT &&
                    maze[next.getN()][next.getM()] == Status.WALL;
        }

        private boolean isInBound(Coordinate coordinate) {
            return 0 <= coordinate.n && coordinate.n < maze.length &&
                    0 <= coordinate.m && coordinate.m < maze[0].length;
        }

        private boolean isArrived(Coordinate coordinate) {
            return coordinate.getN() == end.getN() && coordinate.getM() == end.getM();
        }
    }

    static class CoordinateDepth extends Coordinate {

        private final int depth;

        public CoordinateDepth(Coordinate coordinate, int depth) {
            super(coordinate.n, coordinate.m);
            this.depth = depth;
        }

        public int getDepth() {
            return depth;
        }
    }

    static class Coordinate {

        private final int n;
        private final int m;

        public Coordinate(int n, int m) {
            this.n = n;
            this.m = m;
        }

        public int getN() {
            return n;
        }

        public int getM() {
            return m;
        }

        public Coordinate move(Direction direction) {
            return new Coordinate(this.n + direction.n, this.m + direction.m);
        }
    }
}
