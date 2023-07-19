package boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class MainB_01912 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input/boj/input_01912.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[] series = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine().concat(" "));
		for(int i=0; i<N; i++)
			series[i] = Integer.parseInt(st.nextToken());
		
		int ans = solve(N, series);
		System.out.println(ans);
	}
	
	public static int solve(int N, int[] series) {
		int[] max = Arrays.copyOf(series, N);
		
		for(int i=0; i<N; i++) {
			int[] result = proceed(N, series, i);
			max[i] = result[0];
			i = result[1];
		}
		return find_max(N, max);
	}
	
	public static int find_max(int N, int[] max) {
		int ret = -1001;
		for(int i=0; i<N; i++) {
			ret = (max[i] > ret)? max[i] : ret;
		}
		return ret;		
	}
	
	public static int[] proceed(int N, int[] series, int start) {
		if(series[start] < 0)
			return new int[] {series[start], start};
		int sum = series[start];
		int max = sum;
		int i;
		
		for(i = start+1; i<N; i++) {
			if(sum + series[i] < 0) {
				break;
			}
			sum += series[i];
			max = (sum > max)? sum : max;
		}
		
		return new int[] {max, i};
	}
	
}
