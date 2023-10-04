package boj;

import java.util.*;
import java.io.*;

public class MainB_01016 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input/boj/input_01016.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		long min = Long.parseLong(st.nextToken());
		long max = Long.parseLong(st.nextToken());
		
		int ans = solve(min, max);
		System.out.println(ans);
		br.close();
	}
	
	public static int solve(long min, long max) {
		int ret = 0;
		int cnt = (int)(max-min) + 1;
		boolean[] check = new boolean[cnt];
		
		long num = 2;
		while(num*num <= max) {
			long k = (min / (num*num) + ((min%(num*num))==0? 0 : 1));
			
			while(k*num*num <= max) {
				check[(int)(k*num*num-min)] = true;
				k++;
			}
			num++;
		}
		
		for(int i=0; i<cnt; i++) {
			ret += check[i]? 0 : 1;
		}
		
		return ret;
	}
}
