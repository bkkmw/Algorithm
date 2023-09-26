package boj;

import java.util.*;
import java.io.*;

public class MainB_02252 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input/boj/input_02252.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[] prereq = new int[N];
		List<Integer>[] orders = init_lists(N);
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken())-1;
			int b = Integer.parseInt(st.nextToken())-1;
			
			orders[a].add(b);
			prereq[b]++;
		}
		
		int[] ans = solve(orders, prereq);
		print_ans(ans, sb);
		System.out.println(sb.toString());
		
	}
	
	public static int[] solve(List<Integer>[] orders, int[] prereq) {
		int N = prereq.length;
		int[] ret = new int[N];
		int idx = 0;
		
		Queue<Integer> q = new LinkedList<>();
		
		for(int i=0; i<N; i++) {
			if(prereq[i] == 0)
				q.add(i);
		}
		
		while(!q.isEmpty()) {
			int node = q.poll();
			ret[idx++] = node+1;
			
			Iterator<Integer> it = orders[node].iterator();
			while(it.hasNext()) {
				int next = it.next();
				if(--prereq[next] == 0) {
					q.add(next);
				}
			}
		}
		
		return ret;
	}
	
	public static List<Integer>[] init_lists(int size) {
		List<Integer>[] ret = new LinkedList[size];
		for(int i=0; i<size; i++) 
			ret[i] = new LinkedList<Integer>();
		
		return ret;
	}
	
	public static void print_ans(int[] ans, StringBuilder sb) {
		int N = ans.length;
		for(int i=0; i<N; i++)
			sb.append(ans[i]).append(" ");
		
	}
}
