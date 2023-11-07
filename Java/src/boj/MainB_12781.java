package boj;

import java.io.*;
import java.util.*;

public class MainB_12781 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input/boj/input_12781.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int[][] points = new int[4][2];
		
		for(int i=0; i<4; i++) {
			for(int j=0; j<2; j++) {
				points[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int ans = solve(points);
		System.out.println(ans);
		
		br.close();
	}
	
	public static int solve(int[][] points) {
		int ret = 0;
		
		if(ccw(points[0], points[1], points[2]) * ccw(points[0], points[1], points[3]) < 0)
			ret = 1;
		
		return ret;
	}
	
	public static int ccw(int[] a, int[] b, int[] c) {
		int ret = (b[0]-a[0])*(c[1]-a[1]) - (c[0]-a[0])*(b[1]-a[1]);
		return ret == 0 ? 0 : ret<0 ? -1 : 1;
	}
}
