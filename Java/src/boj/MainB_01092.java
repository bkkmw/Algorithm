package boj;

import java.util.*;
import java.io.*;

public class MainB_01092 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input/boj/input_01092.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int[] cranes = new int[N+1];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++)
			cranes[i] = Integer.parseInt(st.nextToken());
		
		int M = Integer.parseInt(br.readLine());
		int[] boxes = new int[M];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<M; i++)
			boxes[i] = Integer.parseInt(st.nextToken());
		
		int ans = solve(cranes, boxes);
		System.out.println(ans);
		
		br.close();
	}
	
	public static int solve(int[] cranes, int[] boxes) {
		int N = cranes.length, M = boxes.length;
		
		cranes[N-1] = 1000001;
		Arrays.sort(cranes);
		Arrays.sort(boxes);
		
		int c_idx = 0, c_cnt = 0, b_idx = 0, b_cnt = 0;
		int[][] assign = new int[N][2];
		for(int i=0; i<N-1; i++) {
			if(cranes[i] == cranes[i+1]) {
				c_cnt++;
				continue;
			}
			
			b_cnt = 0;
			while(b_idx < M && boxes[b_idx] <= cranes[i]) {
				b_idx++;
				b_cnt++;
			}
			assign[c_idx][0] = c_cnt+1;
			assign[c_idx++][1] = b_cnt;
			
			c_cnt = 0;
		}
		
		if(b_idx < M) return -1;
		
		return calc_ans(Arrays.copyOfRange(assign, 0, c_idx), boxes);
	}
	
	public static int calc_ans(int[][] cranes, int[] boxes) {
		int ret = 0;
		int N = cranes.length;
		
		int c_cnt = 0, b_cnt = 0;
		for(int i=N-1; i>-1; i--) {
			c_cnt += cranes[i][0];
			b_cnt += cranes[i][1];
			
			int days = b_cnt/c_cnt + (b_cnt%c_cnt==0? 0 : 1);
			if(days > ret) ret = days;
		}
		
		return ret;
	}
}
