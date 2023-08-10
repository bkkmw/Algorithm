package boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MainB_09205 {
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input/boj/input_09205.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int TC = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=TC; tc++) {
			int N = Integer.parseInt(br.readLine());
			int[][] pos = new int[N+2][2];
			for(int i=0; i<N+2; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				pos[i][0] = Integer.parseInt(st.nextToken());
				pos[i][1] = Integer.parseInt(st.nextToken());
			}
			
			boolean ans = solve(pos, N+2);
			sb.append((ans) ? "happy" : "sad").append("\n");
		}
		System.out.println(sb.toString());
	}
	
	static boolean solve(int[][] pos, int N) {
		boolean[][] ret = new boolean[N][N];
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(i==j) ret[i][j] = false;
				int dist = Math.abs(pos[i][0] - pos[j][0]);
				dist += Math.abs(pos[i][1] - pos[j][1]);
				if(dist <= 1000) ret[i][j] = true;
			}
		}
		
		for(int k=0; k<N; k++) {
			for(int i=0; i<N; i++) {
				if(k==i) continue;
				for(int j=0; j<N; j++) {
					if(k==j || i==j) continue;
					if(ret[i][k] == true && ret[k][j] == true)
						ret[i][j] = true;
				}
			}
		}
		
		
		return ret[0][N-1];
	}
}
