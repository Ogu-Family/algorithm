package algorithm.src.week17.p3;

import java.util.*;

/**
 * 문제 링크: https://school.programmers.co.kr/learn/courses/30/lessons/258709
 * 시간 복잡도: O(N^2*6^(N/2))
 * 공간 복잡도: O(6^(N/2))
 */

/**
 * 1. dfs로 주사위 a와 b 나누기
 * 2. dfs로 A, B의 주사위로 나올 수 있는 합 구하며, 각 합이 나오는 횟수 계산하기
 * 3. 합에 대한 횟수를 바탕으로 이길 수 있는 횟수 구하기
 * 4. 이길 수 있는 횟수가 가장 큰 경우를 정답으로 저장
 */

public class p3_JeongeunChoi {

    int[][] diceCopy;
    int[] answerDivideDice;
    int diceCnt, maxWinCnt = 0;
    Map<Integer, Integer> aDiceSumMap, bDiceSumMap;

    // divideDice 값이 1이면 a의 주사위, 0이면 b의 주사위
    private void selectDice(int[] divideDice, int diceNum, int cnt) {
        if(cnt==diceCnt/2){
            // A의 주사위로 나올 수 있는 합
            aDiceSumMap = new HashMap<>();
            getSums(1, divideDice);

            // B의 주사위로 나올 수 있는 합
            bDiceSumMap = new HashMap<>();
            getSums(0, divideDice);

            // 합을 바탕으로 이길 수 있는 횟수 구하기
            int winCnt = 0;
            for(Integer aSum : aDiceSumMap.keySet()){
                for(Integer bSum : bDiceSumMap.keySet()){
                    if(aSum>bSum){
                        winCnt += aDiceSumMap.get(aSum) * bDiceSumMap.get(bSum);
                    }
                }
            }

            if(maxWinCnt < winCnt){
                answerDivideDice = Arrays.copyOf(divideDice, divideDice.length); // 복사하여 대입
                maxWinCnt = winCnt;
            }
        } else{
            for(int i=diceNum+1; i<=diceCnt; i++){
                divideDice[i]=1;
                selectDice(divideDice, i, cnt+1);
                divideDice[i]=0;
            }
        }
    }

    private void getSums(int diceOwner, int[] divideDice){
        int[] diceNums = new int[diceCnt/2];
        int j=0;
        for(int i=1; i<=diceCnt; i++){
            if(divideDice[i]==diceOwner){
                diceNums[j++]=i;
            }
        }
        rollDice(diceOwner, diceNums, 0, 0);
    }

    private void rollDice(int diceOwner, int[] diceNums, int i, int sum){
        if(i==diceCnt/2){
            if(diceOwner==1){
                if(aDiceSumMap.containsKey(sum)){
                    aDiceSumMap.put(sum, aDiceSumMap.get(sum)+1);
                } else{
                    aDiceSumMap.put(sum, 1);
                }
            } else if(diceOwner==0){
                if(bDiceSumMap.containsKey(sum)){
                    bDiceSumMap.put(sum, bDiceSumMap.get(sum)+1);
                } else{
                    bDiceSumMap.put(sum, 1);
                }
            }
        } else{
            for(int j=0; j<diceCopy[diceNums[i]-1].length; j++){
                rollDice(diceOwner, diceNums, i+1, sum+diceCopy[diceNums[i]-1][j]);
            }
        }
    }

    public int[] solution(int[][] dice) {
        int[] answer = new int[dice.length/2];
        diceCopy = dice;
        diceCnt = dice.length;

        selectDice(new int[dice.length+1], 0, 0);
        int j=0;
        for(int i=1; i<=diceCnt; i++){
            if(answerDivideDice[i]==1){
                answer[j++]=i;
            }
        }

        return answer;
    }
}