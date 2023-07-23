package swea;

import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileInputStream;

class Solution_1954
{
	static int[][] dir_yx = new int[][] {
		// U R D L
		{-1,0}, {0,+1}, {+1,0}, {0,-1}
	};
	static int[][] map = new int[10][10];
	
	public static void main(String args[]) throws Exception
	{
		System.setIn(new FileInputStream("input/swea/input_1954.txt"));
		Scanner sc = new Scanner(System.in);
		
		int T;
		T=sc.nextInt();
		for(int test_case = 1; test_case <= T; test_case++)
		{
			clear();
			int N = sc.nextInt();
			draw(N);
			System.out.printf("#%d\n", test_case);
			print(N);
			

		}
	}
	
	static void draw(int N) {
		int cnt = 1;
		int y = 0;
		int x = 0;
		int dir = 1;
		map[y][x] = cnt++;
		while(cnt <= N*N) {
			int ny = y + dir_yx[dir][0];
			int nx = x + dir_yx[dir][1];
			if(in_bound(ny,nx,N)) {
				map[ny][nx] = cnt++;
				y = ny;
				x = nx;
			}
			else dir = (dir+1)%4;
		}
	}
	
	static boolean in_bound(int y, int x, int N) {
		if(y < 0 || y > N-1 || x < 0 || x > N-1) {
			return false;
		}
		else if(map[y][x] != 0) return false;
		return true;
	}
	
	static void print(int N) {
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				System.out.printf("%d ",map[i][j]);
			}
			System.out.println();
		}
	}
	
	static void clear() {
		for(int i=0; i<10; i++) {
			for(int j=0; j<10; j++) {
				map[i][j] = 0;
			}
		}
	}
	
}