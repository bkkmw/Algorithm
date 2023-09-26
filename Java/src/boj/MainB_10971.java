package boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MainB_10971 {
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input/boj/input_10971.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int[][] W = new int[N][N];
		
		for(int i=0; i<N; i++){
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<N; j++) {
				W[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int ans = solve(W, N);
		sb.append(ans);
		
		System.out.println(sb);
		
		br.close();
	}
	
	static int solve(int[][] W, int N) {
		int ret = Integer.MAX_VALUE;
		int[] num = new int[N];
		for(int i=0; i<N; i++) {
			num[i] = i;
		}
		
		do {
			int temp = calc(W, num, N);
			if(temp < ret && temp > 0) ret = temp;
		}while(next(num));
		
		return ret;
	}
	
	static int calc(int[][] W, int[] num, int N) {
		int ret = 0;
		for(int i=0; i<N; i++) {
			int src = num[i];
			int dst = num[(i+1)%N];
			if(W[src][dst] == 0) return -1;
			ret += W[src][dst];
		}
		return ret;
	}
	
	public static boolean next(int[] numbers) {		
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
	
	public static void swap(int[] numbers,int i,int j) {
		int temp = numbers[i];
		numbers[i] = numbers[j];
		numbers[j] = temp;
	}

}
