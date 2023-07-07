package programmers;

import java.util.*;

class Solution_176963 {
    public int[] solution(String[] name, int[] yearning, String[][] photo) {
        int[] answer;
        Map<String, Integer> scores = new HashMap<String, Integer>();
        
        int len = name.length;
        
        for(int i=0; i<len; i++) {
            scores.put(name[i], yearning[i]);
        }
        
        int ph_cnt = photo.length;
        answer = new int[ph_cnt];
        
        for(int i=0; i<ph_cnt; i++) {
            int pe_cnt = photo[i].length;
            
            for(int j=0; j<pe_cnt; j++) {
                answer[i] += scores.get(photo[i][j]) == null ? 0 : scores.get(photo[i][j]);
            }
        }
        return answer;
    }
}