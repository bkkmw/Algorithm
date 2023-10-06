package boj;

import java.util.*;
import java.io.*;

public class MainB_01414 {
	
	public static int INF = 53;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input/boj/input_01414.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[][] dist = new int[N][N];
		int len = 0;
		
		for(int i=0; i<N; i++) {
			String line = br.readLine();
			for(int j=0; j<N; j++) {
				dist[i][j] = atoi(line.charAt(j));
				len += dist[i][j]==INF? 0 : dist[i][j];
			}
		}
		
		int ans = solve(dist, len);
		System.out.println(ans);
	}
	
	public static int solve(int[][] dist, int len) {
		int ret = 0, N = dist.length, connected = 0;
		boolean[] check = new boolean[N];
		PriorityQueue<int[]> pq = new PriorityQueue<>((int[] o1, int[] o2) -> o1[1]-o2[1]);
		
		make_symmetric(dist);
		
		for(int i=1; i<N; i++) {
			if(dist[0][i] == INF) continue;
			pq.add(new int[] {i, dist[0][i]});
		}
		connected++;
		check[0] = true;
		
		while((!pq.isEmpty()) && connected < N) {
			int[] poll = pq.poll();
			if(check[poll[0]])
				continue;
			
			check[poll[0]] = true;
			connected++;
			ret += poll[1];
			
			for(int i=0; i<N; i++) {
				if(i == poll[0] || check[i] || dist[poll[0]][i] == INF) continue;
				pq.add(new int[] {i, dist[poll[0]][i]});
			}
			
		}
		
		return connected == N? (len-ret) : -1;
	}
	
	public static void make_symmetric(int[][] mat) {
		int N = mat.length;
		for(int i=0; i<N; i++) {
			for(int j=i+1; j<N; j++) {
				if(mat[i][j] > mat[j][i]) mat[i][j] = mat[j][i];
				else mat[j][i] = mat[i][j];
			}
		}
	}
	
	public static int atoi(char c) {
		if(c==48) return INF;
		else if(c > 90) return c-97+1;
		else return c-65+1+26;
	}
	
}
