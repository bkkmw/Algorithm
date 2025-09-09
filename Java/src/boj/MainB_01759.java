package boj;

import java.util.*;
import java.io.*;

public class MainB_01759 {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream(new File("input/boj/input_01759.txt")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int L = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		
		char[] chars = new char[C];
		st = new StringTokenizer(br.readLine(), " ");
		for(int i=0; i<C; i++) {
			chars[i] = st.nextToken().charAt(0);
		}
		
		List<String> ans = solve(L, chars);
		
		print_ans(ans);
	}
	
	static List<String> solve(int L, char[] chars) {
		List<String> ret = new LinkedList<String>();
		int C = chars.length;
		
		Arrays.sort(chars);
		boolean[] used = new boolean[C];
		
		recur(L, chars, used, 0, 0, ret);
		
		return ret;
	}
	
	static void recur(int L, char[] chars, boolean[] used, int idx, int cnt, List<String> ret) {
		if(cnt == L) {
			if(validate( chars, used)) {
				ret.add(arr_to_string(L, chars, used));
			}
			return;
		}
		
		if(idx >= chars.length) return;
		
		used[idx] = true;
		recur(L, chars, used, idx+1, cnt+1, ret);
		
		used[idx] = false;
		recur(L, chars, used, idx+1, cnt, ret);
	}
	
	static boolean validate(char[] chars, boolean[] used) {
		int C = chars.length;
		int conso_cnt = 0, vowel_cnt = 0;
		
		for(int i=0; i<C; i++) {
			if(!used[i]) continue;
			
			if(check_vowel(chars[i])) {
				vowel_cnt ++;
			} else {
				conso_cnt ++;
			}
				
		}
		
		return vowel_cnt >= 1 && conso_cnt >= 2;
	}
	
	static boolean check_vowel(char c) {
		if(c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u')
			return true;
		
		return false;
	}
	
	static String arr_to_string(int len, char[] chars, boolean[] used) {
		char[] ret = new char[len];
		int idx = 0, C = chars.length;
		
		for(int i=0; i<C; i++) {
			if(used[i]) {
				ret[idx++] = chars[i];
			}
		}
		
		return new String(ret);
	}
	
	static void print_ans(List<String> ans) {
		StringBuilder sb = new StringBuilder();
		
		Iterator<String> it = ans.iterator();
		while(it.hasNext()) {
			sb.append(it.next()).append("\n");
		}
		
		System.out.println(sb.toString());
	}
}
