/**
 * 문제 링크: https://www.acmicpc.net/problem/3987
 * 메모리: 47460 KB
 * 시간: 348 ms
 * 시간 복잡도: O(N * M)
 * 공간 복잡도: O(N * M)
 */

/**
 * 1. 상하좌우 순으로 탐색
 * 2. 탐색 중 블랙홀을 만나거나 맵을 벗어나면 탐색 종료
 * 3. / \ 인 경우 방향 변경
 * 4. 탐색 중 방문한 좌표를 저장하여 무한 루프인지 확인
 * 5. 최대 시간과 방향을 저장하여 반환
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Voyager1 {

    private static final char BLACK_HOLE = 'C';
    private static final int INFINITE = -1;
    private static final char SLASH = '/';
    private static final char BACK_SLASH = '\\';

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int[] info = Arrays.stream(bufferedReader.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        int sizeN = info[0];
        int sizeM = info[1];

        char[][] map = new char[sizeN][sizeM];

        for (int n = 0; n < sizeN; n++) {
            map[n] = bufferedReader.readLine().toCharArray();
        }

        int[] position = Arrays.stream(bufferedReader.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        System.out.print(
                solution(map, new Coordinate(position[0] - 1, position[1] - 1))
        );
    }

    private static String solution(char[][] map, Coordinate startCoordinate) {
        Direction resultDirection = null;
        int resultTime = 0;

        // 상하좌우 순으로 탐색
        for (Direction direction : Direction.values()) {
            int time = getTime(map, new Voyager(startCoordinate, direction));

            if (time == INFINITE) {
                return direction + "\n" + "Voyager";
            }

            if (resultTime < time) {
                resultTime = time;
                resultDirection = direction;
            }
        }

        return resultDirection + "\n" + resultTime;
    }

    private static int getTime(char[][] map, Voyager voyager) {
        // 특정 방향으로 방문한 좌표를 저장
        Direction[][] visited = new Direction[map.length][map[0].length];

        int time = 0;

        do {
            // 이미 같은 방향으로 방문한 경우
            if (voyager.isVisitedEqualsDirection(visited)) {
                return INFINITE;
            }
            time++;
            voyager.checkVisited(visited); // 방문한 좌표 저장
        } while (voyager.move(map)); // 이동

        return time;
    }

    enum Direction {
        U(-1, 0),
        R(0, 1),
        D(1, 0),
        L(0, -1);

        private final int n;
        private final int m;

        Direction(int n, int m) {
            this.n = n;
            this.m = m;
        }

        public Direction turn(char turn) {
            if (turn == SLASH) {
                switch (this) {
                    case U:
                        return R;
                    case R:
                        return U;
                    case D:
                        return L;
                    case L:
                        return D;
                }
            } else if (turn == BACK_SLASH) {
                switch (this) {
                    case U:
                        return L;
                    case R:
                        return D;
                    case D:
                        return R;
                    case L:
                        return U;
                }
            }

            throw new IllegalArgumentException();
        }
    }

    static class Voyager {

        private Coordinate coordinate;
        private Direction direction;

        public Voyager(Coordinate coordinate, Direction direction) {
            this.coordinate = coordinate;
            this.direction = direction;
        }

        public boolean move(char[][] map) {
            Coordinate nextCoordinate = getNextCoordinate(); // 다음 좌표 계산

            // 블랙홀이거나 맵을 벗어난 경우 false 반환
            if (isOutBound(nextCoordinate, map) ||
                    map[nextCoordinate.n][nextCoordinate.m] == BLACK_HOLE) {
                return false;
            }

            // / \ 인 경우 방향 변경
            if (map[nextCoordinate.n][nextCoordinate.m] == SLASH ||
                    map[nextCoordinate.n][nextCoordinate.m] == BACK_SLASH) {
                direction = direction.turn(map[nextCoordinate.n][nextCoordinate.m]);
            }

            // 다음 좌표로 이동
            coordinate = nextCoordinate;

            return true;
        }

        public void checkVisited(Direction[][] visited) {
            visited[coordinate.n][coordinate.m] = direction;
        }

        public boolean isVisitedEqualsDirection(Direction[][] visited) {
            return visited[coordinate.n][coordinate.m] == direction;
        }

        private Coordinate getNextCoordinate() {
            return new Coordinate(
                    coordinate.n + direction.n,
                    coordinate.m + direction.m
            );
        }

        private boolean isOutBound(Coordinate coordinate, char[][] map) {
            return coordinate.n < 0 || map.length <= coordinate.n ||
                    coordinate.m < 0 || map[0].length <= coordinate.m;
        }
    }

    static class Coordinate {

        private final int n;
        private final int m;

        public Coordinate(int n, int m) {
            this.n = n;
            this.m = m;
        }
    }
}
