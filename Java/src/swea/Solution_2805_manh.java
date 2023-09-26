package swea;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

class Solution_2805_manh
{
	static int N;
	
	public static void main(String args[]) throws Exception
	{
		System.setIn(new FileInputStream("input/swea/input_2805.txt"));
//		Scanner sc = new Scanner(System.in);
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T;
		T=Integer.parseInt(in.readLine());
		int ans;

		for(int test_case = 1; test_case <= T; test_case++)
		{
			ans = 0;
			N = Integer.parseInt(in.readLine());
			for(int i=0; i<N; i++) {
				String line = in.readLine();
				for(int j=0; j<N; j++) {
					if(Math.abs(i-N/2) + Math.abs(j-N/2) <= N/2)
						ans += (int)line.charAt(j) -48;
				}
			}
			sb.append(String.format("#%d %d\n", test_case, ans));
		}
		System.out.println(sb.toString());
	}
	
}