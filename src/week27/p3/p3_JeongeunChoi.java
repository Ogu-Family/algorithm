package algorithm.src.week27.p3;

import java.io.*;
import java.util.*;

/**
 * 문제 링크: https://www.acmicpc.net/problem/21609
 * 메모리: 15692 KB
 * 시간: 160 ms
 * 시간 복잡도: O(N^2) 이상
 * 공간 복잡도: O(N^2)
 */

/**
 * bfs + 구현
 * 1. 크기가 가장 큰 블록 그룹 찾기
 * 2. 해당 블록 그룹 제거
 * 3. 중력
 * 4. 90도 반시계 방향 회전
 * 5. 중력
 */

public class p3_JeongeunChoi {

    private static int N, M, answer = 0;
    private static int[] dx = {0, 1, 0, -1}, dy = {1, 0, -1, 0};
    private static int[][] map, blockNums;
    private static ArrayList<BlockGroup> blockGroups = new ArrayList<>();

    private static void findBlock() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] > 0 && blockNums[i][j] == 0) {
                    int blockCnt = 0, color = map[i][j], blockNum =
                            blockGroups.size() + 1, rainbowBlockCnt = 0;
                    Queue<Location> q = new LinkedList<>();
                    q.add(new Location(i, j));
                    blockNums[i][j] = blockNum;
                    blockCnt++;

                    while (!q.isEmpty()) {
                        Location location = q.poll();
                        for (int k = 0; k < 4; k++) {
                            int nx = location.x + dx[k], ny = location.y + dy[k];
                            if (!outOfBound(nx, ny) && blockNums[nx][ny] != blockNum && (
                                    map[nx][ny] == color || map[nx][ny] == 0)) {
                                q.add(new Location(nx, ny));
                                blockNums[nx][ny] = blockNum;
                                blockCnt++;
                                if (map[nx][ny] == 0) {
                                    rainbowBlockCnt++;
                                }
                            }
                        }
                    }

                    if (blockCnt >= 2) {
                        blockGroups.add(
                                new BlockGroup(new Location(i, j), blockCnt, rainbowBlockCnt,
                                        color));
                    }
                }
            }
        }
    }

    private static void removeBlock(BlockGroup blockGroup) {
        Queue<Location> q = new LinkedList<>();
        Location location = blockGroup.location;
        q.add(location);
        map[location.x][location.y] = -2;

        while (!q.isEmpty()) {
            location = q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = location.x + dx[i], ny = location.y + dy[i];
                if (!outOfBound(nx, ny) && map[nx][ny] != -2 && (map[nx][ny] == blockGroup.color
                        || map[nx][ny] == 0)) {
                    q.add(new Location(nx, ny));
                    map[nx][ny] = -2;
                }
            }
        }
    }

    private static void getGravity() {
        for (int i = 0; i < N; i++) {
            for (int j = N - 2; j >= 0; j--) {
                if (map[j][i] >= 0) {
                    int k = j;
                    boolean canMove = false;
                    while (k + 1 < N && map[k + 1][i] == -2) {
                        canMove = true;
                        k++;
                    }
                    if (canMove) {
                        map[k][i] = map[j][i];
                        map[j][i] = -2;
                    }
                }
            }
        }
    }

    private static void rotate() {
        int[][] copyMap = new int[N][N];
        for (int i = 0; i < N; i++) {
            copyMap[i] = Arrays.copyOf(map[i], map[i].length);
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = copyMap[j][N - 1 - i];
            }
        }
    }


    private static boolean outOfBound(int x, int y) {
        return !(x >= 0 && x < N && y >= 0 && y < N);
    }

    private static void input() throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    private static void startAutoPlay() {
        while (true) {
            blockGroups.clear();
            blockNums = new int[N][N];
            // 크기가 가장 큰 블록 그룹 찾기
            findBlock();
            if (blockGroups.size() == 0) {
                break;
            }
            Collections.sort(blockGroups);

            // 해당 블록 그룹 제거
            BlockGroup bigBlockGroup = blockGroups.get(0);
            removeBlock(bigBlockGroup);
            answer += Math.pow(bigBlockGroup.blockCnt, 2);

            // 중력
            getGravity();

            // 90도 반시계 방향 회전
            rotate();

            // 중력
            getGravity();
        }
    }

    public static void main(String[] args) throws Exception {
        input();

        startAutoPlay();

        System.out.println(answer);

    }

    private static class Location {

        int x, y;

        public Location(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static class BlockGroup implements Comparable<BlockGroup> {

        Location location;
        int blockCnt, rainbowBlockCnt, color;

        public BlockGroup(Location location, int blockCnt, int rainbowBlockCnt, int color) {
            this.location = location;
            this.blockCnt = blockCnt;
            this.rainbowBlockCnt = rainbowBlockCnt;
            this.color = color;
        }

        @Override
        public int compareTo(BlockGroup other) {
            if (this.blockCnt != other.blockCnt) {
                return other.blockCnt - this.blockCnt;
            } else if (this.rainbowBlockCnt != other.rainbowBlockCnt) {
                return other.rainbowBlockCnt - this.rainbowBlockCnt;
            } else if (this.location.x != other.location.x) {
                return other.location.x - this.location.x;
            } else {
                return other.location.y - this.location.y;
            }
        }
    }

}
