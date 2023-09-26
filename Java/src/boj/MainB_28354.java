package boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class MainB_28354 {
	
	public static int INFT = 500000;
	
	public static Map<Integer, int[]>[] edges;
	public static int[] tomatos;
	public static int[] initial_tomato;
	public static int[][] queries;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input/boj/input_28354.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());
		
		/**
		 * Key : number of target tomato
		 * Value : int[] : ...
		 */
		edges = new Map[N+1];
		for(int i=0; i<N+1; i++) {
			edges[i] = new HashMap<Integer, int[]>();
		}
		
		tomatos = init_arr(N+1, -1);
		
		initial_tomato = new int[K];
		
		queries = new int[Q][3];
		
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			add_edges(a, b, 0);
		}
		
		st = new StringTokenizer(br.readLine(), " ");
		for(int i=0; i<K; i++) {
			int tomato = Integer.parseInt(st.nextToken());
			initial_tomato[i] = tomato;
			tomatos[tomato] = 0;
		}
		
		for(int i=0; i<Q; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int T = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			queries[i][0] = T;
			queries[i][1] = x;
			queries[i][2] = y;
		}
		
		solve();
		
		for(int i=1; i<N+1; i++) {
			sb.append(tomatos[i]).append(" ");
		}
		
		System.out.println(sb.toString());
	}
	
	public static void solve() {
		Queue<int[]> q = new LinkedList<int[]>();
		int qr_size = queries.length;
		int idx = 0;
		int time = 0;
		
		for(int i=0; i<initial_tomato.length; i++) {
			q.add(new int [] {initial_tomato[i], 0});
		}
		
		while(!(q.isEmpty() && idx >= qr_size)) {
			// proceed until next edge update
			int next_time = (idx < qr_size) ? queries[idx][0] : INFT;
			// update tomato
			while((!q.isEmpty()) && q.peek()[1] < next_time) {
				int[] poll = q.poll();
				
				Iterator<Integer> it = edges[poll[0]].keySet().iterator();
				while(it.hasNext()) {
					int target = it.next();
					int[] edge = edges[poll[0]].get(target);
					
					int t = poll[1] + 1;
					if(tomatos[target] < 0 || tomatos[target] >= t) {
						tomatos[target] = t;
						q.add(new int[] {target, t});
					}
				}
				// TODO : remove connected edges
			}
			
			// update edges
			while((idx < qr_size) && (queries[idx][0] == next_time)) {
				int T = queries[idx][0];
				int a = queries[idx][1];
				int b = queries[idx][2];
				
				if(check_needed(a, b) && (update_edge(T, a, b) == 1)) {
					int src = tomatos[b] == -1 ? a : b;
					int dst = src == a ? b : a;
					
					if(tomatos[src] < T) {
						tomatos[dst] = T + 1;
						q.add(new int[] {dst, T + 1});						
					}
					else {
						add_edges(a, b, T);
					}
				}
				
				idx ++;
			}
		}
		
	}
	
	public static boolean check_needed(int a, int b) {
		return (tomatos[a] < 0) || (tomatos[b] < 0);
	}
	
	public static int update_edge(int T, int a, int b) {
		int ret = 0;
		if(edges[a].get(b) == null && edges[b].get(a) == null) {
			// new connections
			if(tomatos[a] < 0 && tomatos[b] < 0) {
				add_edges(a, b, T);				
			}
			else {
				ret = 1;
			}
		} else {
			// disconnect
			remove_edge(a, b);
		}
		
		return ret;
	}
	
	public static void add_edges(int a, int b, int t) {
		edges[a].put(b, new int[] {t});
		edges[b].put(a, new int[] {t});
	}
	
	public static void remove_edge(int a, int b) {
		edges[a].remove(b);
		edges[b].remove(a);
	}
	
	public static int[] init_arr(int size, int val) {
		int[] ret = new int[size];
		for(int i=0; i<size; i++) {
			ret[i] = val;
		}
		return ret;
	}
}
