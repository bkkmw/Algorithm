package boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class MainB_17836 {

	public static int[][] dir_yx = new int[][] {{-1,0}, {0,+1}, {+1,0}, {0,-1}};
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input/boj/input_17836.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[N][M];
		int[] gram_pos = new int[2];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<M; j++) {
				int temp = Integer.parseInt(st.nextToken());
				map[i][j] = temp;
				if(temp == 2) gram_pos = new int[] {i, j}; 
			}
		}
		
		int ans = solve(N, M, T, map, gram_pos);
		System.out.println(ans == -1 ? "Fail" : ans);
	}
	
	public static int solve(int N, int M, int T, int[][] map, int[] gram_pos) {
		int ret = -1;
		boolean[][] visited = new boolean[N][M];
		Queue<int[]> q = new LinkedList<>();
		int[] arrival_t = new int[2];
		int l1_dist = (N-1-gram_pos[0]) + (M-1-gram_pos[1]);
		
		visited[0][0] = true;
		q.add(new int[] {0, 0, 0});
		
		while(!q.isEmpty()) {
			int cur[] = q.poll();
			
			if(cur[2]+1 > T || check_time(arrival_t, cur[2]+1, l1_dist)) break;
			
			for(int d=0; d<4; d++) {
				int ny = cur[0] + dir_yx[d][0];
				int nx = cur[1] + dir_yx[d][1];
				
				if(ny<0 || ny>N-1 || nx<0 || nx>M-1) continue;
				if(visited[ny][nx]) continue;
				if(map[ny][nx] == 1) continue;
				
				visited[ny][nx] = true;

				if(map[ny][nx] == 2) {
					arrival_t[0] = (cur[2] + 1 + l1_dist) > T? 0 : cur[2]+1+l1_dist;
					continue;
				}
				
				if(ny == N-1 && nx == M-1) {
					arrival_t[1] = cur[2] + 1;
					continue;
				}
				
				q.add(new int[] {ny, nx, cur[2] + 1});
			}
		}
		
		return calc_ret(arrival_t);
	}
	
	public static boolean check_time(int[] arrivals, int current, int l1_dist) {
		boolean acquired = arrivals[0] > 0;
		boolean arrived = arrivals[1] > 0;
		
//		if((!acquired) && (!arrived)) return false;
		if(acquired && arrived) return true;
		
		if(acquired && (current >= arrivals[0])) return true;
		if(arrived && (current >= arrivals[1] - l1_dist)) return true;
		
		return false;
	}
	
	public static int calc_ret(int[] arrivals) {
		if(arrivals[0] < 1 && arrivals[1] < 1) return -1;
		if(arrivals[0] < 1) return arrivals[1];
		if(arrivals[1] < 1) return arrivals[0];
		return (arrivals[0] > arrivals[1] ? arrivals[1] : arrivals[0]);
	}
}
