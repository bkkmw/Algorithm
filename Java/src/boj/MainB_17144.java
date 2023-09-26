package boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MainB_17144 {
	static int[][] dir = new int[][] {
		{-1,0}, {0,1}, {1,0}, {0,-1}
	};
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input/boj/input_17144.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(), " ");
		
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken());
		int[][] map = new int[R][C];
		int top = -1, bot = - 1;
		
		for(int i=0; i<R; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == -1) {
					if(top == -1) top = i;
					else bot = i;
				}
			}
		}
		
		int ans = solve(map, T, top, bot);
		
		System.out.println(ans);
		
	}
	
	static int solve(int[][] map, int T, int top, int bot) {
		for(int i=0; i<T; i++) {
			map = spread(map);
			map = clear(map, top, bot);
		}
		return calc(map);
	}
	
	static int[][] spread(int[][] map) {
		int R = map.length;
		int C = map[0].length;
		int[][] ret = new int[R][C];
		
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				if(map[i][j] > 0) {
					ret[i][j] += map[i][j];
					for(int d=0; d<4; d++) {
						int ni = i + dir[d][0];
						int nj = j + dir[d][1];
						if(check(map, ni, nj)) {
							ret[ni][nj] += map[i][j]/5;
							ret[i][j] -= map[i][j]/5;
						}
					}
				}
				else if(map[i][j] == -1) ret[i][j] = map[i][j];
			}
		}
		return ret;
	}
	
	static boolean check(int[][] map, int i, int j) {
		if(i<0 || i>map.length-1 || j<0 || j>map[0].length-1) return false;
		if(map[i][j] == -1) return false;
		return true;
	}
	
	static int[][] clear(int[][] map, int top, int bot){
		int R = map.length;
		int C = map[0].length;
		int[][] ret = new int[R][C];
		
		ret[top][0] = -1;
		ret[top][1] = 0;
		
		for(int x=2; x<C; x++) ret[top][x] = map[top][x-1];
		for(int y=top-1; y>=0; y--) ret[y][C-1] = map[y+1][C-1];
		for(int x=C-2; x>=0; x--) ret[0][x] = map[0][x+1];
		for(int y=1; y<top; y++) ret[y][0] = map[y-1][0];
		
		ret[bot][0] = -1;
		ret[bot][1] = 0;
		
		for(int x=2; x<C; x++) ret[bot][x] = map[bot][x-1];
		for(int y=bot+1; y<R; y++) ret[y][C-1] = map[y-1][C-1];
		for(int x=C-2; x>=0; x--) ret[R-1][x] = map[R-1][x+1];
		for(int y=R-2; y>=bot+1; y--) ret[y][0] = map[y+1][0];
		
		for(int i=1; i<R-1; i++) {
			if(i == top || i == bot) continue;
			for(int j=1; j<C-1; j++) {
				ret[i][j] = map[i][j];
			}
		}
		return ret;
	}
	
	static int calc(int[][] map) {
		int ret = 0;
		for(int i=0; i<map.length; i++) {
			for(int j=0; j<map[0].length; j++) {
				ret += map[i][j];
			}
		}
		return ret+2;
	}
}
