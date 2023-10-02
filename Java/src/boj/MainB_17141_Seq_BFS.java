package boj;

import java.util.*;
import java.io.*;

// 228 ms & 43108KB
public class MainB_17141_Seq_BFS {
	
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
		int ret = -1;
		int N = map.length, V = virus.length;
		
		boolean[] used = new boolean[V];
		
		ret = generate_comb_seq(map, virus, M, used, 0, 0, N*N);
		return ret== N*N ? -1 : ret;
	}
	
	public static int generate_comb_seq(int[][] map, int[][] virus, int M, boolean[] used, int idx, int cnt, int min) {
		if(cnt == M) {
			return bfs(map, used, virus, min);
		}
		if(idx == used.length)
			return min;
		
		used[idx] = true;
		min = generate_comb_seq(map, virus, M, used, idx+1, cnt+1, min);
		used[idx] = false;
		min = generate_comb_seq(map, virus, M, used, idx+1, cnt, min);
		
		return min;
	}
	
	public static int bfs(int[][] map, boolean[] used, int[][] virus, int min) {
		int N = map.length, V = used.length;
		Queue<int[]> q = new LinkedList<int[]>();
		int[][] check = init_mat(N, -1);
		
		for(int i=0; i<V; i++) {
			if(used[i]) {
				q.add(new int[] {virus[i][0], virus[i][1], 0});
				check[virus[i][0]][virus[i][1]] = 0;
			}
		}
		
		while(!q.isEmpty()) {
			int[] poll = q.poll();
			int y = poll[0], x = poll[1], cnt = poll[2];
			if(cnt >= min) return min;
			
			for(int d=0; d<4; d++) {
				int ny = y+dir_yx[d][0];
				int nx = x+dir_yx[d][1];
				
				if(ny<0 || ny>N-1 || nx<0 || nx>N-1) continue;
				if(map[ny][nx] == 1) continue;
				if(check[ny][nx] != -1 && check[ny][nx] <= cnt+1) continue;
				
				q.add(new int[] {ny, nx, cnt+1});
				check[ny][nx] = cnt+1;
			}
		}
		
		int ret = validate(map, check);
		return ret==-1? min : ret;
	}
	
	public static int validate(int[][] map, int[][] check) {
		int N = map.length;
		int ret = -1;
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(map[i][j] != 1 && check[i][j] == -1) return -1;
				ret = (ret<check[i][j])? check[i][j] : ret;
			}
		}
		return ret;
	}
	
	public static int[][] init_mat(int N, int val) {
		int[][] ret = new int[N][N];
		for(int i=0; i<N; i++)
			for(int j=0; j<N; j++)
					ret[i][j] = val;
		
		return ret;
	}
}
