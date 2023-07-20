package swea;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_1249 {
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input/swea/input_1249.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int TC = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=TC; tc++) {
			int N = Integer.parseInt(br.readLine());
			int[][] map = new int[N][N];
			
			for(int i=0; i<N; i++) {
				String line = br.readLine();
				for(int j=0; j<N; j++) {
					map[i][j] = (int)line.charAt(j) - 48;
				}
			}
			
			int ans = solve(map);
			sb.append("#").append(tc).append(" ");
			sb.append(ans).append("\n");
		}
		System.out.println(sb.toString());
	}
	
	static int[][] dir_yx = new int[][] {
		{-1,0}, {0,+1}, {+1,0}, {0,-1}
	};
	
	static int solve(int[][] map) {
		int N = map.length;
		int[][] check = new int[N][N];
		boolean[][] visited = new boolean[N][N];
		Queue<int[]> q = new LinkedList<int[]>();
		
//		init_map(map, 0);
		q.add(new int[] {0, 0, 0});
		while(!q.isEmpty()) {
			int[] temp = q.poll();
			for(int d=0; d<4; d++) {
				int ny = temp[0] + dir_yx[d][0];
				int nx = temp[1] + dir_yx[d][1];
				if(ny<0 || ny>=N || nx<0 || nx>=N) continue;
				if((check[ny][nx] <= temp[2] + map[ny][nx]) 
						&& (visited[ny][nx] == true)) continue;
				check[ny][nx] = temp[2] + map[ny][nx];
				visited[ny][nx] = true;
				q.add(new int[] {ny, nx, temp[2] + map[ny][nx]});
			}
		}
		return check[N-1][N-1];
	}
	
	static void init_map(int[][] map, int val) {
		for(int i=0; i<map.length; i++) {
			for(int j=0; j<map.length; j++) {
				map[i][j] = val;
			}
		}
	}
}
