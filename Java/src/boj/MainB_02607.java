package boj;

import java.io.*;
import java.util.*;

public class MainB_02607 {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input/boj/input_02607.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		String[] words = new String[N];
		
		for(int i=0; i<N; i++) {
			words[i] = br.readLine();
		}
		
		int ans = solve(words);
		
		System.out.println(ans);
		br.close();
	}
	
	public static int solve(String[] words) {
		int N = words.length;
		int ret = 0;
		
		int[] char_counts = count_chars(words[0]);
		
		for(int i=1; i<N; i++) {
			ret += verify_word(char_counts, words[i]) ? 1 : 0;
		}
		
		return ret;
	}
	
	public static boolean verify_word(int[] src_counts, String dst_word) {
		int[] dst_counts = count_chars(dst_word);
		int added = 0, removed = 0;
		
		for(int i=0; i<26; i++) {
			if(src_counts[i] == dst_counts[i]) 
				continue;
			
			int diff = dst_counts[i] - src_counts[i];
			if(diff > 0) {
				added += diff;
			} else {
				removed -= diff;
			}
		}
		
		return (added < 2) && (removed < 2);
	}
	
	public static int[] count_chars(String word) {
		int[] ret = new int[26];
		int len = word.length();
		
		for(int i=0; i<len; i++) {
			ret[word.charAt(i)-65] ++;
		}
		
		return ret;
	}
}
