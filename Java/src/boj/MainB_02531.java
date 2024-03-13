package boj;

import java.io.*;
import java.util.*;

public class MainB_02531 {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input/boj/input_02531.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int D = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		
		int[] dish = new int[N];
		for(int i=0; i<N; i++) {
			dish[i] = Integer.parseInt(br.readLine());
		}
		
		int ans = solve(dish, D, K, C);
		System.out.println(ans);
		
		br.close();
	}
	
	public static int solve(int[] dish, int D, int K, int C) {
		int N = dish.length, ret = 0;
		int[] counts = new int[D+1];
		
		int count = init_window(dish, counts, K);
		int temp = count + (counts[C] == 0 ? 1 : 0);
		ret = ret>temp? ret : temp;
		
		for(int i=1; i<N; i++) {
			if(--counts[dish[i-1]] == 0) {
				count--;
			}
			if(counts[dish[(i-1+K) % N]]++ == 0) {
				count++;
			}
			temp = count + (counts[C] == 0 ? 1 : 0);
			ret = ret>temp? ret : temp;
		}
		
		return ret;
	}
	
	public static int init_window(int[] dish, int[] counts, int K) {
		int ret = 0;
		for(int i=0; i<K; i++) {
			if(counts[dish[i]]++ == 0) ret++;
		}
		return ret;
	}
}