package boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class MainB_01992 {
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input/boj/input_01992.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		int[][] img = new int[N][N]; 
		for(int i=0; i<N; i++) {
			String line = br.readLine();
			for(int j=0; j<N; j++) {
				img[i][j] = (int)line.charAt(j) - 48;
			}
		}
		solve(img, N, 0, 0, sb);
		System.out.println(sb);
	}
	
	static void solve(int[][] img, int size, int r, int c, StringBuilder sb) {
		if(check(img, size, r, c) != -1) {
			sb.append(check(img, size, r, c));
			return;
		}
		else {
			sb.append("(");
			solve(img, size/2, r, c, sb);
			solve(img, size/2, r, c+size/2, sb);
			solve(img, size/2, r+size/2, c, sb);
			solve(img, size/2, r+size/2, c+size/2, sb);
			sb.append(")");
		}
		
	}
	
	static int check(int[][] img, int size, int r, int c) {
		int base = img[r][c];
		for(int i=r; i<r+size; i++) {
			for(int j=c; j<c+size; j++) {
				if(img[i][j] != base) return -1;
			}
		}
		return base;
	}
}
