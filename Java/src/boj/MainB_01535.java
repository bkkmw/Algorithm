package boj;

import java.io.*;
import java.util.*;

public class MainB_01535 {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input/boj/input_01535.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int[][] arr = new int[N][2];
		
		for(int i=0; i<2; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				arr[j][i] = Integer.parseInt(st.nextToken());
			}
		}
		
		int ans = solve(arr);
		
		System.out.println(ans);
	}
	
	public static int solve(int[][] arr) {
		int N = arr.length;
		int[][] mem = new int[N+2][100];
		
		Arrays.sort(arr, (o1, o2) -> {
			return o1[0] - o2[0];
		});
		
		for(int i=1; i<N+1; i++) {
			for(int j=0; j<arr[i-1][0]; j++) {
				mem[i][j] = mem[i-1][j];
			}
			for(int j=arr[i-1][0]; j<100; j++) {
				int cnt = mem[i-1][j-arr[i-1][0]] + arr[i-1][1];
				mem[i][j] = Math.max(mem[i][j], cnt);
				mem[i+1][j] = mem[i][j];
			}
		}
		
		return mem[N][99];
	}
}
