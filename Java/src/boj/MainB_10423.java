package boj;

import java.util.*;
import java.io.*;

public class MainB_10423 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input/boj/input_10423.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		int[] power = new int[K];
		for(int i=0; i<K; i++)
			power[i] = Integer.parseInt(st.nextToken())-1;
		
		int[][] edges = new int[M][3];
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			edges[i][0] = Integer.parseInt(st.nextToken())-1;
			edges[i][1] = Integer.parseInt(st.nextToken())-1;
			edges[i][2] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(edges, (int[] o1, int[] o2) -> o1[2]-o2[2]);
		
		int ans = solve(N, power, edges);
		System.out.println(ans);
		
		br.close();
	}
	
	public static int solve(int N, int[] power, int[][] edges) {
		int M = edges.length, K = power.length;
		int ret = 0;
		
		int[] par = init_arr(N, -1);
		
		for(int i=0; i<K; i++)
			par[power[i]] = -2;
		
		int cnt = K;
		
		for(int i=0; i<M; i++) {
			if(cnt == N) break;
			int[] edge = edges[i];
			
			if(update_par(par, edge[0], edge[1])) {
				ret += edge[2];
				cnt++;
			}
		}
		
		return ret;
	}
	
	public static boolean update_par(int[] par, int a, int b) {
		int p_a = a, p_b = b;
		
		while(par[p_a] > -1) {
			p_a = par[p_a];
		}
		while(par[p_b] > -1) {
			p_b = par[p_b];
		}
		
		if(p_a == p_b) return false;
		if(par[p_a] == -2 && par[p_b] == -2) return false;
		
		if(par[p_a] == -2) {
			par[p_b] = a;
		}
		else {
			par[p_a] = b;
		}
		
		return true;
	}
	
	public static int[] init_arr(int len, int val) {
		int[] ret = new int[len];
		for(int i=0; i<len; i++)
			ret[i] = val;
		
		return ret;
	}
}
