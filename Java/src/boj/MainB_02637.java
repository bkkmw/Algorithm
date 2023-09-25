package boj;

import java.util.*;
import java.io.*;

public class MainB_02637 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input/boj/input_02637.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		
		List<int[]>[] nexts = init_lists(N);
		int[] order = new int[N];
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken())-1;
			int y = Integer.parseInt(st.nextToken())-1;
			int k = Integer.parseInt(st.nextToken());
			
			nexts[y].add(new int[] {x, k});
			order[x] ++;
		}
		
		int[] ans = solve(N, nexts, order);
		print_ans(ans, sb);
		System.out.println(sb.toString());
	}
	
	public static int[] solve(int N, List<int[]>[] nexts, int[] order) {
		List<int[]> ans = new LinkedList<int[]>();
		int[][] mem = new int[N][N];
		
		Queue<Integer> q = new LinkedList<Integer>();
		
		for(int i=0; i<N; i++) {
			if(order[i] == 0) { 
				q.add(i);
				mem[i][i] = 1;
			}
		}
		
		while(!q.isEmpty()) {
			int node = q.poll();
			Iterator<int[]> it = nexts[node].iterator();
			
			while(it.hasNext()) {
				int[] next = it.next();
				copy_req(mem, node, next[0], next[1]);
				order[next[0]] --;
				
				if(order[next[0]] == 0) {
					q.add(next[0]);
				}
			}
		}
		
		return mem[N-1];
	}
	
	public static void copy_req(int[][] mem, int src, int dst, int amount) {
		int N = mem.length;
		for(int i=0; i<N; i++) {
			mem[dst][i] += mem[src][i]*amount;
		}
	}
	
	public static void print_ans(int[] ans, StringBuilder sb) {
		int N = ans.length;
		for(int i=0; i<N; i++) {
			if(ans[i] > 0) {
				sb.append(String.format("%d %d\n", i+1, ans[i]));
			}
		}
	}
	
	public static List<int[]>[] init_lists(int N) {
		List<int[]>[] ret = new LinkedList[N];
		for(int i=0; i<N; i++) {
			ret[i] = new LinkedList<int[]>();
		}
		return ret;
	}
}
