package boj;

import java.util.*;
import java.io.*;

// 160 ms & 14425KB
public class MainB_17141_BFS_Seq {
	
	public static int[][] dir_yx = new int[][] { {-1,0}, {0,+1}, {+1,0}, {0,-1} };

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input/boj/input_17141.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[N][N];
		int[][] virus = new int[10][2];
		int v_idx = 0;
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 2) {
					virus[v_idx++] = new int[] {i, j};
				}
			}
		}
		int ans = solve(map, Arrays.copyOfRange(virus, 0, v_idx), M);
		System.out.println(ans);
		br.close();
	}
	
	public static int solve(int[][] map, int[][] virus, int M) {
		int N = map.length, v_cnt = virus.length;
		int[][][] check = init_cube(N, v_cnt, -1);
		Queue<int[]> q = new LinkedList<int[]>();
		
		for(int i=0; i<v_cnt; i++) {
			q.add(new int[] {virus[i][0], virus[i][1], i, 0});
			check[virus[i][0]][virus[i][1]][i] = 0;
		}
		
		while(!q.isEmpty()) {
			int[] poll = q.poll();
			int y = poll[0], x = poll[1], src = poll[2], cnt = poll[3];
			
			for(int d=0; d<4; d++) {
				int ny = y+dir_yx[d][0];
				int nx = x+dir_yx[d][1];
				
				if(ny<0 || ny>N-1 || nx<0 || nx>N-1) continue;
				if(map[ny][nx] == 1) continue;
				if(check[ny][nx][src] != -1 && check[ny][nx][src] <= cnt+1) continue;
				
				q.add(new int[] {ny, nx, src, cnt+1});
				check[ny][nx][src] = cnt+1;
			}
		}
		
		return find_ans(map, check, M);
	}
	
	public static int find_ans(int[][] map, int[][][] check, int M) {
		int ret = -1;
		int N = map.length, V = check[0][0].length;
		
		boolean[] used = new boolean[V]; 
		
		ret = generate_comb_seq(map, check, M, used, 0, 0, N*N);
		return ret== N*N ? -1 : ret;
	}

	public static int generate_comb_seq(int[][] map, int[][][] check, int M, boolean[] used, int idx, int cnt, int min) {
		if(cnt == M) {
			return validate(map, check, used, min);
		}
		if(idx == check[0][0].length)
			return min;
		
		used[idx] = true;
		min = generate_comb_seq(map, check, M, used, idx+1, cnt+1, min);
		used[idx] = false;
		min = generate_comb_seq(map, check, M, used, idx+1, cnt, min);
		
		return min;
	}
	
	public static int validate(int[][] map, int[][][] check, boolean[] used, int min) {
		int N = check.length, V = used.length;
		int ret = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(map[i][j] == 1) continue;
				int temp = N*N;
				for(int k=0; k<V; k++) {
					if(used[k] && check[i][j][k] > -1) {
						temp = temp>check[i][j][k]? check[i][j][k] : temp; 
					}
				}
				if(temp == -1) return N*N;
				else if(temp >= min) return min;
				ret = ret<temp? temp : ret;
			}
		}
		return ret;
	}
	
	public static int[][][] init_cube(int N, int d, int val) {
		int[][][] ret = new int[N][N][d];
		for(int i=0; i<N; i++)
			for(int j=0; j<N; j++)
				for(int k=0; k<d; k++)
					ret[i][j][k] = val;
		
		return ret;
	}
}
