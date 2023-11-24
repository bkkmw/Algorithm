package boj;

import java.io.*;
import java.util.*;

public class MainB_02670 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input/boj/input_02670.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		double[] seq = new double[N];
		
		for(int i=0; i<N; i++) 
			seq[i] = Double.parseDouble(br.readLine());
		
		double ans = solve(seq);
		System.out.printf("%.3f", ans);
		br.close();
	}
	
	public static double solve(double[] seq) {
		int N = seq.length;
		double ret = 0;
		double[][] mem = new double[N][2];
		
		// continued
		mem[0][0] = seq[0];
		// stopped
		mem[0][1] = seq[0];
		
		for(int i=1; i<N; i++) {
			mem[i][1] = Double.max(mem[i-1][0], mem[i-1][1]) * seq[i];
			mem[i][0] = seq[i];
			
			double higher = Double.max(mem[i-1][0], mem[i-1][1]);
			ret = ret < higher ? higher : ret;
		}
		
		return ret;
	}
}
