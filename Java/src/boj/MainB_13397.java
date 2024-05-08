package boj;

import java.io.*;
import java.util.*;

public class MainB_13397 {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input/boj/input_13397.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[] arr = new int[N];
		int max = 0, min = 10000;
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			max = Math.max(arr[i], max);
			min = Math.min(arr[i], min);
		}
		
		int ans = solve(arr, M, max-min);
		
		System.out.println(ans);
		br.close();
	}
	
	public static int solve(int[] arr, int M, int max_diff) {
		int l = 0, r = max_diff;
		
		while(l<=r) {
			int mid = (l+r)/2;
			if(validate(arr, M, mid))
				r = mid-1;
			else
				l = mid+1;
		}
		
		return l;
	}
	
	public static boolean validate(int[] arr, int M, int val) {
		int N = arr.length, cnt = 1;
		int min = arr[0], max = arr[0];
		
		for(int i=1; i<N; i++) {
			int temp = arr[i];
			
			min = Math.min(min, temp);
			max = Math.max(max, temp);
			
			if(max-min > val) {
				if(++cnt > M) return false;
				
				min = temp;
				max = temp;
			}
		}
		
		return cnt <= M;
	}
}

