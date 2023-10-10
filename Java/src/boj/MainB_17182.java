package boj;

import java.util.*;
import java.io.*;

public class MainB_17182 {
	
	public static int INF = 1001;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input/boj/input_17182.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[][] dist = new int[N][N];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				int t = Integer.parseInt(st.nextToken());
				dist[i][j] = i==j? INF : t;
			}
		}
		
		int ans = solve(dist, K);
		System.out.println(ans);
		br.close();
	}
	
	public static int solve(int[][] dist, int K) {
		int N = dist.length;
		int ret = 0;
		
		int[][] visited = new int[N][N];
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(i==j) continue;
				visited[i][j] = (1<<i) | (1<<j);
			}
		}
		
		for(int k=0; k<N; k++) {
			for(int i=0; i<N; i++) {
				if(k==i) continue;
				for(int j=0; j<N; j++) {
					if(k==j || i==j) continue;
					if(dist[i][j] > dist[i][k] + dist[k][j]) {
						dist[i][j] = dist[i][k] + dist[k][j];
						visited[i][j] = visited[i][k] | visited[k][j];
					}
				}
			}
		}
		
		int end = 0;
		for(int i=0; i<N; i++)
			end = end | (1<<i);
		
		ret = find_ans(dist, visited, K, 1<<K, end, 10001, 0);
		
		return ret;
	}
	
	public static int find_ans(int[][] dist, int[][] visited, int src, int bit, int end, int ans, int score) {
		if(bit == end) {
			return ans>score? score : ans;
		}
		if(score > ans) return ans;
		
		for(int i=0; i<dist.length; i++) {
			if(((bit>>i) & 1) == 1) continue;
			ans = find_ans(dist, visited, i, visited[src][i] | bit, end, ans, score+dist[src][i]);
		}
		
		return ans;
	}
}
