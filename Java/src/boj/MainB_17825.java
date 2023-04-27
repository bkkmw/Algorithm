package boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MainB_17825 {

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input/boj/input_17825.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(), " ");
		int[] dice = new int[10];
		for(int i=0; i<10; i++) dice[i] = Integer.parseInt(st.nextToken());
		
		int ans = solve(dice);
		System.out.println(ans);
	}
	
	static int solve(int[] dice) {
		int ret = 0;
		int[][] horse = new int[4][2];
		int[] map = init_map();
		ret = recur(map, dice, horse, 0, 0, ret);
		
		return ret;
	}
	
	static int recur(int[] map, int[] dice, int[][] horse, int idx, int cnt, int max) {
		if(idx == 10) return (cnt > max) ? cnt : max;
		if(cnt + 40 * (10-idx) <= max) return max;
		
		for(int i=0; i<4; i++) {
			if(horse[i][1] == 1) continue;
			int next = get_next(map, dice[idx], horse[i][0]);
			if(check_duplication(next, horse)) continue;
			
			int prev_pos = horse[i][0];
			int prev_status = horse[i][1];
			horse[i][0] = next;
			horse[i][1] = (map[next] == -1) ? 1 : 0;
			
			max = recur(map, dice, horse, idx+1, cnt + ((map[next] == -1)? 0 : map[next]), max);
			
			horse[i][1] = prev_status;
			horse[i][0] = prev_pos;
		}
		
		return max;
	}
	 
	static boolean check_duplication(int next, int[][] horse) {
		boolean ret = false;
		for(int i=0; i<4; i++)
			if(next == horse[i][0] && horse[i][1] != 1) return true;
		return ret;
	}
	
	static int get_next(int[] map, int dice, int cur_pos) {
		int ret = cur_pos;
		if(ret == 5) ret = 30 + dice - 1;
		else if(ret == 10) ret = 40 + dice - 1;
		else if(ret == 15) ret = 50 + dice - 1;
		else ret += dice;
		int val = map[ret];
		while(val > 100) {
			int ptr = val / 10;
			int offset = val % 10;
			ret = ptr + offset;
			val = map[ret];
		}
		return ret;
	}
	
	static int[] init_map() {
		int[] ret = new int[58];
		for(int i=1; i<21; i++) ret[i] = 2*i;
		
		for(int i=21; i<26; i++) ret[i] = -1;
		
		ret[30] = 13; ret[31] = 16; ret[32] = 19;
		for(int i=33; i<38; i++) ret[i] = 420 + (i-33);
		
		ret[40] = 22; ret[41] = 24;
		ret[42] = 25; ret[43] = 30; ret[44] = 35;
		for(int i=45; i<50; i++) ret[i] = 200 + (i-45);
		
		ret[50] = 28; ret[51] = 27; ret[52] = 26;
		for(int i=53; i<58; i++) ret[i] = 420 + (i-53);
		
		return ret;
	}
}
