package boj;

import java.io.*;
import java.util.*;

public class MainB_01157 {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input/boj/input_01157.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String str = br.readLine();
		
		char ans = solve(str);
		
		System.out.println(ans);
//		System.out.println('A'-0); // 65
//		System.out.println('a'-0); // 97
		
		br.close();
	}
	
	public static char solve(String str) {
		int[] counts = new int[26];
		
		char[] chars = str.toCharArray();
		int len = chars.length;
		
		for(int i=0; i<len; i++) {
			char cur = chars[i];
			
			int idx = (cur > 96) ? cur-97 : cur-65;
			counts[idx] ++;
		}
		
		return find_max(counts);
	}
	
	public static char find_max(int[] counts) {
		int len = counts.length;
		int max_cnt = 0;
		int max_idx = 0;
		boolean multiple_ans = false;
		
		for(int i=0; i<len; i++) {
			if(counts[i] > max_cnt) {
				max_cnt = counts[i];
				max_idx = i;
				multiple_ans = false;
			} else if(counts[i] == max_cnt) {
				multiple_ans = true;
			}
		}
		
		return multiple_ans ? '?' : (char)(max_idx + 65);
	}
}
