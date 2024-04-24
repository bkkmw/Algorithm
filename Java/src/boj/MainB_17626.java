package boj;

import java.io.*;
import java.util.*;

public class MainB_17626 {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input/boj/input_17626.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int ans = solve(N);
		System.out.println(ans);
		br.close();
	}
	
	public static int solve(int N) {
		int max_int = (int)Math.sqrt(N);
		int[] squares = new int[max_int];
		int[] counts = new int[N+1];
		
		for(int i=1; i<N+1; i++) {
			counts[i] = 5;
		}
		
		for(int i=1; i<=max_int; i++) {
			squares[i-1] = i*i;
			counts[i*i] = 1;
		}
		
		for(int i=1; i<4; i++) {
			for(int j=1; j<N+1; j++) {
				if(counts[j] == 0) continue;
				
				for(int k=0; k<max_int; k++) {
					if(j + squares[k] > N) break;
					counts[j+squares[k]] = Math.min(counts[j+squares[k]], counts[j]+1);
				}
			}
		}
		return counts[N];
	}
}
