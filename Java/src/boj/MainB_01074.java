package boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MainB_01074 {
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input/boj/input_01074.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int N = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		
		int ans = solve(N, r, c);
		
		sb.append(ans);
		System.out.println(sb);
	}
	
	static int solve(int N, int r, int c) {
		int ret = 0;
		int br = 0;
		int bc = 0;
		while(N != 0) {
			int half = 1<<(N-1);
			int rr = r-br;
			int cc = c-bc;

			if(rr < half && cc < half) {
				ret += 0;
			}
			else if(rr < half && cc >= half) {
				ret += half*half;
				bc += half;
			}
			else if(rr >= half && cc < half) {
				ret += 2*half*half;
				br += half;
			}
			else {
				ret += 3*half*half;
				bc += half;
				br += half;
			}
						
			N--;
		}
		return ret;
	}
}
