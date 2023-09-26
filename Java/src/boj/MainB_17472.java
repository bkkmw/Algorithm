package boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class MainB_17472 {
	static int[][] dir_yx = new int[][] { {-1,0},{0,+1},{+1,0},{0,-1} };
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input/boj/input_17472.txt"));
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
				map[i][j] = (temp==1)? -1 : 0;
			}
		}
		
		int ans = solve(map, N, M);
		System.out.println(ans);
	}
	
	static int solve(int[][] src_map, int N, int M) {
		int ret;
		int icnt = mark_island(src_map, N, M);
		int[][] dist = count_dists(src_map, N, M, icnt);
		PriorityQueue<int[]> pq = new PriorityQueue<int[]>(
				(o1, o2) -> {return o1[2] - o2[2];}
		);
		list_bridges(dist, icnt, pq);
		ret = connect(pq, icnt);
		
		return (ret==0)? -1 : ret;
	}
	
	static int mark_island(int[][] map, int N, int M) {
		int ret = 0;
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j] == -1) {
					mark_bound(map, N, M, i, j, ++ret);
				}
			}
		}
		return ret;
	}
	
	static void mark_bound(int[][] map, int N, int M, int i, int j, int val) {
		Queue<int[]> q = new LinkedList<int[]>();
		q.add(new int[] {i, j});
		map[i][j] = val;
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			for(int d=0; d<4; d++) {
				int ny = cur[0] + dir_yx[d][0];
				int nx = cur[1] + dir_yx[d][1];
				if(ny<0 || ny>N-1 || nx<0 || nx>M-1) continue;
				if(map[ny][nx] == -1) {
					q.add(new int[] {ny, nx});
					map[ny][nx] = val;
				}
			}
		}
	}
	
	static int[][] count_dists(int[][] map, int N, int M, int icnt){
		int[][] ret = new int[icnt+1][icnt+1];
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j] != 0) {
					count_dist(map, N, M, i, j, ret);
				}
			}
		}
		return ret;
	}
	
	static void count_dist(int[][] map, int N, int M, int i, int j, int[][] dist) {
		int cnt = 0;
		for(int d=0; d<4; d++) {
			int ny = i + dir_yx[d][0];
			int nx = j + dir_yx[d][1];
			cnt = 0;
			while(ny>=0 && ny<N && nx>=0 && nx<M) {
				if(map[ny][nx] == map[i][j]) break;
				if(map[ny][nx] != 0) {
					if(cnt < 2) break;
					if(dist[map[i][j]][map[ny][nx]] == 0 ||
							dist[map[i][j]][map[ny][nx]] > cnt) {
						dist[map[i][j]][map[ny][nx]] = cnt;
						dist[map[ny][nx]][map[i][j]] = cnt;						
					}
					break;
				}
				ny += dir_yx[d][0]; nx += dir_yx[d][1];
				cnt++;
			}
		}
	}
	
	static void list_bridges(int[][] dist, int icnt, PriorityQueue<int[]> pq) {
		for(int i=1; i<icnt+1; i++) 
			for(int j=i+1; j<icnt+1; j++) 
				if(dist[i][j] != 0) pq.add(new int[] {i,j,dist[i][j]});
	}
	
	static int connect(PriorityQueue<int[]> pq, int icnt) {
		int ret = 0;
		int bcnt = 0;
		int[] parent = new int[icnt+1];
		for(int i=1; i<icnt+1; i++) parent[i] = i;
		
		while(!pq.isEmpty() && bcnt < icnt) {
			int[] cur = pq.poll();
			if(find(parent, cur[0]) == find(parent, cur[1])) continue;
			ret += cur[2];
			union(parent, cur[0], cur[1]);
			bcnt ++;
		}
		for(int i=2; i<icnt+1; i++) {
			if(find(parent, 1) != find(parent, i)) ret = -1;
		}
		return ret;
	}
	
	static void union(int[] parent, int src, int dst) {
		int spar = src;
		while(parent[spar] != spar) spar = parent[spar];
		
		while(parent[src] != src) {
			int next = parent[src];
			parent[src] = spar;
			src = next;
		}
		while(parent[dst] != dst) {
			int next = parent[dst];
			parent[dst] = spar;
			dst = next;
		}
		parent[dst] = spar;
	}
	
	static int find(int[] parent, int tar) {
		while(parent[tar] != tar) {
			tar = parent[tar];
		}
		return tar;
	}
}
