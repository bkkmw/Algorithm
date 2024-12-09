package boj;

import java.io.*;
import java.util.*;

public class MainB_14221 {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream(new File("input/boj/input_14221.txt")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		List<int[]>[] edges = new LinkedList[N];
		for(int i=0; i<N; i++) {
			edges[i] = new LinkedList<int[]>();
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int src = Integer.parseInt(st.nextToken()) - 1;
			int dst = Integer.parseInt(st.nextToken()) - 1;
			int dist = Integer.parseInt(st.nextToken());
			
			edges[src].add(new int[] {dst, dist});
			edges[dst].add(new int[] {src, dist});
		}
		
		st = new StringTokenizer(br.readLine());
		int P = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());
		
		int[] houses = new int[P], stores = new int[Q];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<P; i++) {
			houses[i] = Integer.parseInt(st.nextToken()) - 1;
		}
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<Q; i++) {
			stores[i] = Integer.parseInt(st.nextToken()) - 1;
		}
		
		int ans = solve(edges, houses, stores) + 1;
		
		System.out.println(ans);
		
		br.close();
	}
	
	public static int solve(List<int[]>[] edges, int[] houses, int[] stores) {
		int ret = 0, N = edges.length;
		
		PriorityQueue<int[]> pq = new PriorityQueue<int[]>((o1, o2) -> o1[1]-o2[1]);
		int[] dists = init_arr(N, Integer.MAX_VALUE);
		
		
		for(int i=0; i<stores.length; i++) {
			dists[stores[i]] = 0;
			pq.add(new int[] {stores[i], 0});
			
		}
		
		while(!pq.isEmpty()) {
			int[] poll = pq.poll();
			Iterator<int[]> it = edges[poll[0]].iterator();
			
			while(it.hasNext()) {
				int[] temp = it.next();
				if(poll[1] + temp[1] < dists[temp[0]]) {
					pq.add(new int[] {temp[0], poll[1]+temp[1]});
					dists[temp[0]] = poll[1] + temp[1];
				}
			}
		}			
		
		Arrays.sort(houses);
				
		return find_min_house(dists, houses);
	}
	
	public static int find_min_house(int[] dists, int[] houses) {
		int ret = -1, min = Integer.MAX_VALUE;
		for(int i=0; i<houses.length; i++) {
			int idx = houses[i];
			if(dists[idx] != 0 && dists[idx] < min) {
				ret = idx;
				min = dists[idx];
			}
		}
		
		return ret;
	}
	
	public static int[] init_arr(int size, int val) {
		int[] ret = new int[size];
		for(int i=0; i<size; i++) {
			ret[i] = val;
		}
		
		return ret;
	}
}
