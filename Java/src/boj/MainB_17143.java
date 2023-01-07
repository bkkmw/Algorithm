package boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MainB_17143 {
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input/boj/input_17143.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[R][C];
		int[][] shark = new int[M+1][6];
		
		for(int i=1; i<=M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			shark[i][0] = Integer.parseInt(st.nextToken()) - 1;
			shark[i][1] = Integer.parseInt(st.nextToken()) - 1;
			shark[i][2] = Integer.parseInt(st.nextToken());
			shark[i][3] = Integer.parseInt(st.nextToken());
			shark[i][4] = Integer.parseInt(st.nextToken());
			shark[i][5] = 1;
			map[shark[i][0]][shark[i][1]] = i;
		}
		
		int ans = solve(map, shark);
		System.out.println(ans);
	}
	
	static int solve(int[][] map, int[][] shark) {
		int ret = 0;
		int R = map.length; int C = map[0].length;
		for(int p=0; p<C; p++) {
			ret += get_shark(map, shark, p);
			map = move(map, shark);
		}
		return ret;
	}
	
	static int get_shark(int[][] map, int[][] shark, int col) {
		int ret = 0;
		for(int i=0; i<map.length; i++) {
			if(map[i][col] != 0) {
				shark[map[i][col]][5] = 0;
				return shark[map[i][col]][4];
			}
		}
		return ret;
	}
	
	static int[][] move(int[][] map, int[][] shark) {
		int R = map.length; int C = map[0].length;
		for(int s=1; s<shark.length; s++) {
			if(shark[s][5] == 0) continue;
			int[] next = next_pos(R, C, shark[s]);
			shark[s][0] = next[0];
			shark[s][1] = next[1];
			shark[s][3] = next[2];
		}
		map = kill(shark, R, C);
		return map;
	}
	
	static int[][] kill(int[][] shark, int R, int C) {
		int[][] ret = new int[R][C];
		for(int s=1; s<shark.length; s++) {
			if(shark[s][5] == 0) continue;
			int r = shark[s][0]; int c = shark[s][1];
			int status = ret[r][c];
			if(shark[status][4] < shark[s][4]) {
				ret[r][c] = s;
				shark[status][5] = 0;
			}
			else shark[s][5] = 0;
		}
		return ret;
	}
	
	static int[] next_pos(int R, int C, int[] shark) {		
		int L, dir_sign;
		int dir = shark[3];
		if(dir < 3) {
			L = (R-1);
			dir_sign = (shark[3] == 1)? -1 : 1;			
			int next = dir_sign * (shark[2]%(2*L)) + shark[0];
			while(next < 0 || next >= R) {
				if(next < 0) {
					next = Math.abs(next); dir = 2;
				}
				else {
					next = 2*L - next; dir = 1;
				}
			}
			return new int[] {next, shark[1], dir};
		} else {
			L = (C-1);
			dir_sign = (shark[3] == 4)? -1 : 1;			
			int next = dir_sign * (shark[2]%(2*L)) + shark[1];
			while(next < 0 || next >= C) {
				if(next < 0) {
					next = Math.abs(next); dir = 3;
				}
				else {
					next = 2*L - next; dir = 4;
				}
			}
			return new int[] {shark[0], next, dir};
		}
	}
}
