package boj;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
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
		int time = 0;

		// dst, start, end
		List<int[]>[] wait = new LinkedList[N+1];
		// vertex, time
		Queue<int[]> modified = new LinkedList<int[]>();
		boolean[] tbm = new boolean[N+1];
		
		for(int i=0; i<N+1; i++) {
			wait[i] = new LinkedList<int[]>();
		}
		
		int size = connection.size();
		int idx = 0;
		
		while(time != INF) {
			while(idx < size && time == connection.get(idx)[2]) {
				// add new edges
				int[] conn = connection.get(idx);
				
				if(mature[conn[0]] + mature[conn[1]] >= INF) {
					// at least one is not
					add_edge(conn, mature, wait, modified, tbm);
				}
				// if both are mature, skip
				idx++;
			}
			if(idx < size)
				time = connection.get(idx)[2];
			else
				time = INF;
			// flush changes
			while((!modified.isEmpty()) && modified.peek()[1] <= time) {
				int[] poll = modified.poll();
				if(mature[poll[0]] < poll[1])
					continue;

				mature[poll[0]] = poll[1];
				
				for(int i=0; i<wait[poll[0]].size(); i++) {
					int[] edge = wait[poll[0]].get(i);
					if(edge[2] <= poll[1] || mature[edge[0]] < INF)
						// expired
						continue;
					
					int t = (poll[1] > edge[1]) ? poll[1] + 1 : edge[1] + 1;
//					int t = poll[1] + 1;
					if(!tbm[edge[0]]) {
						modified.add(new int[] {edge[0], t});
						tbm[edge[0]] = true;
					}
					remove_edge(wait[edge[0]], poll[0]);
				}
				wait[poll[0]].clear();
			}
			
		}
		
	}
	
	public static void remove_edge(List<int[]> wait, int dst) {
		int size = wait.size();
		for(int i=0; i<size; i++) {
			if(wait.get(i)[0] == dst) {
				wait.remove(i);
				return;
			}
		}
	}
	
	public static void add_edge(int[] conn, int[] mature, List<int[]>[] wait, Queue<int[]> modified, boolean[] tbm) {
		int src = mature[conn[0]] != INF ? conn[0] : conn[1];
		int dst = src == conn[0] ? conn[1] : conn[0];
		
		if(mature[src] == INF) {
			// add both
			wait[src].add(new int[] {dst, conn[2], conn[3]});
			wait[dst].add(new int[] {src, conn[2], conn[3]});
		}
		else {
			modified.add(new int[] {dst, conn[2] + 1});
			tbm[dst] = true;
		}
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