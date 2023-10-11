package boj;

import java.util.*;
import java.io.*;

public class MainB_02812 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input/boj/input_02812.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[] num = new int[N+1];
		String number = br.readLine();
		
		for(int i=0; i<N; i++)
			num[i] = atoi(number.charAt(i));
		
		num[N] = 10;
		
		StringBuilder sb = solve(num, K);
		System.out.println(sb.toString());
		br.close();
	}
	
	public static StringBuilder solve(int[] num, int K) {
		StringBuilder ret = new StringBuilder();
		int N = num.length;
		
		boolean[] exclude = new boolean[N];
		
		for(int i=0; i<N-1; i++) {
			for(int j=1; j<=K; j++) {
				if(num[i] < num[i+j]) {
					exclude[i] = true;
					K--;
					break;
				}
			}
		}
		
		append_ans(ret, num, exclude);
		
		return ret;
	}
	
	public static void append_ans(StringBuilder sb, int[] num, boolean[] exclude) {
		int N = num.length-1;
		for(int i=0; i<N; i++) {
			if(!exclude[i])
				sb.append(num[i]);
		}
	}
	
	public static int atoi(char c) {
		return c-48;
	}
}
