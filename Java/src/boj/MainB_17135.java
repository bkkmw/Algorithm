package boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MainB_17135 {
	public static void main(String[] args) throws Exception{
		int N;
		int M;
		int D;
		int[][] map;
		int f_row;
		int ans;
		
		System.setIn(new FileInputStream("input/boj/input_17135.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		f_row = -1;
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(f_row == -1 && map[i][j] == 1) f_row = i;
			}
		}
		ans = solve(map, f_row, D);
		System.out.println(ans);
	}
	
	static int solve(int[][] src_map, int f_row, int D) {
		int ret = 0;
		int M = src_map[0].length;
		int[] arc = new int[M];
		for(int i=M-3; i<M; i++) {
			arc[i] = 1;
		}
		
		do {
			int[][] map = copy_A(src_map);
			int temp = play(map, arc, f_row, D);
			if(temp > ret) ret = temp;
		}while(next(arc));
		
		return ret;
		
	}
	
	static int play(int[][] map, int[] arc, int f_row, int D) {
		int ret = 0;
		int N = map.length;
		
		while(N > f_row) {
			ret += round(map, arc, N, D);
			N--;
		}
		return ret;
	}
	
	static int round(int[][] map, int[] arc, int c_row, int D) {
		int ret = 0;
		int[][] enemy = new int[3][2];
		int enemy_cnt = 0;
		for(int a=0; a<arc.length; a++) {
			if(arc[a] == 1) {
				enemy[enemy_cnt++] = search(map, c_row, a, D);
			}
		}
		ret += kill(map, enemy); 
		return ret;
	}
	
	static int kill(int[][] map, int[][] enemy) {
		int ret = 0;
		for(int i=0; i<3; i++) {
			if(enemy[i][0] == -1) continue;
			if(map[enemy[i][0]][enemy[i][1]] == 1) {
				ret++;
				map[enemy[i][0]][enemy[i][1]] = 0;
			}
		}
		return ret;
	}
	
	static int[] search(int[][] map, int c_row, int a, int D) {
		int[] ret = new int[] {-1, map[0].length};
		int min = D+1;
		for(int i=1; i<=D; i++) {
			if(i > min) return ret;
			if(c_row-i < 0) return ret;
			int from = Math.max(0,  a-(D-i));
			int to = Math.min(map[0].length-1, a+(D-i));
			for(int j=from; j<=to; j++) {
				if((map[c_row-i][j] == 1) && 
						(Math.abs(i) + Math.abs(a - j) <= min)) {
					if((Math.abs(i) + Math.abs(a - j) == min) && ret[1] < j) continue;
					ret[0] = c_row -i;
					ret[1] = j;
					min = Math.abs(i) + Math.abs(a - j);
				}
			}
		}
		return ret;
	}
	
	static int[][] copy_A(int[][] src) {
		int[][] ret = new int[src.length][src[0].length];
		for(int i=0; i<src.length; i++) {
			for(int j=0; j<src[0].length; j++) {
				ret[i][j] = src[i][j];
			}
		}
		return ret;
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
	
}
