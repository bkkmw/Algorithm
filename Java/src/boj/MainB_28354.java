package boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class MainB_28354 {
	
	public static int INFT = 500000;

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
		
		List<int[]>[] edge = new List[N+1];
		int[][] conn = new int[M+Q][4];
		int idx = 0;
		Map<String, Integer> status = new HashMap<String, Integer>(); 
		
		for(int i=0; i<N+1; i++) {
			edge[i] = new LinkedList<int[]>();
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int src = Integer.parseInt(st.nextToken());
			int dst = Integer.parseInt(st.nextToken());
			
			conn[idx] = new int[] {src, dst, 0, INFT};
			edge[src].add(conn[idx]);
			edge[dst].add(conn[idx]);
			
			status.put(int2str(src, dst), idx++);
		}
		
		int[] mature = init_arr(N+1, INFT);
		String init = br.readLine();
		st = new StringTokenizer(init, " ");
		for(int i=0; i<K; i++) {
			int tom = Integer.parseInt(st.nextToken());
			mature[tom] = 0;
		}
		
		for(int i=0; i<Q; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int T = Integer.parseInt(st.nextToken());
			int src = Integer.parseInt(st.nextToken());
			int dst = Integer.parseInt(st.nextToken());
			
			Integer conn_idx = status.get(int2str(src, dst));
			if(conn_idx == null) {
				// add
				conn[idx] = new int[] {src, dst, T, INFT};
				edge[src].add(conn[idx]);
				edge[dst].add(conn[idx]);
				
				status.put(int2str(src, dst), idx++);
			}
			else {
				conn[conn_idx][3] = T;
				status.remove(int2str(src, dst));
			}
		}
		
		solve(edge, conn, idx, mature, init, K);
		print_ans(mature, sb);
	}
	
	public static void solve(List<int[]>[] edge, int[][] conn, int idx, int[] mature, String init, int K) {
		Queue<int[]> q = new LinkedList<>();
		int N = edge.length;
		
		StringTokenizer st = new StringTokenizer(init, " ");
		for(int i=0; i<K; i++) {
				q.add(new int[] {Integer.parseInt(st.nextToken()), 0});
		}
		
		while(!q.isEmpty()) {
			int[] poll = q.poll();
			int src = poll[0];
			int t = mature[src];
			if(t < poll[1]) continue;
			
			for(int i=0; i<edge[src].size(); i++) {
				int[] temp = edge[src].get(i);
				if(temp[3] <= t)
					continue; // expired
				int dst = (src == temp[0]) ? temp[1] : temp[0];
				int time = (temp[2] < t) ? t+1 : temp[2] +1;
				
				if(mature[dst] > time) {
					mature[dst] = time;
					q.add(new int[] {dst, time});
				}
			}
		}
	}
	
	public static void print_ans(int[] mature, StringBuilder sb) {
		int N = mature.length;
		for(int i=1; i<N; i++) {
			sb.append(mature[i] == INFT ? -1 : mature[i]).append(" ");
		}
		System.out.println(sb.toString());
	}
	
	public static String int2str(int src, int dst) {
		return String.format("%4d%4d", src, dst).toString();
	}
	
	public static int[] init_arr(int N, int val) {
		int[] ret = new int[N];
		for(int i=0; i<N; i++)
			ret[i] = val;
		return ret;
	}
}
