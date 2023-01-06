package boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MainB_22251 {

	static boolean[][] seg_digit = new boolean[][] {
		{true, true, true, false, true, true, true},
		{false, false, false, false, false, true, true},
		{false, true, true, true, true, true, false},
		{false, false, true, true, true, true, true},
		{true, false, false, true, false, true, true},
		{true, false, true, true, true, false, true},
		{true, true, true, true, true, false, true},
		{false, false, true, false, false, true, true},
		{true, true, true, true, true, true, true},
		{true, false, true, true, true, true, true}
	};
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input/boj/input_22251.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int P = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken());
		
		int ans = solve(N, K, P, X);
		
		System.out.println(ans);
		
		br.close();
	}
	
	static int solve(int N, int K, int P, int X) {
		int ret = 0;
		int[][] toggle_cnts = get_toggle_cnts();
		int[] fnd = convert_to_digit(X);
		int[] limit = convert_to_digit(N);
		
		ret = change_digits(toggle_cnts, fnd, limit, 6-K, P, 0, X);
		
		return ret;
	}
	
	static int change_digits(int[][] toggle_cnts, int[] fnd, int[] limit, int idx, int P, int tcnt, int X) {
		int ret = 0;
		
		if(idx==6) {
			int n = convert_to_num(fnd,idx-1);
			return (n == 0 || n == X)? 0 : 1;
		}
		
		int fnd_src = fnd[idx];
		for(int i=0; i<10; i++) {
			if(tcnt + toggle_cnts[fnd_src][i] > P) continue;
			fnd[idx] = i;
			if(convert_to_num(fnd, idx) > convert_to_num(limit, idx)) continue;
			ret += change_digits(toggle_cnts, fnd, limit, idx+1, P, tcnt + toggle_cnts[fnd_src][i], X);
		}
		fnd[idx] = fnd_src;
		return ret;
	}
	
	
	static int[][] get_toggle_cnts(){
		int[][] ret = new int[10][10];
		for(int i=0; i<10; i++) {
			for(int j=i+1; j<10; j++) {
				int cnt = 0;
				for(int k=0; k<7; k++) {
					cnt += (seg_digit[i][k] != seg_digit[j][k])? 1 : 0;
				}
				ret[i][j] = cnt;
				ret[j][i] = cnt;
			}
		}
		return ret;
	}
	
	static int[] convert_to_digit(int num) {
		int[] ret = new int[6];
		for(int i=5; i>=0; i--) {
			ret[i] = num%10;
			num /= 10;
		}
		return ret;
	}
	
	static int convert_to_num(int[] digits, int idx) {
		int ret = 0;
		for(int i=0; i<=idx; i++) {
			ret *= 10;
			ret += digits[i];
		}
		return ret;
	}
	
}
