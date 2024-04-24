package boj;

import java.util.*;
import java.io.*;

public class MainB_12919 {
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input/boj/input_12919.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String S = br.readLine();
		String T = br.readLine();
		
		int ans = solve(S, T);
		
		System.out.println(ans);
		br.close();
	}
	
	public static int solve(String S, String T) {
		boolean ret = false;
		int acnt = 0, bcnt = 0;
		int slen = S.length(), tlen = T.length();
		
		for(int i=0; i<tlen; i++) {
			if(T.charAt(i) == 'A') 
				acnt ++;
			else
				bcnt++;
		}
		
		for(int i=0; i<slen; i++) {
			if(S.charAt(i) == 'A')
				acnt--;
			else
				bcnt--;
		}
				
		
		ret = recur(S, T, acnt, bcnt);
		
		return ret? 1 : 0;
	}
	
	public static boolean recur(String S, String T, int acnt, int bcnt) {
		if(acnt + bcnt == 0) {
			return T.equals(S);
		}
		
		if(!(T.contains(S) || T.contains(new StringBuilder(S).reverse().toString()))) {
			return false;
		}
		
		if(acnt > 0) {
			StringBuilder sb = new StringBuilder(S);
			boolean ret = recur(sb.append("A").toString(), T, acnt-1, bcnt);
			if(ret) return ret;
		}
		if(bcnt > 0) {
			StringBuilder sb = new StringBuilder(S);
			boolean ret = recur(sb.append("B").reverse().toString(), T, acnt, bcnt-1);
			if(ret) return ret;
		}
		
		return false;
	}
}
