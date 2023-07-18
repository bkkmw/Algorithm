package swea;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

class Solution_1225
{
	public static void main(String args[]) throws Exception
	{
		System.setIn(new FileInputStream("input/swea/input_1225.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = 10;
		int[] list = new int[8];
		
		for(int test_case = 1; test_case <= T; test_case++)
		{
			String line = in.readLine();
			line = in.readLine();
			StringTokenizer st = new StringTokenizer(line, " ");

			for(int i=0; i<8; i++) {
				list[i] = Integer.parseInt(st.nextToken());
			}			
			sb.append("#").append(test_case).append(" ");
			print(solve(list), list, sb);
		}
		System.out.println(sb.toString());
	}
	
	static int solve(int[] list) {
		int idx = 0;
		int val = 0;
		boolean done = false;
		while(!done) {
			list[(idx)%8] -= ((val++) % 5 + 1);		
			if(list[idx%8] < 1) {
				list[idx%8] = 0;
				done = true;
			}
			idx ++;
		}
		return (idx%8);
	}
	
	static void print(int idx, int[] list, StringBuilder sb) {
		for(int i=0; i<8; i++) {
			sb.append(list[(i+idx) % 8]).append(" ");
		}
		sb.append("\n");
	}
	
	
}