package boj;

import java.io.*;
import java.util.*;

public class MainB_01068 {

	public static final int ROOT = -1;
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input/boj/input_01068.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int[] parent = init_arr(N, -3);
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			parent[i] =Integer.parseInt(st.nextToken()); 
		}
		
		int ans = solve(parent, Integer.parseInt(br.readLine()));
		System.out.println(ans);
	}
	
	public static int solve(int[] parent, int target) {
		int ret = 0, N = parent.length;
		List<Integer>[] child = par_to_child(parent);
		boolean[] disconnected = new boolean[N];
		Queue<Integer> q = new LinkedList<Integer>();
		
		if(parent[target] != ROOT) {
			int size = child[parent[target]].size();
			for(int i=0; i<size; i++) {
				if(child[parent[target]].get(i) == target) {
					child[parent[target]].remove(i);
					break;
				}
			}			
		}
		
		disconnected[target] = true;
		q.add(target);
		
		while(!q.isEmpty()) {
			int cur = q.poll();
			
			int len = child[cur].size();
			
			for(int i=0; i<len; i++) {
				int next = child[cur].get(i);
				disconnected[next] = true;
				q.add(next);
				
				child[cur].remove(i--);
				len--;
			}
		}
		
		return count_leaf(child, disconnected);
	}
	
	public static int count_leaf(List<Integer>[] child, boolean[] disconnected) {
		int ret = 0, N = child.length;
		for(int i=0; i<N; i++) {
			if(disconnected[i]) continue;
			if(child[i].size() == 0) ret++;
		}
		
		return ret;
	}
	
	public static List<Integer>[] par_to_child(int[] parent) {
		int N = parent.length;
		List<Integer>[] ret = init_lists(N);
		
		for(int i=0; i<N; i++) {
			if(parent[i] == ROOT) continue;
			ret[parent[i]].add(i);
		}
				
		return ret;
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
		for(int i=0; i<N; i++)
			ret[i] = val;
		
		return ret;
	}
}
