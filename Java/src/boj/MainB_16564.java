package boj;

import java.util.*;
import java.io.*;

public class MainB_16564 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input/boj/input_16564.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[] levels = new int[N];
		int min = 1000000001;
		for(int i=0; i<N; i++) {
			levels[i] = Integer.parseInt(br.readLine());
			if(levels[i] < min)
				min = levels[i];
		}
			
		int ans = solve(levels, K, min);
		System.out.println(ans);
	}
	
	public static int solve(int[] levels, int K, int min) {
		int ret = 0;
		int N = levels.length;
		
		Arrays.sort(levels);
		ret = levels[0];
		int i = 0;
		
		for(i=0; i<N-1; i++) {
			int diff = levels[i+1] - levels[i];
			
			if((i+1)*diff < K+1) {
				K -= (i+1)*diff;
				ret = levels[i+1];
			}
			else {
				break;
			}
		}
		
		if(K > -1) {
			ret += K/(i+1);
		}
		
		return ret;
	}
}
