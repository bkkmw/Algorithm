package boj;

import java.util.*;
import java.io.*;

public class MainB_02307 {
	
	public static final int INF = 100000000;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input/boj/input_02307.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		List<int[]>[] edges = init_lists(N);
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int src = Integer.parseInt(st.nextToken())-1;
			int dst = Integer.parseInt(st.nextToken())-1;
			int cost = Integer.parseInt(st.nextToken());
			
			edges[src].add(new int[] {dst, cost});
			edges[dst].add(new int[] {src, cost});
		}
		
		int ans = solve(edges);
		System.out.println(ans);
		
		br.close();
	}
	
	public static int solve(List<int[]>[] edges) {
		int ret = 0, N = edges.length;
		int[] route = new int[N];
		route[0] = -1;
		
		int min = dijkstra(edges, N, route, -1, -1);
		
		int idx = N-1;
		while(route[idx] != -1) {
			int src = route[idx], dst = idx;
			int temp = dijkstra(edges, N, route, src, dst);
			
			if(temp == INF) return -1;
			ret = (temp-min) > ret ? temp-min : ret;
			idx = route[idx];
		}
		
		return ret;
	}
	
	public static int dijkstra(List<int[]>[] edges, int N, int[] route, int src, int dst) {
		int[] dist = init_arr(N, INF);
		PriorityQueue<int[]> pq = new PriorityQueue<int[]>((o1, o2) -> 	o1[1]-o2[1]);
		
		pq.add(new int[] {0, 0});
		dist[0] = 0;
		
		while(!pq.isEmpty()) {
			int[] cur = pq.poll();
			
			Iterator<int[]> it = edges[cur[0]].iterator();
			while(it.hasNext()) {
				int[] next = it.next();
				
				if(cur[0] == src && next[0] == dst) continue;
				
				if(dist[next[0]] > dist[cur[0]] + next[1]) {
					dist[next[0]] = dist[cur[0]] + next[1];
					if(src == -1)
						route[next[0]] = cur[0];
					pq.add(new int[] {next[0], dist[next[0]]});
				}
			}
		}
		
		return dist[N-1];
	}
	
	public static List<int[]>[] init_lists(int N) {
		List<int[]>[] ret = new LinkedList[N];
		for(int i=0; i<N; i++)
			ret[i] = new LinkedList<int[]>();
			
		return ret;
	}
	
	public static int[] init_arr(int N, int val) {
		int[] ret = new int[N];
		for(int i=0; i<N; i++)
			ret[i] = val;
		
		return ret;
	}
}
