package swea;

import java.util.Scanner;
import java.util.StringTokenizer;
import java.io.FileInputStream;

/*
   사용하는 클래스명이 Solution 이어야 하므로, 가급적 Solution.java 를 사용할 것을 권장합니다.
   이러한 상황에서도 동일하게 java Solution 명령으로 프로그램을 수행해볼 수 있습니다.
 */
class Solution_1210_1
{
	static int map[][] = new int[100][100];
	static int dir_lru[][] = new int[][]{
		// LEFT RIGHT UP
		{0,-1}, {0, +1}, {-1,0}, {+1,0}
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
			System.out.printf("#%d %d\n", test_case, solve(end_pos));
		}
		sc.close();
	}
	
	static int solve(int end_x) {
		int xpos = end_x;
		int ypos = 99;
		int cur_dir = -1;
		while(ypos >0) {
			int nx; 
			int ny;
			for(int dir=0; dir<3; dir++) {
				if(cur_dir == 0 && dir == 1) continue;
				else if(cur_dir == 1 && dir == 0) continue;
				ny = ypos + dir_lru[dir][0];
				nx = xpos + dir_lru[dir][1];
				if((ny < 0) || (ny >= 100) || (nx <0) || (nx >=100)) {
					continue;	// out of bounds
				}
				if(map[ny][nx] == 1) {
					ypos = ny;
					xpos = nx;
					cur_dir = dir;
					break;
				}
				
			}
		}
		return xpos;
	}
}
