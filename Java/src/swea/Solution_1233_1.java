package swea;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_1233_1 {
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input/swea/input_1233.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		for(int test_case=1; test_case<=10; test_case++) {
			int N = Integer.parseInt(br.readLine());
			int ans = 1;
			if(N%2 == 0) ans = 0;
			for(int i=1; i<=N; i++) {
				StringTokenizer	st = new StringTokenizer(br.readLine(), " ");
				if(ans == 1) {
					st.nextToken();
					String temp = st.nextToken();
					boolean tree_temp = isOperator(temp);
					if(i<=N/2 && tree_temp == false) {
						ans = 0;
					}
					else if((i>N/2) && tree_temp == true) {
						ans = 0;
					}
				}

			}
			sb.append("#").append(test_case);
			sb.append(" ").append(ans).append("\n");
			
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
}
