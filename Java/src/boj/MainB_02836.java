package boj;

import java.util.*;
import java.io.*;

public class MainB_02836 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input/boj/input_02836.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][] client = new int[N][2];
		int idx = 0;
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int src = Integer.parseInt(st.nextToken());
			int dst = Integer.parseInt(st.nextToken());
			
			if(dst < src) {
				client[idx][0] = dst;
				client[idx++][1] = src;
			}
		}
		
		long ans = solve(Arrays.copyOfRange(client, 0, idx), M);
		System.out.println(ans);
		
	}
	
	public static long solve(int[][] client, int M) {
		long ret = M;
		int len = client.length;
		
		Arrays.sort(client, (int[] o1, int[] o2) -> o1[0]-o2[0]);
		
		int left = client[0][0];
		int right = client[0][1];
		
		for(int i=1; i<len; i++) {
			if(client[i][0] <= right) {
				right = (right > client[i][1])? right : client[i][1];
			}
			else {
				ret += 2*(right-left);
				left = client[i][0];
				right = client[i][1];
			}
		}
		
		ret += 2*(right-left);
		return ret;
	}
}
