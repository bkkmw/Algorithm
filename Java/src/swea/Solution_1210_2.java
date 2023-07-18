package swea;

import java.util.Scanner;
import java.util.StringTokenizer;
import java.io.FileInputStream;

/*
   사용하는 클래스명이 Solution 이어야 하므로, 가급적 Solution.java 를 사용할 것을 권장합니다.
   이러한 상황에서도 동일하게 java Solution 명령으로 프로그램을 수행해볼 수 있습니다.
 */
class Solution_1210_2
{
	static int map[][] = new int[100][100];
	static int dir[][] = new int[][] {
		// U R D L
		{-1,0}, {0,+1}, {+1,0}, {0,-1}
	};
		
	public static void main(String args[]) throws Exception
	{
		System.setIn(new FileInputStream("input/swea/input_1210.txt"));
		Scanner sc = new Scanner(System.in);
		int T = 10;
		int end_pos = 0;

		for(int test_case = 1; test_case <= T; test_case++)
		{
			int t = Integer.parseInt(sc.nextLine());
			for(int i=0; i<100; i++) {
				StringTokenizer st = new StringTokenizer(sc.nextLine(), " ");
				for(int j=0; j<100; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(map[i][j] == 2) end_pos = j;
				}
			}
			System.out.printf("#%d %d\n", test_case, solve_(end_pos));	
		}
		sc.close();
	}
	
	static int solve_(int end_x) {
		int x = end_x;
		int y = 99;
		int ndir = 0;
		while(y > 0) {
			ndir = check_RL(y,x,ndir);
			y += dir[ndir][0];
			x += dir[ndir][1];
		}		
		return x;
	}
	
	static int check_RL(int y, int x, int pdir) {
		for(int i=0; i<2; i++) {
			int ndir = 2*i + 1;
			int nx = x + dir[ndir][1];
			if(nx < 0 || nx > 99) continue;
			if(map[y][nx] == 1 && (ndir+2)%4 != pdir) return ndir;
		}
		return 0;
	}
}
