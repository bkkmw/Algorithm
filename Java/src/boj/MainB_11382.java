package boj;

import java.util.*;
import java.io.*;

public class MainB_11382 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input/boj/input_11382.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		long ans = 0;
		for(int i=0; i<3; i++)
			ans += Long.parseLong(st.nextToken());
		
		System.out.println(ans);
	}
}
