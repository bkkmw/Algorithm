package boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MainB_14002 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input/boj/input_14002.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		int[] seq = new int[N];
		
		st = new StringTokenizer(br.readLine(), " ");
		for(int i=0; i<N; i++)
			seq[i] = Integer.parseInt(st.nextToken());
		
		int ans = solve(seq, sb);
		System.out.println(ans);
		System.out.println(sb.toString());
	}
	
	public static int solve(int[] seq, StringBuilder sb) {
		int ret = 1;
		int N = seq.length;
		int[] mem = new int[N];
		int[] idx = new int[N];
		int end = 0;
		
		mem[0] = 1;
		idx[0] = 0;
		for(int i=1; i<N; i++) {
			int largest = 1;
			for(int j=i-1; j>-1; j--) {
				if(seq[j] < seq[i] && mem[j] >= largest) {
					largest = mem[j]+1;
					idx[i] = j;
				}
			}
			
			mem[i] = largest;
			if(mem[i] > ret) {
				ret = mem[i];
				end = i;
			}
		}
		
		int[] lis = find_seq(seq, idx, end, ret);
		print_seq(lis, sb);
		return ret;
	}
	
	public static int[] find_seq(int[] seq, int[] idx, int end, int len) {
		int[] ret = new int[len];
		int N = idx.length;
		int i = end;
		
		while(len > 0) {
			ret[--len] = seq[i];
			i = idx[i];
		}
		
		return ret;
	}
	
	public static void print_seq(int[] lis, StringBuilder sb) {
		int len = lis.length;
		for(int i=0; i<len; i++) {
			sb.append(lis[i]).append(" ");
		}
	}
}
