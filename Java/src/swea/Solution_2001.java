package swea;

import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;


class Solution_2001
{		
	public static void main(String args[]) throws Exception
	{
		System.setIn(new FileInputStream("input/swea/input_2001.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String line = null;
		int T, N, M;
		
		line = in.readLine();
		StringTokenizer st = new StringTokenizer(line, " ");
		T = Integer.parseInt(st.nextToken());
		
		for(int test_case = 1; test_case <= T; test_case++)
		{
			line = in.readLine();
			st = new StringTokenizer(line, " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			int map[][] = new int[N+1][N+1];
			
			for(int i=1; i<N+1; i++) {
				line = in.readLine();
				st = new StringTokenizer(line, " ");
				for(int j=1; j<N+1; j++) {
					map[i][j] = map[i-1][j] + map[i][j-1]
						+ Integer.parseInt(st.nextToken()) - map[i-1][j-1];
				}
			}
			sb.append("#");
			sb.append(test_case);
			sb.append(" ");
			sb.append(solve(N,M,map));
			sb.append("\n");
		}
		System.out.println(sb.toString());
		
	}
	
	static int solve(int N, int M, int[][] map) {
		int ret = 0;
		int temp = 0;
		for(int i=1; i<=N-M+1; i++) {
			for(int j=1; j<=N-M+1; j++) {
				temp = map[i+M-1][j+M-1] - map[i+M-1][j-1] - map[i-1][j+M-1] + map[i-1][j-1];
				if(temp > ret) ret = temp;
			}
		}
		return ret;
	}
}