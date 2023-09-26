package swea;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_5643_1 {
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input/swea/input_5643.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int TC = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=TC; tc++) {
			int N = Integer.parseInt(br.readLine());
			int M = Integer.parseInt(br.readLine());
			int[][] map = new int[N][N];
			for(int i=0; i<M; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int src = Integer.parseInt(st.nextToken()) - 1;
				int dst = Integer.parseInt(st.nextToken()) - 1;
				map[src][dst] = 1;
				map[dst][src] = -1;
			}
			
			int ans = solve(map, N);
			sb.append("#").append(tc).append(" ").append(ans).append("\n");
		}
		System.out.println(sb.toString());
	}
	
	static int solve(int[][] map, int N) {
		while(true) {
			boolean modified = false;
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(map[i][j] == 0) continue;
					for(int k=0; k<N; k++) {
						if(i==k) continue;
						if(map[i][k] != 0) continue;
						if(map[i][j] == map[j][k]) {
							map[i][k] = map[j][k];
							modified = true;
						}
					}
				}
			}
			if(!modified) break;
		}
		
		return count_ans(map, N);
	}
	
	static int count_ans(int[][] map, int N) {
		int ret = N;
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(i==j) continue;
				if(map[i][j] == 0) {
					ret--; break;
				}
			}
		}
		return ret;
	}
}
