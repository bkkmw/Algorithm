package boj;

import java.io.*;
import java.util.*;

public class MainB_11779 {

	public static final int INF = 100000001;
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input/boj/input_11779.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		
		List<int[]>[] edges = init_lists(N);
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int src = Integer.parseInt(st.nextToken())-1;
			int dst = Integer.parseInt(st.nextToken())-1;
			int cost = Integer.parseInt(st.nextToken());
			
			edges[src].add(new int[] {dst, cost});
		}
		
		st = new StringTokenizer(br.readLine());
		
		int src = Integer.parseInt(st.nextToken())-1;
		int dst = Integer.parseInt(st.nextToken())-1;
		
		solve(edges, src, dst, sb);
		
		System.out.println(sb.toString());
		
	}
	
	public static void solve(List<int[]>[] edges, int src, int dst, StringBuilder sb) {
		int N = edges.length;
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1,o2) -> {
			return o1[1] - o2[1];
		});
		int[] path = init_arr(N, -1);
		int[] dist = init_arr(N, INF);
		
		dist[src] = 0;
		pq.add(new int[] {src, 0});
		
		while(!pq.isEmpty()) {
			int[] poll = pq.poll();
			if(poll[1] >= dist[dst]) break;
			
			Iterator<int[]> it = edges[poll[0]].iterator();
			while(it.hasNext()) {
				int[] next = it.next();
				
				if(dist[next[0]] > poll[1] + next[1]) {
					dist[next[0]] = poll[1]+next[1];
					pq.add(new int[] {next[0], poll[1]+next[1]});
					path[next[0]] = poll[0];
				}
			}
		}
		
		sb.append(dist[dst]).append("\n");
		print_path(path, dst, sb);
	}
	
	public static void print_path(int[] prev, int dst, StringBuilder sb) {
		int node = dst, cnt = 0;
		int[] path = new int[prev.length];
		
		path[cnt++] = dst;
		
		while(prev[node] > -1) {
			node = prev[node];
			path[cnt++] = node;
		}
		
		sb.append(cnt).append("\n");
		for(int i=cnt-1; i>-1; i--) {
			sb.append(path[i]+1).append(" ");
		}
	}
	
	public static List<int[]>[] init_lists(int N) {
		List<int[]>[] ret = new LinkedList[N];
		
		for(int i=0; i<N; i++)
			ret[i] = new LinkedList<int[]>();
			
		return ret;
	}
	
	public static int[] init_arr(int N, int val) {
		int[] ret = new int[N];
		
		for(int i=0; i<N; i++) {
			ret[i] = val;
		}
		
		return ret;
	}
}
