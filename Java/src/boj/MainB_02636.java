package boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class MainB_02636 {
	static int[][] dir_yx = new int[][] {
		{-1,0},{0,+1},{+1,0},{0,-1}
	};
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input/boj/input_02636.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[N][M];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<M; j++) {
				int temp = Integer.parseInt(st.nextToken());
				map[i][j] = (temp == 0)? -1 : -2;
			}
		}
		
		int[] ans = solve(map, N, M);
		System.out.printf("%d\n%d", ans[0], ans[1]);
	}
	
	static int[] solve(int[][] map, int N, int M) {
		int[] ret = new int[2];
		mark_area(map, 0, 0, 0);
		int vacants_cnt = fill_vacants(map, N, M);
		int[] vacants = count_vacants_dist(map, N, M, vacants_cnt);
		int[][] dist_map = update_dist(map, N, M, vacants);
		ret = count_ans(dist_map, N, M);
		
		return ret;
	}
	

	static void mark_area(int[][] map, int i, int j, int val) {
		int N = map.length; int M = map[0].length;
		Queue<int[]> q = new LinkedList<int[]>();
		q.add(new int[] {i,j});
		map[i][j] = val;
		
		while(!q.isEmpty()) {
			int[] temp = q.poll();
			for(int d=0; d<4; d++) {
				int ny = temp[0] + dir_yx[d][0];
				int nx = temp[1] + dir_yx[d][1];
				if(ny<0 || ny>N-1 || nx<0 || nx>M-1) continue;
				if(map[ny][nx] != -1) continue;
				q.add(new int[] {ny,nx});
				map[ny][nx] = val;
			}
		}
	}
	
	static int fill_vacants(int[][] map, int N, int M){
		int ret = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j] == -1) {
					mark_area(map, i, j, ++ret);
				}
			}
		}
		return ret;
	}
	
	static int[] count_vacants_dist(int[][] map, int N, int M, int cnt) {
		int[] ret = new int[cnt+1];
		for(int i=0; i<N; i++)
			for(int j=0; j<M; j++)
				if(map[i][j] > 0) 
					ret[map[i][j]] = count_dist(map, ret, N, M, i, j);
		for(int i=N-1; i>-1; i--) {
			for(int j=M-1; j>-1; j--) {
				if(map[i][j] > 0) {
					int temp = count_dist(map, ret, N, M, i, j);
					ret[map[i][j]] = (temp < ret[map[i][j]])? temp : ret[map[i][j]];
				}
			}
		}
		return ret;
	}
	
	static int count_dist(int[][] map, int[] vacants, int N, int M, int i, int j) {
		int ret = 10000;
		Queue<int[]> q = new LinkedList<int[]>();
		boolean[][] visited = new boolean[N][M];
		q.add(new int[] {i, j, 0});
		visited[i][j] = true;
		
		while(!q.isEmpty()) {
			int[] temp = q.poll();
			for(int d=0; d<4; d++) {
				int ny = temp[0] + dir_yx[d][0];
				int nx = temp[1] + dir_yx[d][1];
				int cnt = temp[2];
				
				if(ny<0 || ny>N-1 || nx<0 || nx>M-1) continue;
				if(visited[ny][nx] == true) continue;
				if(cnt >= ret) continue;
				
				if(map[ny][nx] == 0) {
					ret = (cnt < ret)? cnt : ret;
				}
				else if(map[ny][nx] > 0 && vacants[map[ny][nx]] != 0) {
					cnt += vacants[map[ny][nx]];
					ret = (cnt < ret)? cnt : ret;
					continue;
				}
				q.add(new int[] {ny, nx, cnt+1});
				visited[ny][nx] = true;
			}	
		}
		return ret;
	}
	
	static int[][] update_dist(int[][] map, int N, int M, int[] vacants) {
		int[][] ret = new int[N][M];
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j] == -2) {
					int min = Integer.MAX_VALUE;
					for(int d=0; d<4; d++) {
						int ny = i + dir_yx[d][0];
						int nx = j + dir_yx[d][1];
						if(ny<0 || ny>N-1 || nx<0 || nx>M-1) continue;
						int temp;
						if(map[ny][nx] == -2) {
							if(ret[ny][nx] == 0) continue;
							temp = ret[ny][nx] + 1;
						}
						else temp = vacants[map[ny][nx]] + 1;
						min = (temp < min)? temp : min;
					}
					ret[i][j] = min;
				}
			}
		}
		int max_dist = 0;
		for(int i=N-1; i>-1; i--) {
			for(int j=M-1; j>-1; j--) {
				if(map[i][j] == -2) {
					for(int d=0; d<4; d++) {
						int ny = i + dir_yx[d][0];
						int nx = j + dir_yx[d][1];
						if(ny<0 || ny>N-1 || nx<0 || nx>M-1) continue;
						int temp;
						if(map[ny][nx] == -2) {
							if(ret[ny][nx] == 0) continue;
							temp = ret[ny][nx] + 1;
						}
						else temp = vacants[map[ny][nx]] + 1;
						ret[i][j] = (temp < ret[i][j])? temp : ret[i][j];
						if(ret[i][j] > max_dist) max_dist = ret[i][j];
					}
				}
			}
		}
		return ret;
	}

	static int[] count_ans(int[][] dist_map, int N, int M) {
		int max = 0;
		int cnt = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(dist_map[i][j] > max) {
					max = dist_map[i][j];
					cnt = 1;
				}
				else if(dist_map[i][j] == max) {
					cnt ++;
				}
			}
		}
		if(max == 0) return new int[] {0, 0}; 
		return new int[] {max, cnt};
	}
}
