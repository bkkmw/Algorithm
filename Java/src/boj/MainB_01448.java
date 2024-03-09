package boj;

import java.io.*;
import java.util.*;

public class MainB_01448 {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input/boj/input_01448.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[] straw = new int[N];
		
		for(int i=0; i<N; i++) {
			straw[i] = Integer.parseInt(br.readLine());
		}
		
		int ans = solve(straw);
		System.out.println(ans);
		br.close();
	}
	
	public static int solve(int[] straw) {
		int ret = -1, N = straw.length;
		
		Arrays.sort(straw);
		
		for(int i=N-1; i>1; i--) {
			int hypo = straw[i];
			int max = straw[i-1] + straw[i-2];
			
			if(hypo < max) return hypo+max;
		}
		
		return ret;
	}
}
