package algorithm.src.week30.p1;

import java.io.*;
import java.util.*;

/**
 * 문제 링크: https://www.acmicpc.net/problem/2110
 * 메모리: 29212 KB
 * 시간: 320 ms
 * 시간 복잡도: O(logN)
 * 공간 복잡도: O(N)
 */

/**
 * 풀이 참고한 문제
 *  * 이분탐색
 *  * 가장 인접한 두 공유기 사이의 최대 거리의 범위는
 *  * 연속된 두 집 사이의 최소 거리 D보다 작거나 같아야 한다.
 *  * 두 집 사이의 거리는 무조건 1보다는 크거나 같으므로
 *  * 1, D를 기준으로 이분탐색을 한다.
 *  * (1+D)/2 최소 거리로 설치 가능한 공유기 개수가 설치 해야하는 공유기 개수보다 크거나 같으면 거리를 벌리고
 *  * 그렇지 않으면 거리를 줄인다.
 */

public class p1_JeongeunChoi {

    private static int installRouter(int[] houses, int distance){
        int cnt = 1, house = houses[0];
        for(int i=1; i<houses.length; i++){
            int nextHouse = houses[i];
            if(nextHouse - house >= distance){
                cnt++;
                house = nextHouse;
            }
        }

        return cnt;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int houseCnt = Integer.parseInt(st.nextToken()), routerCnt = Integer.parseInt(st.nextToken()), answer = 0;
        int[] houses = new int[houseCnt];
        for(int i=0; i<houseCnt; i++){
            houses[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(houses);

        int l = 1, r = houses[houseCnt-1] - houses[0];
        while(l<=r){
            int mid = (l+r)/2;
            int installCnt = installRouter(houses, mid);
            if(installCnt >= routerCnt){
                l = mid + 1;
                answer = Math.max(answer, mid);
            } else if(installCnt < routerCnt){
                r = mid - 1;
            }
        }

        System.out.println(answer);
    }

}
