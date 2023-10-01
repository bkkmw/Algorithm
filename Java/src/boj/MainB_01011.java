package boj;

import java.util.*;
import java.io.*;

public class MainB_01011 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input/boj/input_01011.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int TC = Integer.parseInt(br.readLine())+1;
		
		for(int tc=1; tc<TC; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			int src = Integer.parseInt(st.nextToken());
			int dst = Integer.parseInt(st.nextToken());
			
			int ans = solve(src, dst);
			sb.append(ans).append("\n");
		}
		
		System.out.println(sb.toString());
	}
	
	public static int solve(int src, int dst) {
		int ret = 0;
		int dist = dst-src;
		int stride = 1;
		
		while(true) {
			if(dist >= 2*stride) {
				ret+=2;
				dist -= 2*stride;
			}
			else if(dist >= stride) {
				ret++;
				dist -= stride;
			}
			else
				break;
			stride++;
		}
		
		while(dist != 0) {
			if(dist >= stride) {
				ret ++;
				dist -= stride;
			}
			else {
				stride--;
			}
		}
		
		return ret;
		
	}
}
