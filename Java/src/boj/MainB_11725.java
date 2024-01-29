package boj;

import java.io.*;
import java.util.*;

public class MainB_11725 {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input/boj/input_11725.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		List<Integer>[] edges = init_lists(N);
		
		for(int i=0; i<N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int src = Integer.parseInt(st.nextToken())-1;
			int dst = Integer.parseInt(st.nextToken())-1;
			
			edges[src].add(dst);
			edges[dst].add(src);
		}
		
		solve(edges, sb);
		System.out.println(sb.toString());
	}
	
	public static void solve(List<Integer>[] edges, StringBuilder sb) {
		int N = edges.length;
		int[] parent = init_arr(N, -2);
		
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(0);
		parent[0] = -1;
		
		while(!q.isEmpty()) {
			int cur = q.poll();
			
			Iterator<Integer> it = edges[cur].iterator();
			
			while(it.hasNext()) {
				int next = it.next();
				if(parent[next] > -2) continue;
				
				q.add(next);
				parent[next] = cur;
			}
		}
		
		print_ans(parent, sb);
	}
	
	public static void print_ans(int[] parent, StringBuilder sb) {
		int N = parent.length;
		for(int i=1; i<N; i++) {
			sb.append(parent[i]+1).append("\n");
		}
	}
	
	public static List<Integer>[] init_lists(int N) {
		List<Integer>[] ret = new LinkedList[N];
		for(int i=0; i<N; i++) {
			ret[i] = new LinkedList<Integer>();
		}
		
		return ret;
	}
	
	public static int[] init_arr(int N, int val) {
		int[] ret = new int[N];
		for(int i=0; i<N; i++) {
			ret[i] = val;
		}
		
		return ret;
	}
}
