package src.week05.p2;

import java.util.*;
import java.io.*;

public class p2_GiHoo {

    static class point {
        public int x;
        public int y;

        public point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int rows;
    static int cols;

    static int[] move2x = {-1,0,1,0};
    static int[] move2y = {0,1,0,-1};
    static int[][] box, distance;

    static Queue<point> queue = new LinkedList<>();

    public static void BFS() {
        while (!queue.isEmpty()) {
            point tmp = queue.poll();
            for (int i = 0; i < move2x.length; i++) {
                int nx = tmp.x + move2x[i];
                int ny = tmp.y + move2y[i];
                if (nx >= 0 && nx < rows && ny >= 0 && ny < cols && box[nx][ny] == 0) {
                    box[nx][ny] = 1;
                    queue.offer(new point(nx, ny));
                    distance[nx][ny] = distance[tmp.x][tmp.y] + 1;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        cols = Integer.parseInt(st.nextToken());
        rows = Integer.parseInt(st.nextToken());

        box = new int[rows][cols];
        distance = new int[rows][cols];

        for(int i=0; i< rows; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<cols; j++){
                box[i][j] = Integer.parseInt(st.nextToken());
                if(box[i][j]==1){
                    queue.offer(new point(i, j));
                    distance[i][j] = 0;
                }
            }
        }

        BFS();

        boolean flag = true;
        for(int i=0; i<rows; i++){
            for(int j=0; j<cols; j++){
                if(box[i][j] == 0) flag = false;
            }
        }

        int answer = Integer.MIN_VALUE;
        if(flag){
            for(int i=0; i<rows; i++){
                for(int j=0; j<cols; j++){
                    answer = Math.max(answer, distance[i][j]);
                }
            }
            System.out.println(answer);
        }
        else{
            System.out.println(-1);
        }
    }
}

/**
 * 문제 링크: https://www.acmicpc.net/problem/7576
 * 메모리: 123188 KB
 * 시간: 672 ms
 * 시간 복잡도: O(N*M)
 * 공간 복잡도: O(N*M)
 *
 * 풀이
 * 기본적인 BFS 문제라고 생각합니다
 * 문제의 중요 포인트는 토마토가 익지 못하는 상황을 판별하는 것
 * distance 배열에 토마토가 익은 최소 날짜를 대입
 */

