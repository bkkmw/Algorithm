package boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MainB_01541 {
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input/boj/input_01541.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), "+-", true);
		
		String[] calc = new String[50];
		int idx = 0;
		while(st.hasMoreTokens()) {
			calc[idx++] = st.nextToken();
		}
				
		System.out.println(solve(calc, idx));
	}
	
	static int solve(String[] calc, int idx) {
		int ret = 0;
		int i = 0;
		while(i < idx) {
			if(calc[i].equals("-")) {
				int sum = 0;
				i++;
				while(i < idx && !calc[i].equals("-")) {
					if(!calc[i].equals("+")) sum += Integer.parseInt(calc[i++]);
					else i++;
				}
				ret -= sum;
			}
			else if(!calc[i].equals("+")) ret += Integer.parseInt(calc[i++]);
			else i++;
		}
		
		return ret;
	}
}
