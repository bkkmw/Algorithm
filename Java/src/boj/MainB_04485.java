package boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class MainB_04485 {
	static int[][] dir = new int[][] {
		{0,1}, {1,0}, {0,-1}, {-1,0}
	};
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input/boj/input_04485.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		int tc = 1;
		int[][] map;
		while(N != 0) {
			map = new int[N][N];
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			int ans = solve(map, N);
			sb.append("Problem ").append(tc++).append(": ").append(ans).append("\n");
			N = Integer.parseInt(br.readLine());
		}
		System.out.println(sb);
	}
	
	static int solve(int[][] map, int N) {
		int[][] dist = new int[N][N];
		fill_arr(dist, N, Integer.MAX_VALUE);
		Queue<int[]> q = new LinkedList<int[]>();
		
		q.add(new int[] {0,0});
		dist[0][0] = map[0][0];
		
		while(!q.isEmpty()) {
			int[] now = q.poll();
			int y = now[0];		int x = now[1];
			for(int d=0; d<4; d++) {
				int ny = y + dir[d][0];
				int nx = x + dir[d][1];
				if(ny<0 || ny>N-1 || nx<0 || nx>N-1) continue;
				if(dist[ny][nx] <= dist[y][x] + map[ny][nx])
					continue;
				dist[ny][nx] = dist[y][x] + map[ny][nx];
				q.add(new int[] {ny, nx});
			}
		}
		
		return dist[N-1][N-1];
	}

	static void fill_arr(int[][] arr, int N, int val) {
		for(int i=0; i<N; i++)
			for(int j=0; j<N; j++)
				arr[i][j] = val;
		
	}
}
