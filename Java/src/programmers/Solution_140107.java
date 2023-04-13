package programmers;

public class Solution_140107 {
	public long solution(int k, int d) {
        long answer = 0;
        
        int limit = (int)(d / k);
        
        double cons = ((long)d * (long)d) / ((long)k * (long)k);
        
        for(long i=0; i<=limit; i++) {
            answer += (int)(Math.sqrt(cons - i*i));
        }
        answer += (limit + 1);
        
        return answer;
    }
}
