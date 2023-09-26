package boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MainB_01010_1 {

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input/boj/input_01010.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int TC = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=TC; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int src = Integer.parseInt(st.nextToken());
			int dst = Integer.parseInt(st.nextToken());
			int ans = 1;
			for(int i=1; i<=src; i++) {
				ans = ans * (dst-i+1) / i;
			}
			sb.append(ans).append("\n");
		}
		System.out.println(sb.toString());
		
	}
}
