package boj;

import java.io.*;
import java.util.*;

public class MainB_01725 {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input/boj/input_01725.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[] histogram = new int[N];
		
		for(int i=0; i<N; i++) {
			histogram[i] = Integer.parseInt(br.readLine());
		}
		
		int ans = solve(histogram);
		System.out.println(ans);
	}
	
	public static int solve(int[] arr) {
		int ret = 0, N = arr.length;
		Stack<int[]> st = new Stack<int[]>();
		
		for(int i=0; i<N; i++) {
			int cur = arr[i];
			if(st.isEmpty()) st.add(new int[] {arr[i], i});
			else if(st.peek()[0] < cur) {
				st.add(new int[] {arr[i], i});
			} else if(st.peek()[0] == cur) {
				continue;
			} else {
				int from_idx = i;
				while((!st.isEmpty()) && st.peek()[0] > cur) {
					int[] pop = st.pop();
					int cnt = pop[0] * (i-pop[1]);
					from_idx = pop[1];
					if(cnt > ret) ret = cnt;					
				}
				st.add(new int[] {arr[i], from_idx});
			}
		}
		
		while(!st.isEmpty()) {
			int[] pop = st.pop();
			int cnt = pop[0] * (N-pop[1]);
			if(cnt > ret) ret = cnt;
		}
		
		return ret;
	}
}
