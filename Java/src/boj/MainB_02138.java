package boj;

import java.util.*;
import java.io.*;

public class MainB_02138 {
	
	public static int INF = 100001;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input/boj/input_02138.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		boolean[][] bulb = new boolean[2][N];
		
		for(int i=0; i<2; i++) {
			String line = br.readLine();
			for(int j=0; j<N; j++) {
				bulb[i][j] = line.charAt(j)=='1'? true : false;
			}
		}
		
		int ans = solve(bulb[0], bulb[1]);
		System.out.println(ans==INF? -1 : ans);
		br.close();
	}
	
	public static int solve(boolean[] target, boolean[] bulb_a) {
		int cnt_a = 0, cnt_b = 0;
		int N = target.length;
		
		boolean[] bulb_b = new boolean[N];
		for(int i=0; i<N; i++)
			bulb_b[i] = bulb_a[i];
		
		toggle(bulb_b, 0);
		cnt_b ++;
		
		for(int i=1; i<N-1; i++) {
			if(bulb_a[i-1] != target[i-1]) {
				toggle(bulb_a, i);
				cnt_a++;
			}
			if(bulb_b[i-1] != target[i-1]) {
				toggle(bulb_b, i);
				cnt_b++;
			}
		}
		
		cnt_a = check_bulb(target, bulb_a, cnt_a);
		cnt_b = check_bulb(target, bulb_b, cnt_b);
		
		return cnt_a<cnt_b? cnt_a : cnt_b;
	}
	
	public static int check_bulb(boolean[] target, boolean[] bulb, int cnt) {
		int N = target.length;
		if(target[N-2] == bulb[N-2] && target[N-1] == bulb[N-1])
			return cnt;
		if(target[N-2] != bulb[N-2] && target[N-1] != bulb[N-1])
			return cnt+1;
		
		return INF;
	}
	
	public static void toggle(boolean[] bulb, int idx) {
		int N = bulb.length;
		for(int i=-1; i<2; i++) {
			if(idx+i<0 || idx+i>N-1) continue;
			bulb[idx+i] ^= true;
		}
	}
}
