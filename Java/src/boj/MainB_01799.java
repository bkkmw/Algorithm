package boj;

import java.io.*;
import java.util.*;

public class MainB_01799 {
	
	static int[][] dir_yx = new int[][] { {+1,+1}, {+1,-1} };
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input/boj/input_01799.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		boolean[][] map = new boolean[N][N];
		
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for(int j=0; j<N; j++) {
				map[i][j] = "1".equals(st.nextToken()) ? true : false;
			}
		}
		
		int ans = solve(map);
		
		System.out.println(ans);
		br.close();
	}
	
	public static int solve(boolean[][] map) {
		int ret = 0;
		int N = map.length;
		int[][] mem = init_arr(N, -1);
		
		int empty_cnt[] = count_empty_space(map);
				
		ret = find_max(map, mem, N, 0, 0, empty_cnt[0], 0, 0);
		
		mem = init_arr(N, -1);
		ret += find_max(map, mem, N, 1, 0, empty_cnt[1], 0, 1);
		return ret;
	}
	
	public static int[] count_empty_space(boolean[][] map) {
		int[] ret = new int[2];
		int N = map.length;
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(map[i][j]) {
					ret[(i+j)%2] ++;
				}
			}
		}
		
		return ret;
	}
	
	public static int find_max(boolean[][] map, int[][] mem, int N, int idx, int cnt, int remaining, int max, int odd) {
		if(idx >= N*N) {
			return cnt;
		}
		if(cnt + remaining <= max) 
			return cnt;
		
		int i = idx/N, j = idx%N;
		
		if((i+j) % 2 != odd) return find_max(map,mem, N, idx+1, cnt, remaining, max, odd);
		
		if(!map[i][j]) {
			return find_max(map, mem, N, idx+1, cnt, remaining, max, odd);
		}
		
		int removed = update_map(map, mem, i, j);
		
		int c1 = find_max(map, mem, N, idx+1, cnt+1, remaining - removed, max, odd);
		if(c1 > max) max = c1;
		recover_map(map, mem, i, j);
		
		int c2 = find_max(map, mem, N, idx+1, cnt, remaining, max, odd);
		if(c2 > max) max = c2;
		
		return max;
	}

	public static int update_map(boolean[][] map, int[][] mem, int i, int j) {
		int ret = 1;
		int N = map.length;
		int idx = i*N + j;
		
		map[i][j] = false;
		mem[i][j] = idx;
		
		for(int k=1; k<N-i; k++) {
			for(int d=0; d<dir_yx.length; d++) {
				int ni = i + k*dir_yx[d][0];
				int nj = j + k*dir_yx[d][1];
				
				if(ni<0 || nj<0 || ni>N-1 || nj>N-1 || (map[ni][nj] == false)) continue;
				
				map[ni][nj] = false;
				mem[ni][nj] = idx;
				ret++;
			}
		}
		
		return ret;
	}
	
	public static void recover_map(boolean[][] map, int[][] mem, int i, int j) {
		int N = map.length;
		int idx = i*N + j;
		
		map[i][j] = true;
		mem[i][j] = -1;
		
		for(int k=1; k<N-i-1; k++) {
			for(int d=0; d<dir_yx.length; d++) {
				int ni = i + k*dir_yx[d][0];
				int nj = j + k*dir_yx[d][1];
				
				if(ni<0 || nj<0 || ni>N-1 || nj>N-1) continue;
				
				if(map[ni][nj] == false && mem[ni][nj] == idx) {
					map[ni][nj] = true;
					mem[ni][nj] = -1;
				}
			}
		}
		
	}
	
	public static void copy_arr(boolean[][] src, boolean[][] dst) {
		int N = src.length;
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				dst[i][j] = src[i][j];
			}
		}
	}
	
	public static int[][] init_arr(int N, int val) {
		int[][] ret = new int[N][N];
		for(int i=0; i<N; i++)
			for(int j=0; j<N; j++)
				ret[i][j] = val;
		return ret;
	}
}
