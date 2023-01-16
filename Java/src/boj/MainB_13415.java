package boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class MainB_13415 {

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input/boj/input_13415.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		int[] seq = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for(int i=0; i<N; i++) 
			seq[i] = Integer.parseInt(st.nextToken());
		
		int K = Integer.parseInt(br.readLine());
		int[][] set = new int[K][2];
		
		for(int i=0; i<K; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			set[i][0] = Integer.parseInt(st.nextToken()) - 1;
			set[i][1] = Integer.parseInt(st.nextToken()) - 1;
		}
		
		seq = solve(seq, set, N, K);
		
		print_ans(seq, N, sb);
		System.out.println(sb.toString());
		br.close();
	}
	
	static int[] solve(int[] seq, int[][] set, int N, int K) {
		int[][] stack = find_valid_sets(set, K);
		
		seq = play_sets(seq, stack, N, K);
		return seq;
	}
	
	static int[][] find_valid_sets(int[][] set, int K){
		int[][] ret = new int[K*2][2];
		int top = -1;
		int max = 0;
		
		for(int i=0; i<2*K; i++) {
			
			int cur = set[i/2][i%2];
			
			if(cur >= max) {
				// clear stack & push cur
				top = -1;
				ret[++top][0] = cur;
				ret[top][1] = i%2;
				max = cur;
				continue;
			}
			while(top > -1 && ret[top][0] < cur) {
				top--;
			}
			ret[++top][0] = cur;
			ret[top][1] = i%2;
		}
		ret[2*K-1][0] = top;
		return ret;
	}
	
	static int[] play_sets(int[] seq, int[][] stack, int N, int K) {
		int top = stack[2*K-1][0];
		for(int i=0; i<=top; i++) {
			seq = sort(seq, stack[i][0]+1, stack[i][1]);
		}
		return seq;
	}
	
	static int[] sort(int[] seq, int dst, int order) {
		if(order == 0) {
			Arrays.sort(seq, 0, dst);
		}
		else {
			Integer[] seq_I = Arrays.stream(seq).boxed().toArray(Integer[]::new);
			Arrays.sort(seq_I, 0, dst, Collections.reverseOrder());
			seq = Arrays.stream(seq_I).mapToInt(i->i).toArray();
		}
		return seq;
	}
	
	static void print_ans(int[] seq, int N, StringBuilder sb) {
		for(int i=0; i<N; i++) {
			sb.append(seq[i]).append(" ");
		}
	}
}
