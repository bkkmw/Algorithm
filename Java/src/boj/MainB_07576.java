package boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class MainB_07576 {
	static int[][] dir_yx = new int[][] {{-1,0}, {0,1}, {1,0}, {0,-1}};
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input/boj/input_07576.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(), " ");
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		
		int[][] box = new int[N][M];
		int[][] dist = new int[N][M];
		Queue<int[]> q = new LinkedList<int[]>();
		
		fill_arr(dist, -1);
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<M; j++) {
				box[i][j] = Integer.parseInt(st.nextToken());
				if(box[i][j] == 1) {
					q.add(new int[] {i,j});
					dist[i][j] = 0;
				}
			}
		}
		
		int ans = solve(box, dist, q, N, M);
		sb.append(ans);
		System.out.println(sb);
	}
	
	static int solve(int[][] box, int[][] dist, Queue<int[]> q, int N, int M) {
		int ret = 0;
		spread(box, dist, q, N, M);
		ret = calc(box, dist, N, M);
		return ret;
	}
	
	static int calc(int[][] box, int[][] dist, int N, int M) {
		int ret = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(box[i][j] == 0) {
					if(dist[i][j] == -1) return -1;
					ret = (ret > dist[i][j])? ret : dist[i][j];
				}
			}
		}	
		return ret;
	}
	
	static void spread(int[][] box, int[][] dist, Queue<int[]> q, int N, int M) {
				
		while(!q.isEmpty()) {
			int[] now = q.poll();
			int y = now[0];		int x = now[1];
			for(int d=0; d<4; d++) {
				int ny = y + dir_yx[d][0];
				int nx = x + dir_yx[d][1];
				
				if(ny<0 || ny>=N || nx<0 || nx>=M) continue;
				if(box[ny][nx] == 1 || box[ny][nx] == -1) continue;
				if(dist[ny][nx] != -1) continue;
				
				dist[ny][nx] = dist[y][x] + 1;
				q.add(new int[] {ny, nx});
			}
		}
	}
	
	static void fill_arr(int[][] arr, int val) {
		for(int i=0; i<arr.length; i++) {
			for(int j=0; j<arr[0].length; j++)
				arr[i][j] = val;
		}
	}
}
