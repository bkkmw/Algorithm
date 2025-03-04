package boj;

import java.io.*;
import java.util.*;

public class MainB_02642 {
	
	static int[][] dir_yx = new int[][] {
		{-1,0}, {0,+1}, {+1,0}, {0,-1}
	};

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream(new File("input/boj/input_02642.txt")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = 6;
		int[][] map = new int[N][N];
		
		StringTokenizer st;
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int ans = solve(map);
		
		System.out.println(ans);
		
		br.close();
	}
	
	public static int solve(int[][] map) {
		int N = map.length;
		int cnt = 0;
		int[] pairs = new int[7];
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(map[i][j] == 0) continue;
				if(pairs[map[i][j]] > 0) continue;
				
				int pair = find_pair(map, i, j);
				if(pair < 1) return 0;
				
				pairs[map[i][j]] = pair;
				pairs[pair] = map[i][j];
				cnt++;
			}
		}
		
		return cnt==3 ? pairs[1] : 0;
	}
	
	public static int find_pair(int[][] map, int i, int j) {
		int ret = 0;
		int N = map.length;
		Queue<int[]> q = new LinkedList<int[]>();
		
		for(int d=0; d<4; d++) {
			int ni = i + dir_yx[d][0], nj = j + dir_yx[d][1];
			if(ni < 0 || nj < 0 || ni > N-1 || nj > N-1) continue;
			
			if(map[ni][nj] > 0) {
				// y, x, first direction, last direction
				q.add(new int[] {ni, nj, d, d});
			}
		}
		
		while(!q.isEmpty()) {
			int[] poll = q.poll();
			
			for(int d=0; d<4; d++) {
				if((poll[3]+2) % 4 == d) continue;
				int ni = poll[0] + dir_yx[d][0], nj = poll[1] + dir_yx[d][1];
				if(ni < 0 || nj < 0 || ni > N-1 || nj > N-1 || map[ni][nj] == 0) continue;
				
				if(d == poll[2]) return map[ni][nj];
				
				q.add(new int[] {ni, nj, poll[2], d});
			}
		}
		
		return ret;
	}
}
