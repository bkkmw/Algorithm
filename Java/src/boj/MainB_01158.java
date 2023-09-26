package boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MainB_01158 {
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input/boj/input_01158.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		String line = br.readLine();		
		StringTokenizer st = new StringTokenizer(line, " ");
		
		sb.append("<");
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[] check = new int[N];
		int idx = 0;
		
		for(int i=0; i<N; i++) {
			for(int k=0; k<K; k++) {
				while(check[idx] != 0) idx = (++idx) % N;
				if(k != K-1) idx = (++idx)%N;
				else {
					check[idx] = 1;
					sb.append(idx+1);
				}
			}
			if(i == N-1) sb.append(">");
			else sb.append(", ");
			
		}
		System.out.println(sb.toString());
	}
}
