package swea;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_1233_2 {
	static int[] twos = new int[] {
			1, 2, 4, 8, 16, 32, 64, 128, 256
	};
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input/swea/input_1233.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		for(int test_case=1; test_case<=10; test_case++) {
			int N = Integer.parseInt(br.readLine());
			boolean[] tree = new boolean[N+1];
			
			for(int i=1; i<=N; i++) {
				StringTokenizer	st = new StringTokenizer(br.readLine(), " ");
				st.nextToken();
				String temp = st.nextToken();
				tree[i] = isOperator(temp);
			}
			sb.append("#").append(test_case);
			sb.append(" ").append(solve_(tree,N)).append("\n");
			
		}
		System.out.println(sb.toString());
		br.close();
		
	}
	
	static boolean isOperator(String str) {
		if(str.equals("+") || str.equals("-") ||
				str.equals("*") || str.equals("/"))
			return true;
		else return false;
	}
		
	static int solve_(boolean[] tree, int N) {
		if (N%2 == 0) return 0;
		int height = (int)(Math.log(N) / Math.log(2));
		while(height != 0) {
			int end = (twos[height+1]-1 < N) ? twos[height+1]-1 : N;
			for(int i = twos[height]; i<end; i+=2) {
				if(tree[i] == true || tree[i+1] == true || tree[i/2] == false)
					return 0;
				else tree[i/2] = false;					
				
			}
			height --;
		}
		return 1;
	}
	
}
