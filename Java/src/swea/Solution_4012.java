package swea;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_4012 {
	static int N;
	static int[][] srcScore;
	static int[][] score;
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input/swea/input_4012.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int TC = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=TC; tc++) {
			N = Integer.parseInt(br.readLine());
			srcScore = new int[N][N];
			score = new int[N][N];
			for(int i=0; i<N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				for(int j=0; j<N; j++) {
					srcScore[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			update_map();
			
			int ret = solve();
			sb.append("#").append(tc).append(" ").append(ret).append("\n");	
			
		}
		System.out.println(sb);
		br.close();
	}
	
	static int solve() {
		int ret = Integer.MAX_VALUE;
		int[] check = new int[N];
		for(int i=0; i<N/2; i++) {
			check[i] = -1;
		}
		for(int i=N/2; i<N; i++) {
			check[i] = 1;
		}
		
		do {
			int temp = calc(check);
			if(temp < ret) ret = temp;
		}while(next(check));
		return ret;
	}
	
	static int calc(int[] check) {
		int ret = 0;
		for(int i=0; i<N; i++) {
			for(int j=i+1; j<N; j++) {
				if(check[i] == check[j])
					ret += score[i][j] * check[i];
			}
		}
		return Math.abs(ret);
	}
	
	static boolean next(int[] numbers) { 		
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
	
	static void swap(int[] numbers,int i,int j) {
		int temp = numbers[i];
		numbers[i] = numbers[j];
		numbers[j] = temp;
	}
	
	static void update_map() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				score[i][j] = srcScore[i][j] + srcScore[j][i];
			}
		}
	}
}
