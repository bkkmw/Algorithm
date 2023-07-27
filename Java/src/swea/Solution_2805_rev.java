package swea;

import java.util.Scanner;
import java.util.StringTokenizer;
import java.io.FileInputStream;

class Solution_2805_rev
{
	static int N;
	
	public static void main(String args[]) throws Exception
	{
		System.setIn(new FileInputStream("input/swea/input_2805.txt"));
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		int T;
		T=sc.nextInt();
		int ans;

		for(int test_case = 1; test_case <= T; test_case++)
		{
			ans = 0;
			N = sc.nextInt();
			sc.nextLine();
			for(int i=0; i<N; i++) {
				String line = sc.nextLine();
				int diff = Math.abs(N/2 - i);
				for(int j=0; j<N; j++) {
					if(diff <= j && j <= N-1-diff) {
						ans += (int)line.charAt(j) - 48;
					}
				}
			}
			
			sb.append(String.format("#%d %d\n", test_case, ans));

		}
		System.out.println(sb.toString());
		sc.close();
	}
	
}