package boj;

import java.util.*;
import java.io.*;

public class MainB_01041 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input/boj/input_01041.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[] dice = new int[6];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<6; i++)
			dice[i] = Integer.parseInt(st.nextToken());
		
		long ans = solve(N, dice);
		System.out.println(ans);
		br.close();
	}
	
	public static long solve(int N, int[] dice) {
		long ret = 0;
		
		if(N == 1) {
			int max = 0;
			for(int i=0; i<6; i++) {
				ret += dice[i];
				if(dice[i] > max)
					max = dice[i];
			}
			return ret-max;
		}
		
		int min = 51;
		for(int i=0; i<6; i++)
			if(min > dice[i]) min = dice[i];
		
		ret += 1l * min * (N-2)*(5*N-6);
		
		min = 101;
		for(int i=0; i<6; i++) {
			for(int j=i+1; j<6; j++) {
				if(i+j == 5) continue;
				if(min > dice[i]+dice[j])
					min = dice[i]+dice[j];
			}
		}
		
		ret += 1l * min * (8*N-12);
		
		min = 151;
		for(int i=0; i<6; i++) {
			for(int j=i+1; j<6; j++) {
				if(i+j == 5) continue;
				for(int k=j+1; k<6; k++) {
					if(i+k == 5 || j+k == 5) continue;
					if(min > dice[i]+dice[j]+dice[k])
						min = dice[i]+dice[j]+dice[k];
				}
			}
		}
		
		ret += 1l * min * 4;
		
		return ret;
	}
}
