package boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MainB_11404 {
	
	private static final int INF_COST = 100000 * 99 + 1;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input/boj/input_11404.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		
		int[][] map = new int[N][N];
		init_map(map, INF_COST);
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int src = Integer.parseInt(st.nextToken());
			int dst = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
			if(map[src-1][dst-1] > cost) map[src-1][dst-1] = cost;
		}
		
		solve(N, map);
		print_ans(N, map, sb);
		System.out.println(sb.toString());
	}
	
	public static void solve(int N, int[][] map) {
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(i==j) continue;
				for(int k=0; k<N; k++) {
					if(i==k || j==k) continue;
					// j i k
					map[j][k] = (map[j][k] > map[j][i] + map[i][k]) ? map[j][i] + map[i][k] : map[j][k]; 
				}
			}
		}
	}
	
	public static void print_ans(int N, int[][] map, StringBuilder sb) {
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				sb.append(map[i][j] == INF_COST ? 0 : map[i][j]);
				if(j != N-1) sb.append(" ");
			}
			if(i != N-1) sb.append("\n");
		}
	}
	
	public static void init_map(int[][] map, int val) {
		int N = map.length;
		for(int i=0; i<N; i++)
			for(int j=0; j<N; j++)
				map[i][j] = val;
	}
}
