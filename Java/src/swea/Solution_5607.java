package swea;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_5607 {
	static final int MOD = 1234567891;
	static final int MAX = 10000000;
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input/swea/input_5607.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int TC = Integer.parseInt(br.readLine());
		long[] fact_mod = generate_fact_mod_table(MAX, MOD);
		for(int tc=1; tc<TC+1; tc++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int R = Integer.parseInt(st.nextToken());
			
			long ans = solve(N, R, fact_mod);
			sb.append(String.format("#%d %d\n", tc, ans));
		}
		System.out.println(sb.toString());
		br.close();
	}
	
	static long solve(int N, int R, long[] table) {
		long ret = 0;
		long nmod = table[N];
		long rmod = calc_mod((table[R] * table[N-R])%MOD, MOD-2);
		ret = (nmod*rmod)%MOD;
		return ret;
	}
	
	static long calc_mod(long val, int p) {
		if(p==0) return 1;
		long ret = calc_mod(val, p/2);
		if(p%2==0) return (ret*ret) % MOD;
		else return (ret * ((ret*val)%MOD)) % MOD;
	}
	
	static long[] generate_fact_mod_table(int N, int mod) {
		long[] ret = new long[N+1];
		ret[0] = 1;
		for(int i=1; i<N+1; i++) ret[i] = (ret[i-1] * i) % MOD;
		return ret;
	}
}
