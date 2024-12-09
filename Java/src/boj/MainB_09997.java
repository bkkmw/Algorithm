package boj;

import java.util.*;
import java.io.*;

public class MainB_09997 {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream(new File("input/boj/input_09997.txt")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		String[] words = new String[N];
		boolean[][] chars = new boolean[N][26];
		
		for(int i=0; i<N; i++) {
			String word = br.readLine();
			words[i] = word;
			
			chars[i] = count_chars(word);
		}
		
		int ans = solve(words, chars);
		System.out.println(ans);
		
		br.close();
	}
	
	public static int solve(String[] words, boolean[][] chars) {
		int ret = 0, N = words.length;
		
		ret = count(words, chars, 0, new boolean[26]);
		
		return ret;
	}
	
	public static int count(String[] words, boolean[][] chars, int idx, boolean[] used) {
		// check
		if(check_done(used))
			return (int)Math.pow(2, words.length-idx);
		if(idx == words.length) 
			return 0;
		
		int cnt = 0;
		cnt += count(words, chars, idx+1, used);
		
		boolean[] temp = used;
		used = update_used(used, chars[idx]);
		cnt += count(words, chars, idx+1, used);
		used = temp;
		
		return cnt;
	}
	
	public static boolean check_done(boolean[] used) {
		boolean ret = true;
		for(int i=0; i<26; i++) {
			if(!used[i]) return false;
		}
		
		return ret;
	}
	
	public static boolean[] update_used(boolean[] used, boolean[] chars) {
		boolean[] ret = new boolean[26];
		for(int i=0; i<26; i++) {
			ret[i] = used[i] | chars[i];
		}
		
		return ret;
	}
	
	public static boolean[] count_chars(String word) {
		boolean[] ret = new boolean[26];
		for(int i=0; i<word.length(); i++) {
			char alpha = word.charAt(i);
			
			ret[alpha-97] = true;
		}
		
		return ret;
	}
}
