package boj;

import java.io.*;
import java.util.*;

public class MainB_20437 {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input/boj/input_20437.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for(int t=1; t<=T; t++) {
			String W = br.readLine();
			int K = Integer.parseInt(br.readLine());
			
			int[] ans = solve(W, K);
			if(ans != null)
				sb.append(ans[0]).append(" ").append(ans[1]).append(" ").append("\n");
			else
				sb.append(-1).append("\n");
		}
		
		System.out.println(sb.toString());
		br.close();
	}
	
	public static int[] solve(String W, int K) {
		int[] ret = new int[] { 10001, 0 };
		int len = W.length();
		
		List<Integer>[] indexes = find_indexes(W);
		
		for(int i=0; i<26; i++) {
			if(indexes[i].size() < K) continue;
			
			int[] sub_len = find_min_max_length(indexes[i], len, K);
			int min = sub_len[0], max  = sub_len[1];
			
			if(min < ret[0]) ret[0] = min;
			if(max > ret[1]) ret[1] = max;
		}
		
		return ret[0] == 10001? null : ret;
	}
	
	public static List<Integer>[] find_indexes(String W) {
		List<Integer>[] ret = new ArrayList[26];
		for(int i=0; i<26; i++) {
			ret[i] = new ArrayList<Integer>();
		}
		
		int len = W.length();
		
		for(int i=0; i<len; i++) {
			char c = W.charAt(i);
			
			ret[c-97].add(i);
		}
		
		return ret;
	}
	
	public static int[] find_min_max_length(List<Integer> indexes, int len, int K) {
		int min = 10001, max = 0;
		int size = indexes.size();
		
		for(int i=0; i<size-(K-1); i++) {
			int last_idx = (i+K-1) % size;
			int sub_len = indexes.get(last_idx) - indexes.get(i)+1;
			
			if(sub_len < min) min = sub_len;
			if(sub_len > max) max = sub_len;
		}
		
		return new int[] {min, max};
	}
}
