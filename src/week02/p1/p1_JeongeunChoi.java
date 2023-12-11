package algorithm.src.week02.p1;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class p1_JeongeunChoi {

    static int tableLength;
    static int distance;
    static String bench;
    static List<Integer> people = new ArrayList<>();
    static List<Boolean> eaten = new ArrayList<>();
    static int maxEatingCnt = 0;

    public static void eatingHamburger() {
        for (int i = 0; i < people.size(); i++) {
            int person = people.get(i);
            int start = (person - distance > 0) ? (person - distance) : 0;
            int end = (person + distance) < bench.length() ? (person + distance) : bench.length() - 1;
            for (int j = start; j <= end; j++) {
                if (bench.charAt(j) == 'H' && !eaten.get(j)) {
                    maxEatingCnt++;
                    eaten.set(j, true);
                    break;
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        tableLength = Integer.parseInt(st.nextToken());
        distance = Integer.parseInt(st.nextToken());
        bench = br.readLine();

        for (int i = 0; i < bench.length(); i++) {
            if (bench.charAt(i) == 'P') {
                people.add(i);
            }
            eaten.add(false);
        }
        eatingHamburger();

        System.out.println(maxEatingCnt);
    }
}

/**
 * 문제 링크: https://www.acmicpc.net/problem/19941
 * 메모리: 15128KB
 * 시간: 152ms
 * 시간 복잡도: O(N^K) N의 최대값은 20000, K의 최대값은 10
 * 공간 복잡도: O(N)
 */

/**
 * 그리디 알고리즘으로 구현
 * 사람 위치를 리스트에 따로 저장해두고
 * 왼쪽부터 한명씩 햄버거를 먹는다.
 * 한사람당 거리를 기준으로 먹을 수 있는 범위를 정하고
 * 왼쪽부터 먹을 수 있는 햄버거를 찾는다.
 * 가장 왼쪽에 있는 햄버거를 먹게 된다.
 */

// ------------------------------------------------------------

/**
 * 처음에 틀린 시간초과 풀이
 * 시간 복잡도: O(2^N)
 * 공간 복잡도: O(N)
 */

/**
 * DFS로 구현(시간초과 발생)
 * 햄버거를 먹을 수 있는 경우를 모두 탐색하여, 햄버거를 먹을 수 있는 최대 사람 수를 구함.
 */

//    public static void eatingHamburger(int idx, int eatingCnt){
//        if(idx==people.size() || maxEatingCnt == hamburgerCnt) {
//            if(eatingCnt > maxEatingCnt){
//                maxEatingCnt = eatingCnt;
//            }
//        } else{
//            int person = people.get(idx);
//            int start = (person-distance>0)?(person-distance):0;
//            int end = (person+distance)<bench.length()?(person+distance):bench.length()-1;
//            for(int i=start; i<=end; i++){
//                if(bench.charAt(i)=='H' && !eaten.get(i)){
//                    eaten.set(i, true);
//                    eatingHamburger(idx+1, eatingCnt+1);
//                    eaten.set(i, false);
//                }
//            }
//            eatingHamburger(idx+1, eatingCnt);
//        }
//    }


