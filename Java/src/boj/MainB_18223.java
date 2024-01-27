package boj;

import java.io.*;
import java.util.*;

public class MainB_18223 {
	
	public static final int INF = 50000001;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input/boj/input_18223.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		int P = Integer.parseInt(st.nextToken());
		
		List<int[]>[] edges = init_lists(V);
		
		for(int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine());
			int src = Integer.parseInt(st.nextToken())-1;
			int dst = Integer.parseInt(st.nextToken())-1;
			int w = Integer.parseInt(st.nextToken());
			
			edges[src].add(new int[] {dst, w});
			edges[dst].add(new int[] {src, w});
		}
		
		boolean ans = solve(edges, P-1);
		System.out.println(ans? "SAVE HIM" : "GOOD BYE");
	}
	
	public static boolean solve(List<int[]>[] edges, int P) {
		int V = edges.length;
		int[] dist = new int[V];
		
		PriorityQueue<int[]> pq = new PriorityQueue<int[]>((o1, o2) ->  {
			return o2[1]-o1[1];
		});
		
		int[] src2 = find_min_dist(edges, 0, new int[] {V-1, P});
		int src2dst = src2[0];
		int src2p = src2[1];
		int p2dst = find_min_dist(edges, P, new int[] {V-1})[0];
		
		return src2dst == (src2p + p2dst);
	}
	
	public static int[] find_min_dist(List<int[]>[] edges, int src, int[] dst) {
		int[] dist = init_arr(edges.length, INF);
		int[] ret = new int[dst.length];
		
		PriorityQueue<int[]> pq = new PriorityQueue<int[]>((o1, o2) -> o2[1]-o1[1]);
		
		dist[src] = 0;
		Iterator<int[]> it = edges[src].iterator();
		while(it.hasNext()) {
			int next[] = it.next();
			dist[next[0]] = next[1];
			pq.add(new int[] {next[0], next[1]});
		}
		
		while(!pq.isEmpty()) {
			int[] cur = pq.poll();
			
			it = edges[cur[0]].iterator();
			while(it.hasNext()) {
				int[] next = it.next();
				if(dist[cur[0]] + next[1] < dist[next[0]]) {
					dist[next[0]] = dist[cur[0]] + next[1];
					pq.add(new int[] {next[0], next[1]});
				}
			}
		}
		
		for(int i=0; i<dst.length; i++) {
			ret[i] = dist[dst[i]];
		}
		
		return ret;
	}
	
	public static int[] init_arr(int N, int val) {
		int[] ret = new int[N];
		for(int i=0; i<N; i++)
			ret[i] = val;
		
		return ret;
	}
	
	public static List<int[]>[] init_lists(int N) {
		List<int[]>[] ret = new LinkedList[N];
		for(int i=0; i<N; i++) {
			ret[i] = new LinkedList<int[]>();
		}
		return ret;
	}
}
