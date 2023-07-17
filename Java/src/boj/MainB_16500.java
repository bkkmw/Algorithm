package boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

public class MainB_16500 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input/boj/input_16500.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String S = br.readLine();
		int N = Integer.parseInt(br.readLine());
		String[] words = new String[N];
		
		for(int i=0; i<N; i++) {
			words[i] = br.readLine();
		}
		
		boolean ans = solve(S, words);
		System.out.println(ans? 1 : 0);
	}
	
	public static boolean solve(String S, String[] words) {
		int len = S.length();
		int N = words.length;
		boolean[] res = new boolean[len+1];
		res[len] = true;
		
		for(int i=len; i>-1; i--) {
			if(!res[i]) continue;
			for(int j=0; j<N; j++) {
				int strlen = words[j].length();
				if(i - strlen < 0) continue;
				if(S.substring(i-strlen, i).equals(words[j])) 
					res[i-strlen] = true;
			}
		}
		
		return res[0];
	}
	
}
