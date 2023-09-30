package boj;

import java.util.*;
import java.io.*;

public class MainB_01027 {
	
	public static float INF = 2000000000;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input/boj/input_01027.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int[] height = new int[N];
		st = new StringTokenizer(br.readLine(), " ");
		for(int i=0; i<N; i++)
			height[i] = Integer.parseInt(st.nextToken());
		
		int ans = solve(height);
		System.out.println(ans);
	}
	
	public static int solve(int[] height) {
		int N = height.length;
		int ret = 0;
		
		for(int i=0; i<N; i++) {
			int l = i-1, r = i+1;
			int cnt = 0;
			float max = -1;
			float min = -INF;
			
			while(l > -1) {
				float slope = (float)(height[l]-height[i]) / (i-l);
				if(slope >= 0 && slope > max) {
					cnt++;
					max = slope;
					min = 0;
				}
				else if (slope < 0 && slope > min) {
					cnt ++;
					min = slope;
				}
				l--;
			}
			
			max = -1;
			min = -INF;
			
			while(r < N) {
				float slope = (float)(height[r]-height[i]) / (r-i);
				if(slope >= 0 && slope > max) {
					cnt++;
					max = slope;
					min = 0;
				}
				else if (slope < 0 && slope > min) {
					cnt ++;
					min = slope;
				}
				r++;
			}
			
			if(cnt > ret) ret = cnt;
		}
		
		return ret;
	}
}
