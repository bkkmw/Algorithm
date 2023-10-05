package boj;

import java.util.*;
import java.io.*;

public class MainB_01036 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input/boj/input_01036.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		String[] numbers = new String[N];
		int max_len = 0;
		
		for(int i=0; i<N; i++) {
			numbers[i] = br.readLine();
			if(numbers[i].length() > max_len)
				max_len = numbers[i].length();
		}
		
		int K = Integer.parseInt(br.readLine());
		
		solve(numbers, max_len, K, sb);
		System.out.println(sb.toString());
		br.close();
	}
	
	public static void solve(String[] numbers, int max_len, int K, StringBuilder sb) {
		int N = numbers.length;
		int[][] counts = new int[max_len][36];
		boolean[] modified = new boolean[36];
		
		for(int i=0; i<N; i++) {
			int len = numbers[i].length();
			for(int j=0; j<len; j++) {
				int num = atoi(numbers[i].charAt(j));
				counts[max_len-len+j][num]++;
			}
		}
		
		String[][] affects = calc_affect(counts);
		Arrays.sort(affects, (String[] o1, String[] o2) -> o2[1].compareTo(o1[1]));
		
		for(int i=0; i<K; i++) {
			modified[atoi(affects[i][0].charAt(0))] = true;
		}
		
		print_ans(counts, modified, sb);
		
	}
	
	public static String[][] calc_affect(int[][] counts) {
		String[][] ret = new String[36][2];
		int len = counts.length;
		
		for(int i=0; i<36; i++) {
			StringBuilder sb = new StringBuilder();
			int carry = 0;
			for(int j=len-1; j>-1; j--) {
				int temp = counts[j][i]*(35-i) + carry;
				
				carry = temp/36;
				sb.append(itoa(temp%36));
			}
			sb.append(itoa(carry));
			ret[i][0] = itoa(i)+"";
			ret[i][1] = sb.reverse().toString();
		}
		
		return ret;
	}
	
	
	public static void print_ans(int[][] counts, boolean[] modified, StringBuilder sb) {
		int len = counts.length;
		int carry = 0;
		
		for(int i=len-1; i>-1; i--) {
			int sum = carry;
			for(int j=0; j<36; j++) 
				sum += counts[i][j] * ((modified[j])? 35 : j);
			
			carry = sum/36;
			sb.append(itoa(sum%36));
		}
		sb.append(carry==0 ? "" : carry<36? itoa(carry) : itoa(carry%36)+""+itoa(carry/36));
		sb.reverse();
	}
	
	public static char itoa(int i) {
		if(i < 10) return (char) ('0'+(char)i);
		else return (char)('A' +(char)(i-10));
	}
	
	public static int atoi(char c) {
		if(c < 60) return c-48;
		else return c-55;
	}
}
