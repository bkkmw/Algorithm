package boj;

import java.io.*;
import java.util.*;

public class MainB_01240 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input/boj/input_01240.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		List<int[]>[] edges = init_lists(N);
		
		for(int i=0; i<N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int src = Integer.parseInt(st.nextToken())-1;
			int dst = Integer.parseInt(st.nextToken())-1;
			int cost = Integer.parseInt(st.nextToken());
			
			edges[src].add(new int[] {dst, cost});
			edges[dst].add(new int[] {src, cost});
		}
		
		int[][] pairs = new int[M][2];
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			pairs[i][0] = Integer.parseInt(st.nextToken())-1;
			pairs[i][1] = Integer.parseInt(st.nextToken())-1;
		}
		
		solve(edges, pairs, sb);
		System.out.println(sb.toString());
		br.close();
	}
	
	public static void solve(List<int[]>[] edges, int[][] pairs, StringBuilder sb) {
		int N = edges.length, M = pairs.length;
		
		for(int i=0; i<M; i++) {
			int dist = bfs(edges, pairs[i][0], pairs[i][1]);
			sb.append(dist).append("\n");
		}
		
	}
	
	public static int bfs(List<int[]>[] edges, int src, int dst) {
		int ret = 0, N = edges.length;
		boolean[] visited = new boolean[N];
		Queue<int[]> q = new LinkedList<int[]>();
		
		q.add(new int[] {src, 0});
		visited[src] = true;
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			
			Iterator<int[]> it = edges[cur[0]].iterator();
			while(it.hasNext()) {
				int[] next = it.next();
				if(visited[next[0]]) continue;
				if(next[0] == dst) return cur[1] + next[1];
				
				q.add(new int[] {next[0], cur[1]+next[1]});
				visited[next[0]] = true;
			}
		}
		return ret;
	}
	
	public static List<int[]>[] init_lists(int N) {
		List<int[]>[] ret = new List[N];
		for(int i=0; i<N; i++)
			ret[i] = new LinkedList<int[]>();
			
		return ret;
	}
}
