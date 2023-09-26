package boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MainB_15686 {
	static int[][] hm;
	static int[][] ch;
	static int[][] check;
	static int N;
	static int M;
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input/boj/input_15686.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		hm = new int[2*N][2];
		ch = new int[14][2];
		int h_cnt = 0;
		int c_cnt = 0;
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<N; j++) {
				int temp = Integer.parseInt(st.nextToken());
				if(temp == 0) continue;
				else if (temp == 1) {
					hm[h_cnt][0] = i; 	hm[h_cnt++][1] = j;
				}
				else {
					ch[c_cnt][0] = i;	ch[c_cnt++][1] = j;
				}
			}
		}
		
		System.out.println(solve(h_cnt, c_cnt));
		
	}
	
	static int solve(int h_cnt, int c_cnt) {
		int ret = Integer.MAX_VALUE;
		int[] comb = new int[c_cnt];
		
		for(int i=c_cnt-M; i<c_cnt; i++) {
			comb[i] = 1;
		}
		do {
			int temp = calc_dist(h_cnt, c_cnt, comb);
			if(temp < ret) ret = temp;
		} while(next(comb, c_cnt));
		return ret;
	}
	
	static int calc_dist(int h_cnt, int c_cnt, int[] comb) {
		int ret = 0;
		for(int i=0; i<h_cnt; i++) {
			int min = 101;
			for(int j=0; j<c_cnt; j++) {
				if(comb[j] == 0) continue;
				int dist = Math.abs(hm[i][0] - ch[j][0]) + 
						Math.abs(hm[i][1] - ch[j][1]);
				if(dist < min) min = dist;
			}
			ret += min;
		}
		return ret;
	}
	
	static boolean next(int[] arr, int len) {
		int i = len - 1;
		while(i>0 && arr[i-1] >= arr[i]) --i;
		
		if(i==0) return false;
		
		int j= len - 1;
		while(arr[i-1]>=arr[j]) --j;
		
		swap(arr, i-1, j);
		
		int k = len - 1;
		while(i<k) swap(arr, i++, k--);
		
		return true;
	}
	
	public static void swap(int[] numbers,int i,int j) {
		int temp = numbers[i];
		numbers[i] = numbers[j];
		numbers[j] = temp;
	}
}
