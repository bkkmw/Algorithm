package boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MainB_17404 {
	
	public static int INF = 1000000;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input/boj/input_17404.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
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
		System.out.println(ans);
	}
	
	public static int solve(int[][] costs) {
		int N = costs.length;
		int[][][] ret = new int[N][3][3];
		
		
		for(int i=0; i<3; i++) {
			ret[0][i][i] = costs[0][i];
			
			ret[1][i][i] = INF;
			ret[1][(i+1)%3][i] = costs[1][(i+1)%3] + costs[0][i];
			ret[1][(i+2)%3][i] = costs[1][(i+2)%3] + costs[0][i];
		}
		
		for(int i=2; i<N; i++) {
			for(int j=0; j<3; j++) {
				for(int k=0; k<3; k++) {
					ret[i][j][k] = costs[i][j] +
							Math.min(ret[i-1][(j+1)%3][k], ret[i-1][(j+2)%3][k]);
				}
			}
		}
		
		
		return find_ans(ret[N-1]);
	}
	
	public static int find_ans(int[][] scores) {
		
		int r = Math.min(scores[0][1], scores[0][2]);
		int g = Math.min(scores[1][0], scores[1][2]);
		int b = Math.min(scores[2][0], scores[2][1]);
		
		
		return Math.min(r, Math.min(g, b));
	}
}
