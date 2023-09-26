package swea;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


class Solution_9229
{
	
	public static void main(String args[]) throws Exception
	{
		System.setIn(new FileInputStream("input/swea/input_9229.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String line = br.readLine();
		StringTokenizer st = new StringTokenizer(line);
		int T = Integer.parseInt(st.nextToken());		
		
		for(int test_case = 1; test_case <= T; test_case++)
		{
			line = br.readLine();
			st = new StringTokenizer(line, " ");
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int[] arr = new int[N];
			line = br.readLine();
			st = new StringTokenizer(line, " ");
			for(int i=0; i<N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			sb.append("#").append(test_case).append(" ");
			sb.append(solve(arr, N, M)).append("\n");
		
		}
		System.out.println(sb.toString());
	}
	
	static int solve(int[] arr, int N, int M) {
		int ret = -1;
		for(int i=0; i<N; i++) {
			for(int j=i+1; j<N; j++) {
				int temp = arr[i]+arr[j];
				if(temp == M) return M;
				else if(temp > ret && temp < M) {
					ret = temp;
				}
			}
		}
		return ret;
	}
	
}