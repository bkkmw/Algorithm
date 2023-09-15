package boj;

import java.util.*;
import java.io.*;

public class MainB_01253 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input/boj/input_01253.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		
		st = new StringTokenizer(br.readLine(), " ");
		for(int i=0; i<N; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		
		int ans = solve(arr, N);
		System.out.println(ans);
	}
	
	public static int solve(int[] arr, int N) {
		int ret = 0;
		int zeros = 0;
		
		Map<Integer, Integer> map = new HashMap<>();
		for(int i=0; i<N; i++) {
			if(arr[i] == 0)
				zeros++;
			
			for(int j=i+1; j<N; j++) {
				// add map
				int sum = arr[i]+arr[j];
				if(map.get(sum) == null)
					map.put(sum, 1);
				else 
					map.put(sum, map.get(sum)+1);
			}
		}
		
		for(int i=0; i<N; i++) {
			ret += (map.get(arr[i])) == null ? 0 :
				arr[i] == 0 ? check_zero(map.get(arr[i]), zeros) :
				(map.get(arr[i]) <= zeros && check(arr, i)) ? 0 : 1;
		}
		
		return ret;
	}
	
	public static int check_zero(int cnt, int zeros) {
		if(zeros > 2)
			return 1;
		
		if(cnt > zeros*(zeros-1))
			return 1;
		
		return 0;
	}
	
	public static boolean check(int[] arr, int idx) {
		int N = arr.length;
		
		for(int i=0; i<N; i++) {
			if(i == idx)
				continue;
			if(arr[i] == arr[idx]) {
				return false;
			}
		}		
		return true;
	}
}
