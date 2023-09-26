package boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class MainB_03040 {
	static int[] check = new int[9];
	static int[] hat = new int[9];
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input/boj/input_03040.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		for(int i=0; i<9; i++) {
			hat[i] = Integer.parseInt(br.readLine());
		}
		solve(0, 0, 0, sb);
		System.out.println(sb);
	}
	
	static void solve(int idx, int sum, int cnt, StringBuilder sb) {
		if(sum == 100 && cnt == 7) {
			for(int i=0; i<9; i++) {
				if(check[i] == 1) sb.append(hat[i]).append("\n");
			}
			return;
		}
		if(idx == hat.length) return;
		
		check[idx] = 0;
		solve(idx+1, sum, cnt, sb);
		
		check[idx] = 1;
		solve(idx+1, sum + hat[idx], cnt+1, sb);
		
		check[idx] = 0;
	}
}
