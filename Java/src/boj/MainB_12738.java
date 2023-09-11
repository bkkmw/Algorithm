package boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MainB_12738 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input/boj/input_12738.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int[] seq = new int[N];

		st = new StringTokenizer(br.readLine(), " ");
		for(int i=0; i<N; i++)
			seq[i] = Integer.parseInt(st.nextToken());

		int ans = solve(seq);
		System.out.println(ans);
	}
	
	public static int solve(int[] seq) {
		int N = seq.length;
		int[] lis = new int[N];
		int size = 0;
		
		lis[size++] = seq[0];
		for(int i=1; i<N; i++) {
			size = update_lis(lis, seq[i], size);
		}
		
		return size;
	}
	
	public static int update_lis(int[] lis, int num, int size) {
		
		if(lis[size-1] < num) {
			lis[size++] = num;
			return size;
		}
		
		int left = 0;
		int right = size-1;
		
		while(left < right) {
			int idx = (left+right) >> 1;
			if(lis[idx] < num)
				left = idx+1;
			else
				right = idx;
		}
		
		lis[left] = num; 
		
		return size;
	}
}
