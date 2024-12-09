package boj;

import java.util.*;
import java.io.*;

public class MainB_02325 {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream(new File("input/boj/input_02325.txt")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][] edges = new int[N][N];
		for(int i=0; i<N; i++) {
			edges[i] = init_arr(N, 1001);
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int src = Integer.parseInt(st.nextToken())-1;
			int dst = Integer.parseInt(st.nextToken())-1;
			int dist = Integer.parseInt(st.nextToken());
			
			edges[src][dst] = dist;
			edges[dst][src] = dist;
		}
		
		int ans = solve(edges);
		System.out.println(ans);
		br.close();
	}
	
	public static int solve(int[][] edges) {
		int ret = 0, N = edges.length;
		int[] parent = init_arr(N, 0);
		int min_dist = find_min_dist(edges, parent);
		
		Stack<int[]> st = new Stack<int[]>();
		int next = N-1;
		while(next > 0) {
			st.push(new int[] {next, parent[next]});
			next = parent[next];
		}
		
		while(!st.isEmpty()) {
			int[] pop = st.pop();
			int weight = edges[pop[0]][pop[1]]; 
			
			edges[pop[0]][pop[1]] = 1001;
			edges[pop[1]][pop[0]] = 1001;
			
			int temp = find_min_dist(edges, init_arr(N, 0));
			if(temp > ret) ret = temp;
			
			edges[pop[0]][pop[1]] = weight;
			edges[pop[1]][pop[0]] = weight;
			
		}
		
		
		return ret;
	}
	
	public static int find_min_dist(int[][] edges, int[] parent) {
		int ret = 0, N = edges.length;
		
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1]-o2[1]);
		int[] dist = init_arr(N, Integer.MAX_VALUE);
		parent[0] = -1;
		dist[0] = 0;
		
		for(int i=0; i<N; i++) {
			if(i == 0 || edges[0][i] > 1000) continue;
			dist[i] = edges[0][i];
			parent[i] = 0;
			pq.add(new int[] {i, dist[i]});
		}
		
		
		while(!pq.isEmpty()) {
			int[] poll = pq.poll();
			
			for(int i=0; i<N; i++) {
				if(poll[0] == i || edges[poll[0]][i] > 1000) continue;
				
				if(dist[i] > dist[poll[0]] + edges[poll[0]][i]) {
					dist[i] = dist[poll[0]] + edges[poll[0]][i];
					
					parent[i] = poll[0];
					pq.add(new int[] {i, dist[i]});
				}
			}
			
			
		}
		
		return dist[N-1];
	}
	
	public static int[] init_arr(int size, int val) {
		int[] ret = new int[size];
		
		for(int i=0; i<size; i++) {
			ret[i] = val;
		}
		
		return ret;
	}
	
}
