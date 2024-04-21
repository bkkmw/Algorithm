package boj;

import java.io.*;
import java.util.*;

public class MainB_02961 {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input/boj/input_02961.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int[][] flavors = new int[N][2];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<2; j++) {
				flavors[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int ans = solve(flavors);
		
		System.out.println(ans);
		
		br.close();
	}
	
	public static int solve(int[][] flavors) {
		int N = flavors.length, ret = 0;
		boolean[] used = new boolean[N];
		ret = recur(flavors, used, 0, N, 1000000001);
		
		return ret;
	}
	
	public static int recur(int[][] flavors, boolean[] used, int idx, int len, int min) {
		if(idx == len) {
			int diff = get_flavor_diff(flavors, used);
			return Math.min(min, diff);
		}
		
		used[idx] = true;
		int ret = recur(flavors, used, idx+1, len, min);
		used[idx] = false;
		ret = Math.min(ret, recur(flavors, used, idx+1, len, min));
		
		return ret;
	}
	
	public static int get_flavor_diff(int[][] flavors, boolean[] used) {
		int N = flavors.length;
		int sour = 1, bitter = 0;
		for(int i=0; i<N; i++) {
			if(used[i]) {
				sour *= flavors[i][0];
				bitter += flavors[i][1];
			}
		}
		if(sour == 1 && bitter == 0) return 1000000001;
		return Math.abs(sour - bitter);
	}
}
