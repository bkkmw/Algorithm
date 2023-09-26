package boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MainB_01149 {
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input/boj/input_01149.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int[][] costs = new int[N][3];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			costs[i][0] = Integer.parseInt(st.nextToken());
			costs[i][1] = Integer.parseInt(st.nextToken());
			costs[i][2] = Integer.parseInt(st.nextToken());
		}
		
		int ans = solve(costs);
		sb.append(ans);
		System.out.println(sb.toString());
	}
	
	static int solve(int[][] costs) {
		int N = costs.length;
		int[][] ret = new int[N][3];
		for(int i=0; i<3; i++) {
			ret[0][i] = costs[0][i];
		}
		for(int i=1; i<N; i++) {
			for(int j=0; j<3; j++) {
				ret[i][j] = costs[i][j] + 
						Math.min(ret[i-1][(j+1)%3], ret[i-1][(j+2)%3]);
			}
		}
		
		return Math.min(ret[N-1][0], Math.min(ret[N-1][1], ret[N-1][2]));
	}
}
