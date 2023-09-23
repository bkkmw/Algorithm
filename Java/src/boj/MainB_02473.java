package boj;

import java.util.*;
import java.io.*;

public class MainB_02473 {
	
	public static long INF = 3000000000L;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input/boj/input_02473.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		int al_cnt = 0;
		
		st = new StringTokenizer(br.readLine(), " ");
		for(int i=0; i<N; i++) {
			int num = Integer.parseInt(st.nextToken());
			if(num < 0) al_cnt++;
			arr[i] = num;
		}
		
		Arrays.sort(arr);
		int[] ans = solve(arr, al_cnt);
		for(int i=0; i<3; i++) {
			sb.append(ans[i]).append(" ");
		}
		System.out.println(sb.toString());
	}
	
	public static int[] solve(int[] arr, int al_cnt) {
		int[] ret = new int[3];
		int N = arr.length;
		long min = INF;
		
		for(int i=0; i<N; i++) {
			int l,r;
			if(i < al_cnt) {
				l = al_cnt;
				r = N-1;
			}
			else {
				l = 0;
				r = al_cnt-1;
			}
			
			while(l < r) {
				int sum = arr[i] + arr[l] + arr[r];
				if(Math.abs(sum) < Math.abs(min)) {
					min = sum;
					ret[0] = i;
					ret[1] = l;
					ret[2] = r;
				}
				
				if(sum<0) l++;
				else r--;
			}
		}
		
		if(al_cnt > 2) {
			long sum = arr[al_cnt-1] + arr[al_cnt-2] + arr[al_cnt-3];
			if(Math.abs(sum) < Math.abs(min)) {
				min = sum;
				ret[0] = al_cnt-3;
				ret[1] = al_cnt-2;
				ret[2] = al_cnt-1;
			}
		}
		if((N-al_cnt) > 2) {
			long sum = arr[al_cnt] + arr[al_cnt+1] + arr[al_cnt+2];
			if(Math.abs(sum) < Math.abs(min)) {
				min = sum;
				ret[0] = al_cnt;
				ret[1] = al_cnt+1;
				ret[2] = al_cnt+2;
			}
			
		}
		
		
		Arrays.sort(ret);
		return new int[] {arr[ret[0]], arr[ret[1]], arr[ret[2]]};
	}
}
