package boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MainB_02580 {
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input/boj/input_02580.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int[][] map = new int[9][9];
		for(int i=0; i<9; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<9; j++) 
				map[i][j] = Integer.parseInt(st.nextToken());
		}
		boolean[][][] used = check_used(map);
		solve(map, used, -1, false);
		print_ans(map, sb);
	}
	
	static boolean solve(int[][] map, boolean[][][] used, int idx, boolean done) {
		if(done) return true;
		if(idx == 80) {
			return true;
		}
		if(map[(++idx)/9][idx%9] != 0) 
			done = solve(map, used, idx, done);
		else {
			for(int i=1; i<10; i++) {
				if(used[idx/9][idx%9][i] == false) {
					if(possible(map, idx/9, idx%9, i)) {
						map[idx/9][idx%9] = i;
						done = solve(map, used, idx, done);
						if(!done) map[idx/9][idx%9] = 0;
					}
				}
			}
		}
		return done;
	}
	
	static boolean possible(int[][] map, int i, int j, int ins) {
		boolean ret = true;
		for(int r=0; r<9; r++) 
			if(map[r][j] == ins) return false;
		
		for(int c=0; c<9; c++) 
			if(map[i][c] == ins) return false;
		int sr = (i/3) *3; int sc = (j/3)*3;
		for(int r=0; r<3; r++) 
			for(int c=0; c<3; c++) 
				if(map[sr+r][sc+c] == ins) return false;
		
		return ret;
	}
	
	static boolean[][][] check_used(int[][] map){
		boolean[][][] ret = new boolean[9][9][10];
		for(int i=0; i<9; i++) 
			for(int j=0; j<9; j++) 
				if(map[i][j] == 0)
					ret[i][j] = check_three(map, i, j);
		return ret;
	}
	
	static boolean[] check_three(int[][] map, int i, int j) {
		boolean[] ret = new boolean[10];
		for(int r=0; r<9; r++) ret[map[r][j]] = true;
		for(int c=0; c<9; c++) ret[map[i][c]] = true;
		int sr = (i/3) *3; int sc = (j/3)*3;
		for(int r=0; r<3; r++) {
			for(int c=0; c<3; c++) {
				ret[map[sr+r][sc+c]] = true;
			}
		}
		return ret;
	}
	
	static void print_ans(int[][] map, StringBuilder sb) {
		for(int i=0; i<9; i++) {
			for(int j=0; j<9; j++)
				sb.append(map[i][j]).append(" ");
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
}
