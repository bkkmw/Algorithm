package swea;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_3124 {
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input/swea/input_3124.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=TC; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			int V = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());
			
			Edge[] list = new Edge[E];
			
			for(int i=0; i<E; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int A = Integer.parseInt(st.nextToken()) - 1;
				int B = Integer.parseInt(st.nextToken()) - 1;
				int C = Integer.parseInt(st.nextToken());
				list[i] = new Edge(A, B, C);
			}
			
			Arrays.sort(list);
			
			sb.append("#").append(tc).append(" ");
			long ans = solve(V, list);
			sb.append(ans).append("\n");
		}
		System.out.println(sb);
	}
	
	static long solve(int V, Edge[] list) {
		long ret = 0;
		int cnt = 0;
		int[] par = new int[V];
		for(int i=0; i<V; i++) par[i] = i;
		
		for(int i=0; i<list.length; i++) {
			if(union(par, list[i].from, list[i].to)) {
				ret += list[i].weight;
				cnt++;
				if(cnt == V-1) return ret;
			}
		}		
		return ret;
	}
	
	static int find(int[] par, int a) {
		if(par[a] == a) return a;
		return par[a] = find(par, par[a]);
	}
	
	static boolean union(int[] par, int a, int b) {
		int aroot = find(par, a);
		int broot = find(par, b);
		
		if(aroot == broot) return false;
		par[broot] = aroot;
		return true;
	}
	
	static class Edge implements Comparable<Edge>{
		int from;
		int to;
		int weight;
		
		public Edge(int from, int to, int weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}
		@Override
		public int compareTo(Edge o) {
			// TODO Auto-generated method stub
			return this.weight - o.weight;
		}
		
	}
}

