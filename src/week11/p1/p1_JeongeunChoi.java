package algorithm.src.week11.p1;

import java.io.*;

/**
 * 문제 링크: https://www.acmicpc.net/problem/5639
 * 메모리: 33648 KB
 * 시간: 616 ms
 * 시간 복잡도: O(N)
 * 공간 복잡도: O(N)
 */

/**
 * 풀이 참고한 문제
 * 전위 순회한 값을 후위 순회하여 출력해야 하는 문제
 * 전위 순회한 결과를 왼쪽 트리, 오른쪽 트리로 분할하여 탐색하며 출력
 */

public class p1_JeongeunChoi {
    static int[] tree = new int[10001];
    static int i;

    static void dfs(int s, int e) {
        if(s<e){
            for(i= s+1; i<e; i++){
                if(tree[s]<tree[i]) break;
            }

            dfs(s+1, i);
            dfs(i, e);
            System.out.println(tree[s]);
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str;
        int i = 0;

        while((str=br.readLine())!=null){
            tree[i++]=Integer.parseInt(str);
        }

        dfs(0, i);
    }
}