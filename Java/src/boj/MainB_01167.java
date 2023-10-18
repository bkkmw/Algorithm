package boj;

import java.util.*;
import java.io.*;

public class MainB_01167 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input/boj/input_01167.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		
		List<int[]>[] edges = new ArrayList[N];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int cur = Integer.parseInt(st.nextToken())-1;
			edges[cur] = new ArrayList<int[]>();
			while(true) {
				int node = Integer.parseInt(st.nextToken());
				if(node == -1) break;
				int w = Integer.parseInt(st.nextToken());
				
				edges[cur].add(new int[] {node-1, w});
			}
		}
		
		int ans = solve(edges);
		System.out.println(ans);
		br.close();
	}
	
	public static int solve(List<int[]>[] edges) {
		int N = edges.length;
		
		boolean[] visited = new boolean[N];
		visited[0] = true;
		int[] res = find_max(edges, visited, 0, 0);
		
		visited = new boolean[N];
		visited[res[0]] = true;
		return find_max(edges, visited, res[0], 0)[1];
	}
	
	public static int[] find_max(List<int[]>[] edges, boolean[] visited, int node, int dist) {
		int len = edges[node].size();
		int[] ret = new int[2];
		for(int i=0; i<len; i++) {
			int[] next = edges[node].get(i);
			if(visited[next[0]]) continue;
			
			visited[next[0]] = true;
			int[] temp = find_max(edges, visited, next[0], dist+next[1]);
			
			if(temp[1] > ret[1]) {
				ret[0] = temp[0];
				ret[1] = temp[1];
			}
		}
		
		if(ret[1] == 0) {
			ret[0] = node;
			ret[1] = dist;
		}
		return ret;
	}
}
