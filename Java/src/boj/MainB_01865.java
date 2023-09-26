package boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class MainB_01865 {

	public static int INF = 2500 * 10000 + 1;
	
	public static void main(String[] args) throws Exception {
				
		System.setIn(new FileInputStream("input/boj/input_01865.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int TC = Integer.parseInt(br.readLine());
		
		for(int t=1; t<TC+1; t++) {
			st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int W = Integer.parseInt(st.nextToken());
			
			List<int[]> edges = new ArrayList<>(2*M + W);
			
			for(int i=0; i<M; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int S = Integer.parseInt(st.nextToken()) - 1;
				int E = Integer.parseInt(st.nextToken()) - 1;
				int T = Integer.parseInt(st.nextToken());
				
				edges.add(new int[] {S, E, T});
				edges.add(new int[] {E, S, T});
			}
			
			for(int i=0; i<W; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int S = Integer.parseInt(st.nextToken()) - 1;
				int E = Integer.parseInt(st.nextToken()) - 1;
				int T = Integer.parseInt(st.nextToken());
				
				edges.add(new int[] {S, E, -T});
			}
			
			boolean ans = solve(edges, N);
			sb.append(ans? "YES" : "NO").append("\n");
		}
		
		System.out.println(sb.toString());
	}
	
	public static boolean solve(List<int[]> edges, int N) {
		
		int[] dist = init_arr(N, INF);
		
		dist[0] = 0;
		for(int i=0; i<N; i++) {
			for(int[] edge : edges) {
				if(dist[edge[1]] > dist[edge[0]] + edge[2])
					dist[edge[1]] = dist[edge[0]] + edge[2];
			}
		}
		
		return find_negative_cycle(dist, edges, N);
	}
	
	public static boolean find_negative_cycle(int[] dist, List<int[]> edges, int N) {
		boolean ret = false;
		
		for(int[] edge : edges) {
			if(dist[edge[1]] > dist[edge[0]] + edge[2])
				return true;
		}
		
		return ret;
	}
	
	public static int[] init_arr(int N, int val) {
		int[] ret = new int[N];
		for(int i=0; i<N; i++)
			ret[i] = val;
		return ret;
	}
}
