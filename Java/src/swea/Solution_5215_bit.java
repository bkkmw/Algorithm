package swea;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_5215_bit {
	static int[][] list = null;
	static int ans = 0;
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input/swea/input_5215.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=T; tc++) {
			ans = 0;
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken());
			int L = Integer.parseInt(st.nextToken());
			list = new int[N][2];
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				list[i][0] = Integer.parseInt(st.nextToken());
				list[i][1] = Integer.parseInt(st.nextToken());
			}
			
			ans = solve(list, L);
			sb.append("#").append(tc).append(" ").append(ans).append("\n");
		}
		System.out.println(sb);
	}
	
	static int solve(int[][] list, int L) {
		int ret = -1;
		int N = list.length;
		for(int i=0; i<(1<<N); i++) {
			int cal = 0, score = 0;
			for(int j=0; j<N; j++) {
				if((i & (1<<j)) != 0) {
					score += list[j][0];
					cal += list[j][1];
				}
				if(cal > L) break;
			}
			if(cal <= L && score > ret) ret = score;
		}
		
		return ret;
	}
}
