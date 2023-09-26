package boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class MainB_01010_2 {
	static BigInteger[] table = new BigInteger[30];

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input/boj/input_01010.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		table[0] = new BigInteger("1");
		for(int i=1; i<30; i++) {
			table[i] = (table[i-1].multiply(new BigInteger(String.valueOf(i))));
		}
		
		int TC = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=TC; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int src = Integer.parseInt(st.nextToken());
			int dst = Integer.parseInt(st.nextToken());
			
			BigInteger numer = table[dst].divide(table[dst - src]);
			BigInteger denom = table[src];
			
			BigInteger big_ans = numer.divide(denom);
			int ans = big_ans.intValue();
			
			sb.append(ans).append("\n");
		}
		System.out.println(sb.toString());
		
	}
}
