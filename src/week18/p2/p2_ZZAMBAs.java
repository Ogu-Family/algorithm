package src.week18.p2;

/**
 * 문제 링크: https://school.programmers.co.kr/learn/courses/30/lessons/42586
 * 최대 실행 시간: 0.77ms
 * 최대 실행 메모리: 80MB
 * 시간 복잡도: O(N)
 * 공간 복잡도: O(N)
 *
 * 스택, 큐, 덱, 배열
 * 
 * 0. time이란 변수를 선언한다. 이 변수는 개발 시작한 지 며칠이 지났는지를 표기. 최초 값은 0.
 * 1. 뒤에서부터 스택에 넣음. 
 * 2. 스택 최상단을 배포할 때까지 시간을 계산, time에 추가
 * 3. 지난 시간을 바탕으로 배포할 수 있는 것들을 싹 다 pop
 * 4. 스택이 빌 때까지 반복.
 */

import java.util.*;

public class p2_ZZAMBAs {
    static final int DELIVERY_POINT = 100;
    
    public int[] solution(int[] progresses, int[] speeds) {
        ArrayList<Integer> answerList = new ArrayList<>();
        int time = 0;
        Stack<Pair<Integer, Integer>> stk = new Stack<>();
        for (int i = progresses.length - 1; i >= 0; i--) {
            stk.push(new Pair<>(progresses[i], speeds[i]));
        }
        
        while (!stk.isEmpty()) {
            int count = 0;
            Pair<Integer, Integer> top = stk.peek();
            int nowProg = top.progress + time * top.speed;
            int restProg = DELIVERY_POINT - nowProg;
            
            time += restProg / top.speed + (restProg % top.speed != 0 ? 1 : 0);
            
            while (!stk.isEmpty() && stk.peek().progress + stk.peek().speed * time >= DELIVERY_POINT) {
                stk.pop();
                count++;
            }
            answerList.add(count);
        }
        
        int[] answer = new int[answerList.size()];
        for(int i = 0; i < answerList.size(); i++)
            answer[i] = answerList.get(i);
        return answer;
    }
    
    static class Pair<T, R> {
        T progress;
        R speed;
        
        Pair(T progress, R speed) {
            this.progress = progress;
            this.speed = speed;
        }
    }
}
