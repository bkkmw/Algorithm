package programmers;

import java.util.*;

class Solution {
    Map<String, Integer> map;
    List<Integer>[] lists;
    
    public String[] solution(String[][] tickets) {       
        map = new HashMap<String, Integer>();        
        int ap_idx = 0;
        
        Arrays.sort(tickets, (String[] o1, String[] o2) -> {
            if(o1[0].equals(o2[0]))
               return o1[1].compareTo(o2[1]);
            else return o1[0].compareTo(o2[0]);
        });
        
        int len = tickets.length;
        String[] answer = new String[len+1];
        for(int i=0; i<len; i++) {
            for(int j=0; j<2; j++) {
                if(map.get(tickets[i][j])==null)
                    map.put(tickets[i][j], ap_idx++);
            }
        }
        
        lists = new LinkedList[ap_idx];
        
        for(int i=0; i<ap_idx; i++) 
            lists[i] = new LinkedList<Integer>();
            
        for(int i=0; i<len; i++) {
            int src = map.get(tickets[i][0]);
            lists[src].add(i);
        }
        
        answer[0] = "ICN";
        find_path(tickets, map.get("ICN"), 0, len, answer);
        
        return answer;
    }
    
    public boolean find_path(String[][] tickets, int src, int cnt, int len, String[] answer) {
        if(cnt == len) {
            return true;
        }
        
        for(int i=0; i<lists[src].size(); i++) {
            int t_idx = lists[src].get(i);
            if(t_idx < 0) continue;
            
            lists[src].set(i, -1);
            answer[cnt+1] = tickets[t_idx][1];
            if(find_path(tickets, map.get(tickets[t_idx][1]), cnt+1, len, answer)) {
                return true;
            }
            lists[src].set(i, t_idx);
        }
        return false;
    }
}