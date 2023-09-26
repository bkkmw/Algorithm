package swea;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_5215 {
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
			
			recur(-1, 0, 0, L);
			sb.append("#").append(tc).append(" ").append(ans).append("\n");
		}
		System.out.println(sb);
	}
	
	static void recur(int idx, int score, int cal, int limit) {
		if(cal > limit) return;
		if(cal <= limit) if(score > ans) ans = score;
		if(idx == list.length-1) return;
		
		recur(idx+1, score, cal, limit);
		recur(idx+1, score+list[idx+1][0], cal+list[idx+1][1], limit);
		
		return;
	}
}
