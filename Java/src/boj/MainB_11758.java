package boj;

import java.io.*;
import java.util.*;

public class MainB_11758 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input/boj/input_11758.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int[][] points = new int[3][2];
		
		for(int i=0; i<3; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<2; j++) {
				points[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int ans = solve(points);
		System.out.println(ans);
		
		br.close();
	}
	
	public static int solve(int[][] points) {
		int ccw = (points[1][0]-points[0][0])*(points[2][1]-points[0][1])
				- (points[2][0]-points[0][0])*(points[1][1]-points[0][1]);
		
		return ccw == 0 ? 0 : ccw<0 ? -1 : 1;
	}
}
