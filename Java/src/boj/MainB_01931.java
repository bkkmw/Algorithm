package boj;

import java.io.*;
import java.util.*;

public class MainB_01931 {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input/boj/input_01931.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int[][] meetings = new int[N][2];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<2; j++) {
				meetings[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int ans = solve(meetings);
		System.out.println(ans);
	}
	
	public static int solve(int[][] meetings) {
		int ret = 0, N = meetings.length;
		
		Arrays.sort(meetings, (o1, o2) -> {
			return o1[1] == o2[1] ? o1[0]-o2[0] : o1[1]-o2[1]; 
		});
		
		ret++;
		int ends = meetings[0][1];
		
		for(int i=1; i<N; i++) {
			if(meetings[i][0] >= ends) {
				ret ++;
				ends = meetings[i][1];
			}
		}

		return ret;
	}
}
