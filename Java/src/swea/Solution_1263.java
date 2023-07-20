package swea;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_1263 {
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input/swea/input_1263.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int TC = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=TC; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken());
			int[][] graph = new int[N][N];
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					int conn = Integer.parseInt(st.nextToken());
					graph[i][j] = (conn==0) ? 1000001 : conn;
				}
			}
			
			int ans = solve(graph);
			sb.append("#").append(tc).append(" ").append(ans).append("\n");
		}
		System.out.println(sb.toString());
	}
	
	static int solve(int[][] graph){
		int N = graph.length;
		
		for(int k=0; k<N; k++) {
			for(int i=0; i<N; i++) {
				if(i==k) continue;
				for(int j=0; j<N; j++) {
					if(i==j || k==j) continue;
					if(graph[i][j] > graph[i][k] + graph[k][j])
						graph[i][j] = graph[i][k] + graph[k][j];
				}
			}			
		}		
		return count_cc(graph, N);
	}
	
	static int count_cc(int[][] graph, int N) {
		int ret = Integer.MAX_VALUE;
		for(int i=0; i<N; i++) {
			int temp = 0;
			for(int j=0; j<N; j++) {
				if(i==j) continue;
				temp += graph[i][j];
			}
			if(temp < ret) ret = temp;
		}
		return ret;
	}
}
