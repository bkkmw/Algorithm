package boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class MainB_03055 {
	static int[][] dir_yx = new int[][] {
		{-1,0}, {0,1}, {1,0}, {0,-1}
	};
		
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input/boj/input_03055.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(), " ");
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		
		char[][] map = new char[R][C];
		int sr=0, sc=0;
		
		for(int i=0; i<R; i++) {
			String line = br.readLine();
			for(int j=0; j<C; j++) {
				map[i][j] = line.charAt(j);
				if(line.charAt(j) == 'S') {
					sr = i; sc = j;
				}
			}
		}
		
		int ans = solve(map, sr, sc);
		sb.append((ans == -1)? "KAKTUS" : ans);
		System.out.println(sb);
		br.close();
	}
	
	static int solve(char[][] map, int sr, int sc) {
		int ret = -1;
		int[][] check = new int[map.length][map[0].length];
		
		init_arr(check, Integer.MAX_VALUE);
				
		for(int i=0; i<map.length; i++) {
			for(int j=0; j<map[0].length; j++) {
				if(map[i][j] == '*') 
					mark(map, check, i, j);
			}
		}
		
		ret = calc(map, check, sr, sc);
		
		return ret;
	}
	
	static int calc(char[][] map, int[][] check, int i, int j) {
		int ret = -1;
		int[][] dist = new int[map.length][map[0].length];
		
		Queue<int[]> q = new LinkedList<int[]>();
		q.add(new int[] {i,j});
		dist[i][j] = 0;
		
		while(!q.isEmpty()) {
			int[] now = q.poll();
			for(int d=0; d<4; d++) {
				int ny = now[0] + dir_yx[d][0];
				int nx = now[1] + dir_yx[d][1];
				
				if(ny<0 || ny>=map.length || nx<0 || nx>=map[0].length) continue;
				if(dist[ny][nx] != 0) continue;
				if(map[ny][nx] == 'D') {
					return dist[now[0]][now[1]] + 1;
					
				}
				if(map[ny][nx] == '*' || map[ny][nx] == 'X' || map[ny][nx] == 'S') continue;
				if(dist[now[0]][now[1]] + 1 >= check[ny][nx]) continue;
				
				dist[ny][nx] = dist[now[0]][now[1]] + 1;
				q.add(new int[] {ny, nx});
			}
		}
		
		return ret;
	}
	
	static void mark(char[][] map, int[][] check, int i, int j) {
		Queue<int[]> q = new LinkedList<int[]>();
		check[i][j] = 0;
		q.add(new int[] {i, j});
		while(!q.isEmpty()) {
			int[] now = q.poll();
			for(int d=0; d<4; d++) {
				int ny = now[0] + dir_yx[d][0];
				int nx = now[1] + dir_yx[d][1];
				
				if(ny<0 || ny>=map.length || nx<0 || nx>=map[0].length) continue;
				if(map[ny][nx] == '*' || map[ny][nx] == 'D' || map[ny][nx] == 'X') continue;
				
				if(check[ny][nx] <= check[now[0]][now[1]] + 1) continue;
				
				check[ny][nx] = check[now[0]][now[1]] + 1;
				q.add(new int[] {ny, nx});				
			}
		}
	}
	
	static void init_arr(int[][] arr, int val) {
		for(int i=0; i<arr.length; i++) {
			for(int j=0; j<arr[0].length; j++)
				arr[i][j] = val;
		}
	}
}
