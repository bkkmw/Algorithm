package boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MainB_01806 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input/boj/input_01806.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		
		int[] seq = new int[N+1];
		st = new StringTokenizer(br.readLine(), " ");
		
		seq[0] = 0;
		seq[1] = Integer.parseInt(st.nextToken());
		
		for(int i=2; i<N+1; i++) {
			seq[i] = seq[i-1] + Integer.parseInt(st.nextToken());
		}
		
		int ans = solve(seq, N, S);
		
		System.out.println(ans);
	}
	
	public static int solve(int[] seq, int N, int S) {
		int ret = N+1;
		int front = 0;
		
		for(int i=1; i<N+1; i++) {
			int sum = seq[i] - seq[front];
			while(sum >= S) {
				int len = i - front;
				if(len == 1)
					return 1;
				if(len < ret)
					ret = len;
				sum = seq[i] - seq[++front];
			}
		}
		
		return ret == N+1 ? 0 : ret;
	}
}
