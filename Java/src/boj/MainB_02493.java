package boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MainB_02493 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input/boj/input_02493.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		String line = br.readLine();
		StringTokenizer st = new StringTokenizer(line);
		int N = Integer.parseInt(st.nextToken());
		
		line = br.readLine();
		int[][] stack = new int[N+1][2];	// Height, index
		int top = -1;
		stack[++top][0] = Integer.MAX_VALUE;	// H
		stack[top][1] = 0;						// idx
		// stack[0] => 0
		st = new StringTokenizer(line, " ");
		for(int i=0; i<N; i++) {
			int temp = Integer.parseInt(st.nextToken());
			int j = 0;
			for(j=top; j>=0; j--) {
				if(stack[j][0] > temp) break;
			}
			sb.append(stack[j][1]);
			sb.append(" ");
			top = j;
			stack[++top][0] = temp;
			stack[top][1] = i+1;			
		}
		System.out.println(sb.toString());
		br.close();
		
	}
}
