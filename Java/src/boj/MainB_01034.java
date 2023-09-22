package boj;

import java.util.*;
import java.io.*;

public class MainB_01034 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input/boj/input_01034.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][] status = new int[N][M+1];
		for(int i=0; i<N; i++) {
			String line = br.readLine();
			for(int j=0; j<M; j++) {
				status[i][j] = line.charAt(j) == '1' ? 1 : 0;
				status[i][M] += status[i][j];
			}
			status[i][M] = M - status[i][M];
		}
		
		int K = Integer.parseInt(br.readLine());
		int ans = solve(status, K);
		System.out.println(ans);
	}
	
	public static int solve(int[][] map, int K) {
		int ret = 0;
		int N = map.length, M = map[0].length-1;
		boolean odd = (K%2 == 1)? true : false;
		
		int[] counts = new int [N];
		
		for(int i=0; i<N; i++) {
			if(map[i][M] > K) continue;
			if(odd ^ (map[i][M]%2 == 1)) continue;
			counts[i]++;
			for(int j=i+1; j<N; j++) {
				if(map[i][M] != map[j][M]) continue;
				
				if(compare_row(map[i], map[j]))
					counts[i]++;
				
			}
			if(counts[i] > ret)
				ret = counts[i];
		}
		
		return ret;
	}
	
	public static boolean compare_row(int[] r1, int[] r2) {
		int len = r1.length-1;
		for(int i=0; i<len; i++)
			if(r1[i] != r2[i])
				return false;
		return true;
	}
}
