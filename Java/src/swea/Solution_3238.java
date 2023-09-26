package swea;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_3238{
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input/swea/input_3238.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int TC = Integer.parseInt(br.readLine());
		for(int tc=1; tc<TC+1; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			long N = Long.parseLong(st.nextToken());
			long R = Long.parseLong(st.nextToken());
			int P = Integer.parseInt(st.nextToken());
			
			int ans = solve(N, R, P);
			sb.append(String.format("#%d %d\n", tc, ans));
		}
		System.out.println(sb.toString());
	}
	
	static int solve(long N, long R, int P) {
		long ret = 1;
		long[] ftable = generate_table(P);
		
		while(N > 0) {
			int nmod = (int)(N % P);
			int rmod = (int)(R % P);
			if(nmod < rmod) return 0;
			ret *= ftable[nmod] % P;
			ret %= P;
			ret *= (calc_mod((ftable[rmod]*ftable[nmod-rmod])%P, P-2, P)) % P;
			ret %= P;
			N /= P; R /= P;
		}
		return (int)ret;
	}
	
	static long calc_mod(long val, int p, int P) {
		if(p==0) return 1;
		long ret = calc_mod(val, p/2, P);
		if(p%2==0) return (ret*ret) % P;
		else return (ret * ((ret*val)%P)) % P;
	}
	
	static long[] generate_table(int P) {
		long[] ret = new long[P+1];
		ret[0] = 1;
		for(int i=1; i<P+1; i++) 
			ret[i] = (ret[i-1] * i) % P;
		return ret;
	}
}
