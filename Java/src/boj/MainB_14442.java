package boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class MainB_14442 {
	
	static int dir_yx[][] = new int[][] {
		{-1,0}, {0,+1}, {+1,0}, {0,-1}
	};

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input/boj/input_14442.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int map[][] = new int[N][M];
		
		for(int i=0; i<N; i++) {
			String line = br.readLine();
			for(int j=0; j<M; j++)
				map[i][j] = line.charAt(j) - 48;
		}
		
		int ans = solve(map, N, M, K);
		
		System.out.println(ans);
		br.close();
	}
	
	static int solve(int[][] map, int N, int M, int K) {
        if(N==1 && M==1) return 1;
		int ret = Integer.MAX_VALUE;
		int visited[][][] = new int[N][M][K+1];
		Queue<int[]> q = new LinkedList<int[]>();
		
		fill_cube(visited, Integer.MAX_VALUE);
		fill_next(visited[0][0], 0, K+1, 1);
		// y pos, x pos, broken, dist
		q.add(new int[] {0, 0, 0, 1});

		while(!q.isEmpty()) {
			int cur[] = q.poll();
			
			if(cur[3]+1 >= ret) continue;
			
			for(int d=0; d<4; d++) {
				int ny = cur[0] + dir_yx[d][0];
				int nx = cur[1] + dir_yx[d][1];
				
				if(ny<0 || ny>N-1 || nx<0 || nx>M-1) continue;
				if(visited[ny][nx][cur[2]] <= cur[3] + 1) continue;
				
				if(ny == N-1 && nx == M-1) {
					if(visited[ny][nx][cur[2]] > cur[3] + 1) {
						visited[ny][nx][cur[2]] = cur[3] + 1;
						ret = find_min(visited[ny][nx], K+1);
					}
					continue;
				}
				
				if(map[ny][nx] == 0) {
					if(visited[ny][nx][cur[2]] <= cur[3] + 1) continue;
					else {
						fill_next(visited[ny][nx], cur[2], K+1, cur[3] + 1);
						q.add(new int[] {ny, nx, cur[2], cur[3] + 1});
					}
				}
				else {
					if(cur[2] >= K) continue;
					if(visited[ny][nx][cur[2]+1] <= cur[3] + 1) continue;
					fill_next(visited[ny][nx], cur[2]+1, K+1, cur[3] + 1);
					q.add(new int[] {ny, nx, cur[2] + 1, cur[3] + 1});
				}
			}
			
		}
		
		return (ret == Integer.MAX_VALUE) ? -1 : ret;
	}
	
	static void fill_next(int[] pos, int k, int K, int dist) {
		for(int i=k;i <K; i++) {
			if(pos[i] < dist) return;
			pos[i] = dist;
		}
	}
	
	static void fill_cube(int[][][] cube, int val) {
		int N = cube.length;
		int M = cube[0].length;
		int K = cube[0][0].length;
		
		for(int i=0; i<N; i++)
			for(int j=0; j<M; j++)
				for(int k=0; k<K; k++)
					cube[i][j][k] = val;
	}
	
	static int find_min(int counts[], int K) {
		int ret = Integer.MAX_VALUE;
		for(int i=0; i<K; i++) {
			if(counts[i] < ret) {
				ret = counts[i];
			}
		}
		return (ret == Integer.MAX_VALUE) ? -1 : ret;
	}
}
