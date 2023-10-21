package programmers;

import java.util.*;

public class Solution_42627 {
    public int solution(int[][] jobs) {
        int answer = 0;
        int len = jobs.length, idx = 0, done = 0;
        Arrays.sort(jobs, (int[] o1, int[] o2) -> o1[0] - o2[0]);
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>((int[] o1, int[] o2) -> o1[1]-o2[1]);
        
        int c_time = 0;
        while(done < len) {
            while(idx<len && jobs[idx][0] <= c_time) {
                pq.add(new int[] {jobs[idx][0], jobs[idx][1]});
                idx++;
            }
            
            if(idx<len && pq.isEmpty()) {
                c_time = jobs[idx][0];
                continue;
            }
            
            int[] job = pq.poll();
            c_time += job[1];
            answer += (c_time-job[0]);
            done++;
        }
        
        return (int)(answer/len);
    }
}