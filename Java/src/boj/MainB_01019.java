package boj;

import java.io.*;
import java.util.*;

public class MainB_01019 {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input/boj/input_01019.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		long[] counts = solve(N);
		
		StringBuilder sb = arr_to_sb(counts);
		System.out.println(sb.toString());
		br.close();
	}
	
	public static long[] solve(int N) {
		long[] ret = new long[10];
		int digit = find_digit(N);
		boolean is_ms_digit = true;

		while(N > 0) {
			int num = N / integer_pow(10, digit-1);
			
			update_counts(ret, digit, num, is_ms_digit);
			
			ret[num] += N % integer_pow(10, digit-1) + 1;
			
			N %= integer_pow(10, digit-1);
			digit --;
			is_ms_digit = false;
		}
		
		ret[0] += digit;
		
		return ret;
	}
	
	public static void update_counts(long[] counts, int digit, int ms_num, boolean is_ms_digit) {
				
		for(int i=0; i<ms_num; i++) {
			add_counts_by_digit(counts, digit-1);
			
			if(is_ms_digit && i == 0) continue;
			
			counts[i] += integer_pow(10, digit-1);
		}
		
		if(is_ms_digit) {
			for(int i=0; i<digit-1; i++) {
				counts[0] -= integer_pow(10, i);
			}
		}
	}
	
	public static void add_counts_by_digit(long[] counts, int digit) {
		int cnt = digit * integer_pow(10, digit-1);
		
		for(int i=0; i<10; i++)
			counts[i] += cnt;
	}
	
	public static int find_digit(int N) {
		int ret = 0;
		while(N > 0) {
			ret++;
			N /= 10;
		}
		
		return ret;
	}
	
	public static int integer_pow(int base, int pow) {
		int ret = 1;
		for(int i=1; i<=pow; i++) {
			ret *= base;
		}
		
		return ret;
	}
	
	public static StringBuilder arr_to_sb(long[] arr) {
		StringBuilder ret = new StringBuilder();
		
		for(int i=0; i<arr.length; i++) {
			ret.append(arr[i]).append(" ");
		}
		
		return ret;
	}
}
