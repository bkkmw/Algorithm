package swea;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_3234_bit {
	static int[] table = new int[] {0, 1, 2, 6, 24, 120, 720, 5040, 40320, 362880};
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
		ret = compare(list, 0, 0, 0, 0, sum, 0);
		
		return ret;
	}
	
	static int compare(int[] list, int level, int visit, int left, int right, int rem, int cnt) {
		if(left < right) return cnt;
		if(level == list.length) return cnt+1;	
		if(left >= right + rem) return cnt + (1 << (list.length - level)) * table[list.length - level];
		
		for(int i=0; i<list.length; i++) {
			if((visit & (1<<i)) == 0) {
				cnt = compare(list, level+1, (visit | (1<<i)), left+list[i], right, rem-list[i], cnt);
				cnt = compare(list, level+1, (visit | (1<<i)), left, right+list[i], rem-list[i], cnt);	
			}
		}
				
		return cnt;
	}
		
}
