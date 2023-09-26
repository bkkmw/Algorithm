package boj;

import java.util.*;
import java.io.*;

public class MainB_16724 {
	
	public static Map<Character, Integer> char2int = new HashMap<>();
	static {
		char2int.put('U', 0);
		char2int.put('R', 1);
		char2int.put('D', 2);
		char2int.put('L', 3);
	}
	
	public static int[][] dir_yx = new int[][] {
		{-1,0}, {0,+1}, {+1,0}, {0,-1}
	};

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input/boj/input_16724.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[N][M];
		
		for(int i=0; i<N; i++) {
			String line = br.readLine();
			for(int j=0; j<M; j++) {
				map[i][j] = char2int.get(line.charAt(j));
			}
		}
		int ans = solve(map);
		System.out.println(ans);
	}
	
	public static int solve(int[][] map) {
		int ret = 0;
		int N = map.length, M = map[0].length;
		int idx = 1;
		int[][] check = new int[N][M];
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(check[i][j] > 0) continue;
				check[i][j] = idx;
				int res = traverse(map, check, i, j, idx);
				check[i][j] = res;
				if(res == idx) {
					ret ++;
					idx ++;
				}
			}
		}
		
		
		return ret;
	}
	
	public static int traverse(int[][] map, int[][] check, int i, int j, int idx) {
		int ni = i+dir_yx[map[i][j]][0];
		int nj = j+dir_yx[map[i][j]][1];
		
		if(check[ni][nj] == 0) {
			check[ni][nj] = idx;
			int res = traverse(map, check, ni, nj, idx);
			check[ni][nj] = res;
			return res;
		}
		else if(check[ni][nj] == idx) {
			return idx;
		}
		else {
			return check[ni][nj];
		}		
	}
}
