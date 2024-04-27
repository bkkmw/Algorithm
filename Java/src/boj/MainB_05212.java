package boj;

import java.io.*;
import java.util.*;

public class MainB_05212 {
	
	public static int[][] dir_yx = new int[][] {
		{-1,0}, {0,+1}, {+1,0}, {0, -1}
	};

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input/boj/input_05212.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine());
		
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		char[][] map = new char[R][C];
		
		for(int i=0; i<R; i++) {
			String line = br.readLine();
			for(int j=0; j<C; j++) {
				map[i][j] = line.charAt(j);
			}
		}
		
		char[][] ans = solve(map);
		format_ans(sb, ans);
	
		System.out.println(sb.toString());
		br.close();
	}
	
	public static char[][] solve(char[][] map) {
		int R = map.length, C = map[0].length;
		int[] trbl = new int[] {R-1, 0, 0, C-1};
		
		char[][] ret = new char[R][C];
		
		copy_map(ret, map);
		
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				if(map[i][j] == '.') continue;
				
				if(check_flood(map, i, j)) {
					ret[i][j] = '.';
				} else {
					update_trbl(trbl, i, j);
				}
			}
		}
		
		return bound_map(ret, trbl);
	}
	
	public static void update_trbl(int[] trbl, int i, int j) {
		if(i < trbl[0]) trbl[0] = i;
		if(j > trbl[1]) trbl[1] = j;
		if(i > trbl[2]) trbl[2] = i;
		if(j < trbl[3]) trbl[3] = j;		
	}
	
	public static boolean check_flood(char[][] map, int i, int j) {
		int R = map.length, C = map[0].length;
		int cnt = 0;
		
		for(int d=0; d<4; d++) {
			int ni = i+dir_yx[d][0], nj = j+dir_yx[d][1];
			
			if(ni<0 || ni>R-1 || nj<0 || nj>C-1) {
				cnt++;
				continue;
			}
			
			if(map[ni][nj] == '.') cnt++;
		}
		
		return cnt >= 3;
	}
	
	public static char[][] bound_map(char[][] map, int[] trbl) {
		int width = trbl[1]-trbl[3]+1, height = trbl[2]-trbl[0]+1;
		char[][] ret = new char[height][width];
		
		for(int i=0; i<height; i++) {
			for(int j=0; j<width; j++) {
				ret[i][j] = map[i+trbl[0]][j+trbl[3]];
			}
		}
		
		return ret;
	}
		
	public static void format_ans(StringBuilder sb, char[][] ans) {
		int R = ans.length, C = ans[0].length;
		
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				sb.append(ans[i][j]);
			}
			sb.append("\n");
		}
	}
	
	public static void copy_map(char[][] dst, char[][] src) {
		int R = src.length, C = src[0].length;
		for(int i=0; i<R; i++)
			for(int j=0; j<C; j++)
				dst[i][j] = src[i][j];
	}
}
