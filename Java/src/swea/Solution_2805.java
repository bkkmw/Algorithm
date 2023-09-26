package swea;

import java.util.Scanner;
import java.util.StringTokenizer;
import java.io.FileInputStream;

class Solution_2805
{
	static int N;
	static int[][]map = new int[49][49];
	static int[][]score = new int[49][49];
	
	public static void main(String args[]) throws Exception
	{
		System.setIn(new FileInputStream("input/swea/input_2805.txt"));
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++)
		{
			clear();
			N = sc.nextInt();
			sc.nextLine();
			for(int i=0; i<N; i++) {
				String line = sc.nextLine();
				for(int j=0; j<N; j++) {
					map[i][j] = (int)line.charAt(j) - 48;
				}
			}
			score_map(N);
			System.out.printf("#%d %d\n", test_case, solve(N));

		}
		sc.close();
	}
	
	static void clear() {
		for(int i=0; i<49; i++) {
			for(int j=0; j<49; j++) {
				map[i][j] = 0;
				score[i][j] = 0;
			}
		}
	}
	
	static void score_map(int N) {
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				int diff = Math.abs(N/2 - i);
				if(j>=diff && j<=N-1-diff) {
					score[i][j] = 1;
				}
			}
		}
	}
	
	static int solve(int N) {
		int ret = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				ret += score[i][j] * map[i][j];
			}
		}
		return ret;
	}
}