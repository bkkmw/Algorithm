package boj;

import java.io.*;
import java.util.*;

public class MainB_13430 {
	
	public static long MOD = 1000000007;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream(new File("input/boj/input_13430.txt")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int K = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		
		int ans = (int)solve(K, N);
		
		System.out.println(ans);
		br.close();
	}
	
	/*
	 * S(k, n) = Prod. of (n+i) (i ~ [0~k]) / (k+1)!
	 */
	public static long solve(int K, int N) {
		
		long denom = 1;
		long numer = N;
		
		for(long i=1; i<K+1; i++) {
			numer = numer*(N+i) % MOD;
			denom = denom*(i+1) % MOD;			
		}
		
		// MODULAR inverse
		return numer * inverse(denom, MOD-2) % MOD;
	}
	
	public static long inverse(long a, long exp) {
		long ret = 1;
		
		// MODULAR EXPONENT
		while(exp > 0) {
			if((exp & 1) == 1 ) {
				ret = ret*a % MOD;
			}
			a = a * a % MOD;
			exp >>= 1;
		}
		
		return ret;
	}
}
