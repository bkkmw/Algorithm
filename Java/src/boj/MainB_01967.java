package boj;

import java.io.*;
import java.util.*;

public class MainB_01967 {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input/boj/input_01967.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		List<int[]>[] edges = init_lists(N);
		
		for(int i=0; i<N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int src = Integer.parseInt(st.nextToken())-1;
			int dst = Integer.parseInt(st.nextToken())-1;
			int w = Integer.parseInt(st.nextToken());
			
			edges[src].add(new int[] {dst, w});
			edges[dst].add(new int[] {src, w});
		}
		
		int ans = solve(edges);
		System.out.println(ans);
	}
	
	public static int solve(List<int[]>[] edges) {
		int N = edges.length;
		
		int ans[] = find_max_dist(edges, 0);
		ans = find_max_dist(edges, ans[0]);
		
		return ans[1];
	}
	
	public static int[] find_max_dist(List<int[]>[] edges, int src) {
		int N = edges.length, max_dist = 0, max_node = 0;
		Queue<int[]> q = new LinkedList<int[]>();
		boolean[] visited = new boolean[N];
		
		q.add(new int[] {src, 0});
		visited[src] = true;
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			
			Iterator<int[]> it = edges[cur[0]].iterator();
			while(it.hasNext()) {
				int[] next = it.next();
				if(visited[next[0]]) continue;
				
				int[] new_node = new int[] {next[0], cur[1]+next[1]};
				q.add(new_node);
				visited[new_node[0]] = true;
				
				if(new_node[1] > max_dist) {
					max_dist = new_node[1];
					max_node = new_node[0];
				}
			}
		}
		
		return new int[] {max_node, max_dist};
	}
	
	public static List<int[]>[] init_lists(int N) {
		List<int[]>[] ret = new LinkedList[N];
		for(int i=0; i<N; i++) {
			ret[i] = new LinkedList<int[]>();
		}
		return ret;
	}
}
