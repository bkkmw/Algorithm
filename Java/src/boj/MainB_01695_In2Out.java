package boj;

import java.util.*;
import java.io.*;

public class MainB_01695_In2Out {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input/boj/input_01695.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int[] seq = new int[N];
		st = new StringTokenizer(br.readLine());
		
		for(int i=0; i<N; i++)
			seq[i] = Integer.parseInt(st.nextToken());
		
		int ans = solve(seq);
		System.out.println(ans);
		
		br.close();
	}
	
	public static int solve(int[] seq) {
		int N = seq.length;
		int[][] mem = init_map(N, -1);
		
		return count(seq, mem, 0, N-1);
	}
	
	public static int count(int[] seq, int[][] mem, int lidx, int ridx) {
		if(lidx >= ridx) return 0;
		if(mem[lidx][ridx] > -1)
			return mem[lidx][ridx];
		
		if(seq[lidx] == seq[ridx]) 
			return count(seq, mem, lidx+1, ridx-1);
		
		int min = count(seq, mem, lidx+1, ridx) + 1;
		mem[lidx][ridx] = Math.min(min, count(seq, mem, lidx, ridx-1)+1); 
		return mem[lidx][ridx];
	}
	
	public static int[][] init_map(int N, int val) {
		int[][] ret = new int[N][N];
		for(int i=0; i<N; i++)
			for(int j=0; j<N; j++)
				ret[i][j] = val;
		return ret;
	}
}
