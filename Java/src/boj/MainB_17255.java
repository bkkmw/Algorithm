package boj;

import java.io.*;
import java.util.*;

public class MainB_17255 {
	
	static int[] TEN_POWS = new int[] {
			1, 10, 100, 1_000, 10_000, 100_000, 1_000_000, 10_000_000
	};

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input/boj/input_17255.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String N = br.readLine();
		
		int ans = solve(N);
		
		System.out.println(ans);
		br.close();
	}
	
	public static int solve(String N) {
		int ret = 0;
		int num = Integer.parseInt(N);
		int[][][] mem = init_mem(N);
		int[] counts = init_digit_counts(N);
		
		for(int i=0; i<10; i++) {
			if(counts[i] == 0) continue;
			counts[i]--;
			ret += count_ans(mem, counts, 1, N.length(), num, i);
			counts[i]++;
		}
		
		
		return ret;
	}
	
	public static int count_ans(int[][][] mem, int[] counts, int used, int len, int target_num, int num) {
		if(used == len) {
			return target_num == num ? 1 : 0;
		}
		int ret = 0;
		
		int left = num / TEN_POWS[used-1];
		int right = num % 10;
		int temp = -1;
		
		for(int i=0; i<10; i++) {
			if(counts[i] == 0) continue;
			
			if(mem[left][i][0] > 0) {
				counts[i]--;
				mem[left][i][0]--;
				
				ret += count_ans(mem, counts, used+1, len, target_num, i*TEN_POWS[used]+num);
				
				counts[i]++;
				mem[left][i][0]++;
				
				temp = i*TEN_POWS[used]+num;
			}
			
			if(mem[right][i][1] > 0) {
				if(temp == num*10+i) {
					continue;
				}
				
				counts[i]--;
				mem[right][i][1]--;
				
				ret += count_ans(mem, counts, used+1, len, target_num, num*10 + i);
				
				counts[i]++;
				mem[right][i][1]++;
			}
		}
		
		return ret;
	}
	
	public static int[] init_digit_counts(String N) {
		int[] ret = new int[10];
		int len = N.length();
		
		for(int i=0; i<len; i++) {
			ret[N.charAt(i)-48]++;
		}
		
		return ret;
	}
	
	public static int[][][] init_mem(String N) {
		int[][][] ret = new int[10][10][2];
		
		int len = N.length();
		for(int i=0; i<len; i++) {
			int digit = N.charAt(i)-48;
			if(i>0) {
				int left = N.charAt(i-1)-48;
				ret[digit][left][0]++;
			}
			if(i<len-1) {
				int right = N.charAt(i+1)-48;
				ret[digit][right][1]++;
			}
		}
		
		return ret;
	}
}
