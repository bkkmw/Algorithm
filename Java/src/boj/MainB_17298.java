package boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MainB_17298 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input/boj/input_17298.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int[] numbers = new int[N+1];
		
		st = new StringTokenizer(br.readLine(), " ");
		for(int i=0; i<N; i++)
			numbers[i] = Integer.parseInt(st.nextToken());
		
		int[] ans = solve(N, numbers);
		print_ans(ans, numbers);
	}
	
	public static int[] solve(int N, int[] numbers) {
		int[] ret = new int[N];
		numbers[N] = 1000000001;
		ret[N-1] = N;
		
		for(int i=N-2; i>=0; i--) {
			if(numbers[i] < numbers[i+1])
				ret[i] = i+1;
			else {
				int idx = ret[i+1];
				while(numbers[i] >= numbers[idx])
					idx = ret[idx];
				ret[i] = idx;
			}
		}
		return ret;
	}
	
	public static void print_ans(int[] ans, int[] numbers) {
		int N = ans.length;
		StringBuilder sb = new StringBuilder();
		
		for(int i=0; i<N; i++) {
			sb.append(ans[i] == N ? -1 : numbers[ans[i]]);
			sb.append(" ");
		}
		
		System.out.println(sb.toString());
	}
	
}
