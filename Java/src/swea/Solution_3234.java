package swea;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_3234 {
	public static void main(String[] args) throws Exception{
		int N;
		int[] list;
		int ans;
		int sum;
		
		System.setIn(new FileInputStream("input/swea/input_3234.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int TC = Integer.parseInt(br.readLine());
		
		for(int tc=1 ; tc<=TC; tc++) {
			N = Integer.parseInt(br.readLine());
			list = new int[N];
			sum = 0;
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for(int i=0; i<N; i++) {
				list[i] = Integer.parseInt(st.nextToken());
				sum += list[i];
			}
			Arrays.sort(list);
			
			ans = solve(list, N, sum);
			
			sb.append("#").append(tc).append(" ");
			sb.append(ans).append("\n");
		}
		System.out.println(sb);
	}
	
	static int solve(int[] list, int N, int sum) {
		int ret = 0;
		do {
			int rem = sum - list[0];
			ret += compare(list, 0, list[0], 0, rem, 0);
		}while(next(list));
		
		return ret;
	}
	
	static int compare(int[] list, int idx, int left, int right, int rem, int cnt) {
		if(left < right) return cnt;
		if(idx == list.length - 1) return cnt+1;	
		if(left >= right + rem) return cnt +(1 << list.length -1 - idx);
		
		cnt = compare(list, idx+1, left+list[idx+1], right, rem-list[idx+1], cnt);
		cnt = compare(list, idx+1, left, right+list[idx+1], rem-list[idx+1], cnt);
		
		return cnt;
	}
	
	static boolean next(int[] numbers) {
		int N = numbers.length;
		int i = N-1;
		while(i>0 && numbers[i-1]>=numbers[i]) --i;
		if(i==0) return false; 
		int j= N-1;
		while(numbers[i-1]>=numbers[j]) --j;
		swap(numbers, i-1, j);
		int k = N-1;
		while(i<k) swap(numbers, i++, k--);
		return true;
	}
	
	static void swap(int[] numbers,int i,int j) {
		int temp = numbers[i];
		numbers[i] = numbers[j];
		numbers[j] = temp;
	}
	
}
