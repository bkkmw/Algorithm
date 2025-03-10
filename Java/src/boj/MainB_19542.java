package boj;

import java.io.*;
import java.util.*;

public class MainB_19542 {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input/boj/input_19542.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken())-1;
		int D = Integer.parseInt(st.nextToken());
		
		List<int[]>[] edges = new List[N];
		for(int i=0; i<N; i++) {
			edges[i] = new ArrayList<int[]>();
		}
		
		for(int i=0; i<N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int src = Integer.parseInt(st.nextToken())-1;
			int dst = Integer.parseInt(st.nextToken())-1;
			
			edges[src].add(new int[] {dst});
			edges[dst].add(new int[] {src});
		}
		
		int ans = solve(edges, S, D);
		System.out.println(ans);
		br.close();
	}
	
	public static int solve(List<int[]>[] edges, int S, int D) {
		int ret = 0;
		int N = edges.length;
		Iterator<int[]> it = edges[S].iterator();
		boolean[] checked = new boolean[N];
		
		checked[S] = true;
		
		while(it.hasNext()) {
			int next = it.next()[0];
			
			int dist = calc_next_dist(edges, checked, next, 1, D) + 2;
			ret += dist<0 ? 0 : dist;
		}
		
		return ret;
	}
	
	public static int calc_next_dist(List<int[]>[] edges, boolean[] checked, int node, int cnt, int D) {
		checked[node] = true;
		Iterator<int[]> it = edges[node].iterator();
		boolean is_leaf = true;
		
		int ret = -2*D;
		while(it.hasNext()) {
			int next = it.next()[0];
			if(checked[next]) continue;
			
			is_leaf = false;
			int dist = calc_next_dist(edges, checked, next, 2, D) + 2;
			
			if(ret >= 0) {
				ret += dist<0? 0 : dist;
			} else {
				ret = Math.max(dist, ret);
			}
		}
		
		if(is_leaf) return -2*D;
		
		return ret;
	}
}
