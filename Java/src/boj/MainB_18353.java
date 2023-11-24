package boj;

import java.util.*;
import java.io.*;

public class MainB_18353 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input/boj/input_18353.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int[] stats = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) 
			stats[i] = Integer.parseInt(st.nextToken());
		
		int ans = solve(stats);
		System.out.println(ans);
		br.close();
	}
	
	public static int solve(int[] stats) {
		int ret = 1, N = stats.length;
		int[] mem = new int[N];
		
		mem[0] = 1;
		for(int i=1; i<N; i++) {
			int largest = 1;
			for(int j=i-1; j>-1; j--) {
				if(stats[j] > stats[i] && mem[j] >= largest)
					largest = mem[j]+1;
			}
			mem[i] = largest;
			if(mem[i] > ret) ret = mem[i];
		}
		
		return N-ret;
	}
	
}
