package boj;

import java.util.*;
import java.io.*;

public class MainB_02295 {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input/boj/input_02295.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[] nums = new int[N];
		
		for(int i=0; i<N; i++) {
			nums[i] = Integer.parseInt(br.readLine());
		}
		
		int ans = solve(nums);
		System.out.println(ans);
		
		br.close();
	}
	
	public static int solve(int[] nums) {
		int ret = 0;
		int N = nums.length;
		HashMap<Long, Integer> map = new HashMap<Long, Integer>();
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				long sum = 0L + nums[i] + nums[j];
				map.put(sum, 1);
//				System.out.printf("%d\n", sum);
			}
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				long diff = nums[i] > nums[j] ? nums[i]-nums[j] : nums[j]-nums[i];
//				System.out.printf("%d %d\n", diff, map.get(diff));
				if(map.get(diff) != null) {
					ret = Math.max(ret, Math.max(nums[i], nums[j]));
//					System.out.printf("%d %d %d - %d\n", ret, nums[i], nums[j], ret);
				}
			}
		}
		
		return ret;
	}
}
