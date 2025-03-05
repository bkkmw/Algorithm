package boj;

import java.io.*;
import java.util.*;

public class MainB_01522 {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream(new File("input/boj/input_01522.txt")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String str = br.readLine();
		
		int ans = solve(str);
		
		System.out.println(ans);
		
		br.close();
	}
	
	public static int solve(String string) {
		char[] str = string.toCharArray();
		int len = str.length;
		int a_cnt = 0;
		
		for(int i=0; i<len; i++) {
			if(str[i] == 'a')
				a_cnt++;
		}
		int ret = a_cnt;
				
		for(int i=0; i<len; i++) {
			if(str[i] == 'b') continue;
			
			int cnt = get_count(str, i, a_cnt);
			if(cnt < ret) ret = cnt;
		}
		
		return ret;
	}
	
	public static int get_count(char[] str, int src, int a_cnt) {
		int ret = 0;
		int serial_cnt = 1;
		int len = str.length;
 
		// start from 'a'
		for(int i=1; i<len; i++) {
			if(serial_cnt == a_cnt)
				return ret;
			
			int idx = (src+i) % len;
			
			if(str[idx] == 'b') {
				ret++;
			}
			
			serial_cnt++;
		}
		
		return ret;
	}
}
