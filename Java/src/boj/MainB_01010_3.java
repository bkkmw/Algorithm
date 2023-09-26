package boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class MainB_01010_3 {

	// nCr = n-1Cr + n-1Cr-1
	static int[][] table = new int[30][30];
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input/boj/input_01010.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		for(int i=0; i<30; i++) {
			table[i][i] = 1;
		}
		for(int i=1; i<30; i++) {
			table[i][0] = 1;
		}
		for(int i=2; i<30; i++) {
			for(int j=1; j<=i; j++) {
				table[i][j] = table[i-1][j-1] + table[i-1][j];
			}
		}
		
		int TC = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=TC; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int src = Integer.parseInt(st.nextToken());
			int dst = Integer.parseInt(st.nextToken());
						
			sb.append(table[dst][src]).append("\n");
		}
		System.out.println(sb.toString());
		
	}
}
