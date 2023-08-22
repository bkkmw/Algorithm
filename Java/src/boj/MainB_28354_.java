package boj;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class MainB_28354_ {
	
	public static final int INF = 1000000;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream(new File("input/boj/input_28354.txt")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());
		
		int[] mature = init_arr(N);
		List<int[]> conn = new ArrayList<int[]>();
		Map<String, int[]> map = new HashMap<String, int[]>();
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int src = Integer.parseInt(st.nextToken());
			int dst = Integer.parseInt(st.nextToken());
			int[] temp = new int[] {src, dst, 0, INF};
			
			map.put(int2str(src, dst), temp);
			conn.add(temp);
		}
		
		st = new StringTokenizer(br.readLine(), " ");
		for(int i=0; i<K; i++) {
			int tomato = Integer.parseInt(st.nextToken());
			mature[tomato] = 0;
			
		}
		
		for(int i=0; i<Q; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int t = Integer.parseInt(st.nextToken());
			int src = Integer.parseInt(st.nextToken());
			int dst = Integer.parseInt(st.nextToken());
			
			String key = int2str(src, dst);
			int[] temp = map.get(key);
			
			if(temp == null) {
				temp = new int[] {src, dst, t, INF};
				map.put(key, temp);
				conn.add(temp);
			}
			else {
				temp[3] = t;
				map.remove(key);
			}
		}
						
		solve(mature, conn);
		print_ans(mature, new StringBuilder());
	}
	
	public static void solve(int[] mature, List<int[]> connection) {
		int N = mature.length - 1;
		
		List<int[]>[] next = new List[N+1];
		for(int i=0; i<N+1; i++) 
			next[i] = new ArrayList<int[]>();
		
		connection.forEach((con) -> {
			if(mature[con[0]] + mature[con[1]] < 2*INF) {
				// at least one is mature
				int target = mature[con[0]] < mature[con[1]] ? con[1] : mature[con[0]] == mature[con[1]] ? 0 : con[0];
				// 5%
//				if(target == 0) return;
				if(target > 0) {
					int src = target == con[0] ? con[1] : con[0];
					
					// update status of target
					int time = mature[src] > con[2] ? mature[src]+1 : con[2]+1;
					if(time <= con[3] && mature[target] > time)
						update_status(target, time, mature, next, con[2]);					
				}
			}
			// 3%
//			else {
				// for future usage
				next[con[0]].add(new int[] {con[1], con[2], con[3]});
				next[con[1]].add(new int[] {con[0], con[2], con[3]});
//			}
		});
	}
	
	public static void update_status(int target, int t, int[] mature, List<int[]>[] next, int src_t) {
		
		mature[target] = t;
		for(int i=0; i<next[target].size(); i++) {
			int[] temp = next[target].get(i);
			int time = mature[target] > temp[1] ? mature[target] + 1 : temp[1] +1;
			
			if(temp[2] < src_t) {
				next[target].remove(i--);
				continue;
			}
			
			if(mature[temp[0]] > time) {
				// update
				if(time <= temp[2]) {
					update_status(temp[0], time, mature, next, src_t);
				}
			}
		}
		// 2%
//		next[target].clear();
		return;
	}
	
	public static int[] init_arr(int N) {
		int[] ret = new int[N+1];
		for(int i=0; i<N+1; i++) {
			ret[i] = INF;
		}
		return ret;
	}
	
	public static String int2str(int src, int dst) {
		return String.format("%6d%6d", src, dst).toString();
	}
	
	public static void print_ans(int[] mature, StringBuilder sb) {
		int N = mature.length;
		for(int i=1; i<N; i++) {
			sb.append(mature[i] == INF ? -1 : mature[i]).append(" ");
		}
		System.out.println(sb.toString());
	}
}