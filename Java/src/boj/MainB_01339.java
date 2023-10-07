package boj;

import java.util.*;
import java.io.*;

public class MainB_01339 {
	
	public static int[] power = new int[] {
			100000000, 10000000, 1000000, 100000, 10000, 1000, 100, 10, 1 
	};

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input/boj/input_01339.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		String[] words = new String[N];
		for(int i=0; i<N; i++)
			words[i] = br.readLine();
		
		int ans = solve(words);
		System.out.println(ans);
		br.close();
	}
	
	public static int solve(String[] words) {
		int N = words.length;
		int[][] counts = new int[26][9];
		
		for(int i=0; i<N; i++) {
			int len = words[i].length();
			for(int j=0; j<len; j++) {
				int num = atoi(words[i].charAt(j));
				counts[num][9-len+j]++;
				if(counts[num][9-len+j] == 10) {
					counts[num][9-len+j] = 0;
					counts[num][9-len+j-1]++;
				}
			}
		}
		
		Arrays.sort(counts, (int[] o1, int[] o2) -> {
			for(int i=0; i<8; i++) {
				if(o1[i] == o2[i]) continue;
				else return o2[i]-o1[i];
			}
			return o2[8]-o1[8];
		});
		
		return calc_ans(counts);
	}
	
	public static int calc_ans(int[][] counts) {
		int ret = 0;
		
		for(int i=0; i<10; i++) {
			int tar = (9-i);
			for(int j=0; j<9; j++) {
				ret += (counts[i][j] * tar * power[j]);
			}
		}
		return ret;
	}
	
	public static int atoi(char c) {
		return c-65;
	}
}
