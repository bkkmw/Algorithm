package boj;

import java.io.*;
import java.util.*;

public class MainB_21610 {
	
	public static int[][] dir_yx = new int[][] {
		{0,-1}, {-1,-1}, {-1,0}, {-1,+1}, {0,+1}, {+1,+1}, {+1,0}, {+1,-1}
	};
	
	public static int[][] diag_dir_yx = new int[][] {
		{-1,+1}, {+1,+1}, {+1,-1}, {-1,-1}
	};

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input/boj/input_21610.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[N][N];
		int[][] cmd = new int[M][2];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<2; j++) {
				cmd[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		
		int ans = solve(map, cmd);
		System.out.println(ans);
		br.close();
	}
	
	public static int solve(int[][] map, int[][] cmd) {
		int N=map.length, M=cmd.length;

		List<int[]> clouds = new LinkedList<int[]>();
		List<int[]> disappeared_clouds = null;
		
		create_clouds(clouds, N);
		
		for(int i=0; i<M; i++) {
			
			clouds = move_clouds(clouds, N, cmd[i]);
			
			rain(map, clouds);
			
			disappeared_clouds = clear_clouds(clouds);
			
			cast_copy_bug(map, disappeared_clouds);
			
			create_clouds(map, clouds, disappeared_clouds);
			disappeared_clouds.clear();;
		}
		
		return count_water(map);
	}
	
	public static void create_clouds(List<int[]> clouds, int N) {
		int[][] init = new int[][] {
			{N-1, 0}, {N-1,1}, {N-2,0}, {N-2,1}
		};
		
		for(int i=0; i<init.length; i++) {
			clouds.add(new int[] {init[i][0], init[i][1]});
		}
	}
	
	public static List<int[]> move_clouds(List<int[]> clouds, int N, int[] cmd) {
		List<int[]> ret = new ArrayList<int[]>();
				
		int dy = dir_yx[cmd[0]-1][0] * cmd[1];
		int dx = dir_yx[cmd[0]-1][1] * cmd[1];
		
		int neg_guard = N*50;
		
		Iterator<int[]> it = clouds.iterator();
		while(it.hasNext()) {
			int[] temp = it.next();
			int ni = (neg_guard + temp[0] + dy) % N;
			int nj = (neg_guard + temp[1] + dx) % N;
			
			ret.add(new int[] {ni, nj});
		}
		
		return ret;
	}
	
	public static void rain(int[][] map, List<int[]> clouds) {
		Iterator<int[]> it = clouds.iterator();
		int N = map.length;
		
		boolean[][] check = new boolean[N][N];
		
		while(it.hasNext()) {
			int[] temp = it.next();
			if(check[temp[0]][temp[1]]) continue;
			
			map[temp[0]][temp[1]] ++;
			check[temp[0]][temp[1]] = true;
		}
	}
	
	public static List<int[]> clear_clouds(List<int[]> clouds) {
		List<int[]> ret = new ArrayList<int[]>();
		
		Iterator<int[]> it = clouds.iterator();
		
		while(it.hasNext()) {
			int[] temp = it.next();
			ret.add(new int[] {temp[0], temp[1]});
		}
		
		clouds.clear();
		return ret;
	}
	
	public static void cast_copy_bug(int[][] map, List<int[]> rained_pos) {
		List<int[]> increments = new ArrayList<int[]>();
		Iterator<int[]> it = rained_pos.iterator();
		
		while(it.hasNext()) {
			int[] temp = it.next();
			int increment = count_diagonal_bucket(map, temp[0], temp[1]);
			
			increments.add(new int[] {temp[0], temp[1], increment});
		}
		
		it = increments.iterator();
		while(it.hasNext()) {
			int[] temp = it.next();
			map[temp[0]][temp[1]] += temp[2];
		}
	}
	
	public static int count_diagonal_bucket(int[][] map, int i, int j) {
		int ret = 0;
		int N = map.length;
		
		for(int d=0; d<4; d++) {
			int ni = i + diag_dir_yx[d][0];
			int nj = j + diag_dir_yx[d][1];
			
			if(ni<0 || ni>N-1 || nj<0 || nj>N-1) continue;
			if(map[ni][nj] > 0) ret++;
		}
		
		return ret;
	}
	
	public static void create_clouds(int[][] map, List<int[]> clouds, List<int[]> disappeared_clouds) {
		int N = map.length;
		boolean[][] disappeared = new boolean[N][N];
		
		Iterator<int[]> it = disappeared_clouds.iterator();
		while(it.hasNext()) {
			int[] temp = it.next();
			disappeared[temp[0]][temp[1]] = true;
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(map[i][j] >= 2 && !disappeared[i][j]) {
					map[i][j] -= 2;
					clouds.add(new int[] {i, j});
				}
			}
		}
	}
	
	public static int count_water(int[][] map) {
		int N = map.length, ret = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				ret += map[i][j];
			}
		}
		
		return ret;
	}
}
