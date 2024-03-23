package boj;

import java.io.*;
import java.util.*;

public class MainB_14719 {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input/boj/input_14719.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int H = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());
		
		int[] heights = new int[W+1];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<W; i++) 
			heights[i] = Integer.parseInt(st.nextToken());
		
		heights[W] = -1;
		int ans = solve(heights, H);
		System.out.println(ans);
		br.close();
	}
	
	public static int solve(int[] heights, int H) {
		int ret = 0, W = heights.length;
		
		for(int j=1; j<=H; j++) {
			int from = -1;
			for(int i=0; i<W; i++) {
				if(heights[i] >= j) {
					if(from > -1) {
						ret += i-from-1;
						from = i;
					} else {
						from = i;
					}
				}
			}
		}
		
		return ret;
	}
}
