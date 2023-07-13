package boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class MainB_06198 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input/boj/input_06198.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[] heights = new int[N+1];
		
		for(int i=0; i<N; i++) 
			heights[i] = Integer.parseInt(br.readLine());
		
		heights[N] = 1000000001;
		long ans = solve(heights);
		System.out.println(ans);
	}
	
	public static long solve(int[] heights) {
		int N = heights.length-1;
		int[] view = new int[N+1];
		
		view[N-1] = N;
		for(int i=N-2; i>=0; i--) {
			if(heights[i] <= heights[i+1])
				view[i] = i+1;
			else {
				int idx = view[i+1];
				while((heights[i] > heights[idx]))
					idx = view[idx];
				view[i] = idx;
			}
			
		}	
		
		return calc(view);
	}
	
	public static long calc(int[] view) {
		long ret = 0;
		int N = view.length - 1;
		for(int i=0; i<N; i++) {
			ret += (view[i] - i);
		}
		return ret - N;
	}
}
