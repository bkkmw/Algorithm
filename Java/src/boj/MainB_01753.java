package boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

public class MainB_01753 {
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input/boj/input_01753.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(), " ");
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		
		List<int[]>[] list = new ArrayList[V];
		
		for(int i=0; i<V; i++) list[i] = new ArrayList<int[]>();
		
		
		int K = Integer.parseInt(br.readLine())-1;
		
		for(int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int src = Integer.parseInt(st.nextToken())-1;
			int dst = Integer.parseInt(st.nextToken())-1;
			int weight = Integer.parseInt(st.nextToken());
			list[src].add(new int[] {dst, weight});
		}
		
		solve(list, V, E, K, sb);
		System.out.println(sb);
		br.close();
	}
	
	static void solve(List<int[]>[] list, int V, int E, int K, StringBuilder sb) {
		int[] dist = new int[V];
		int[] check = new int[V];
		int current = K;
		for(int i=0; i<V; i++) dist[i] = Integer.MAX_VALUE;
		dist[current] = 0;
		
		for(int c=0; c<V; c++) {
			check[current] = 1;
			Iterator<int[]> iter = list[current].iterator();
			
			while(iter.hasNext()) {
				int[] temp = iter.next();
				int t_dist = dist[current] + temp[1];
				if(t_dist < dist[temp[0]] && t_dist >= 0) {
					dist[temp[0]] = t_dist;
				}
			}
			current = get_next(dist, check);
		}
		
		for(int i=0; i<V; i++) {
			sb.append((dist[i]==Integer.MAX_VALUE)? "INF" : dist[i]).append("\n");
		}
		
	}
	
	static int get_next(int[] dist, int[] check) {
		int ret = -1;
		int min = Integer.MAX_VALUE;
		for(int i=0; i<dist.length; i++) {
			if(check[i] == 1) continue;
			if(dist[i] <= min) {
				min = dist[i];
				ret = i;
			}
		}
		
		return ret;
	}
}
