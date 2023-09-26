package boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MainB_14003 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input/boj/input_14003.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		
		int[] seq = new int[N];
		st = new StringTokenizer(br.readLine(), " ");
		
		for(int i=0; i<N; i++) {
			seq[i] = Integer.parseInt(st.nextToken());
		}
		
		int ans = solve(seq, sb);
		System.out.println(ans);
		System.out.println(sb.toString());
	}
	
	public static int solve(int[] seq, StringBuilder sb) {
		int N = seq.length;
		int[] lis = new int[N];
		int[] idxs = new int[N];
		int size = 0;

		lis[size++] = seq[0];
		for(int i=1; i<N; i++) {
			size = update_lis(lis, idxs, seq[i], i, size);
		}

		int[] ans = print_seq(seq, idxs, size);
		for(int i=0; i<size; i++) {
			sb.append(ans[i]).append(" ");
		}
		return size;
	}

	public static int update_lis(int[] lis, int[] idxs, int num, int idx, int size) {

		if(lis[size-1] < num) {
			idxs[idx] = size;
			lis[size++] = num;
			return size;
		}

		int left = 0;
		int right = size-1;

		while(left < right) {
			int mid = (left+right) >> 1;
			if(lis[mid] < num)
				left = mid+1;
			else
				right = mid;
		}

		lis[left] = num;
		idxs[idx] = left;

		return size;
	}
	
	public static int[] print_seq(int[] seq, int[] idx, int size) {
		int len = idx.length;
		int[] ret = new int[size];
		int i = len - 1;
		
		while(size > 0) {
			if(idx[i] == size-1) {
				ret[--size] = seq[i];
			}
			i--;				
		}
		
		return ret;
	}
}
