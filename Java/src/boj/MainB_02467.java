package boj;

import java.io.*;
import java.util.*;

public class MainB_02467 {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input/boj/input_02467.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		
		int[] ans = solve(arr);
		
		sb.append(ans[0]).append(" ").append(ans[1]);
		System.out.println(sb.toString());
		br.close();
	}
	
	public static int[] solve(int[] arr) {
		int N = arr.length, min = 2*1000000000+1;
		int[] ret = new int[2];
		Arrays.sort(arr);
		
		int lidx = 0, ridx = N-1;
		
		while(lidx<ridx) {
			int sum = arr[lidx] + arr[ridx];
			if(sum == 0) return new int[] {arr[lidx], arr[ridx]};
			
			if(Math.abs(min) > Math.abs(sum)) {
				min = sum;
				ret[0] = arr[lidx];
				ret[1] = arr[ridx];
			}
			
			if(sum < 0) {
				lidx++;
			}else {
				ridx--;
			}
		}
		
		return ret;
	}
}
