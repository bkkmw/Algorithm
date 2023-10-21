package programmers;

import java.util.*;

public class Solution_43162 {

    public int solution(int n, int[][] computers) {
        int answer = 0;
        int N = computers.length;
        
        boolean[] connected = new boolean[N];
        
        for(int i=0; i<N; i++) {
            if(connected[i]) continue;
            
            connect(computers, i, connected);
            answer ++;
        }
        return answer;
    }
    
    public void connect(int[][] computers, int idx, boolean[] connected) {
        int N = computers.length;
        Queue<Integer> q = new LinkedList<Integer>();
        
        q.add(idx);
        connected[idx] = true;
        
        while(!q.isEmpty()) {
            int poll = q.poll();
            for(int i=0; i<N; i++) {
                if(poll == i || connected[i]) continue;
                if(computers[poll][i] == 0) continue;
                
                q.add(i);
                connected[i] = true;
                
            }
        }
    }
}