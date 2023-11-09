package boj;

import java.io.*;
import java.util.*;

public class MainB_01948 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input/boj/input_01948.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		List<int[]>[] roads = init_arr_lists(N);
		List<int[]>[] reverse = init_arr_lists(N);
		
		int[] pre = new int[N];
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int src = Integer.parseInt(st.nextToken())-1;
			int dst = Integer.parseInt(st.nextToken())-1;
			int cost = Integer.parseInt(st.nextToken());
			
			roads[src].add(new int[] {dst, cost});
			reverse[dst].add(new int[] {src, cost, i});
			pre[dst] += 1;
		}
		
		st = new StringTokenizer(br.readLine());
		int src = Integer.parseInt(st.nextToken())-1;
		int dst = Integer.parseInt(st.nextToken())-1;
		
		int[] ans = solve(roads, reverse, pre, src, dst, N, M);
		System.out.printf("%d\n%d", ans[0], ans[1]);
		
		br.close();
	}
	
	public static int[] solve(List<int[]>[] roads, List<int[]>[] reverse, int[] pre, int src, int dst, int N, int M) {
		int[] dists = new int[N];
		Queue<Integer> q = new LinkedList<Integer>();
		
		q.add(src);
		find_max_dist(roads, pre, dists, q);

		q.add(dst);
		int cnt = count_critical_roads(reverse, dists, q, M);
		
		return new int[] {dists[dst], cnt};
	}
	
	public static int count_critical_roads(List<int[]>[] reverse, int[] dists, Queue<Integer> q, int M) {
		int ret = 0;
		int N = reverse.length;
		boolean[] checked_r = new boolean[M];
		boolean[] checked_c = new boolean[N];
		
		while(!q.isEmpty()) {
			int city = q.poll();
			
			Iterator<int[]> it = reverse[city].iterator();
			while(it.hasNext()) {
				int[] prev = it.next();
				
				if(dists[city]-prev[1] == dists[prev[0]]) {
					if(!checked_r[prev[2]]) {
						checked_r[prev[2]] = true;
						ret ++;						
					}
					
					if(!checked_c[prev[0]]) {
						checked_c[prev[0]] = true;
						q.add(prev[0]);
					}
				}
			}
		}
		
		return ret;
	}
	
	public static void find_max_dist(List<int[]>[] roads, int[] pre, int[] dists, Queue<Integer> q) {
		while(!q.isEmpty()) {
			int city = q.poll();
			
			Iterator<int[]> it = roads[city].iterator();
			while(it.hasNext()) {
				int[] next = it.next();
				int dist = dists[city] + next[1];
				
				if(dists[next[0]] < dist) {
					dists[next[0]] = dist;
				}
				
				if(--pre[next[0]] == 0) {
					q.add(next[0]);
				}
			}
		}
		
	}
	
	public static List<int[]>[] init_arr_lists(int N) {
		List<int[]>[] ret = new LinkedList[N];
		for(int i=0; i<N; i++)
			ret[i] = new LinkedList<int[]>();
		
		return ret;
	}
}
