package boj;

import java.io.*;
import java.util.*;

public class MainB_22871 {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input/boj/input_22871.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int[] an = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			an[i] = Integer.parseInt(st.nextToken());
		}
		
		long ans = solve(an);
		
		System.out.println(ans);
		br.close();
	}
	
	public static long solve(int[] an) {
		int N = an.length;
		long[] mem = init_arr(N, 5000000000L);
		mem[0] = 0;
		
		for(int i=1; i<N; i++) {
			for(int j=0; j<i; j++) {
				long power = (i-j) * (1 + Math.abs(an[i] - an[j]));
				long temp = Math.max(power, mem[j]);
				if(mem[i] > temp)
					mem[i]=temp;
			}
		}
		
		return mem[N-1];
	}
	
	public static long[] init_arr(int N, long val) {
		long[] ret = new long[N];
		for(int i=0; i<N; i++)
			ret[i] = val;
		
		return ret;
	}
}
