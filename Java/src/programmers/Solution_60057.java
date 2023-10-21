package programmers;

public class Solution_60057 {	
    public int solution(String s) {
        int answer = 1001;
        int len = s.length(), size = 1;
        
        while(size <= len/2) {
            int temp = find_len(s, size);
            if(temp < answer) answer = temp;
            size++;
        }
        return answer==1001? len : answer;
    }
    
    public int find_len(String s, int size) {
        int len = s.length();
        int ret = len;
        String temp = s.substring(0, size);
        int cnt = 1;
        
        for(int i=1; i*size<len; i++) {
            if((i+1)*size > len) continue;
            if(s.substring(i*size, (i+1)*size).equals(temp)) {
                ret -= size;
                cnt++;
            }
            else {
                ret += (cnt==1)? 0 : count_digit(cnt);
                cnt = 1;
                temp = s.substring(i*size, (i+1)*size);
            }
        }
        ret += (cnt==1)? 0 : count_digit(cnt);
        return ret;
    }
    
    public int count_digit(int num) {
        int ret = 0;
        while(num > 0) {
            ret ++;
            num /= 10;
        }
        return ret;
    }
}
