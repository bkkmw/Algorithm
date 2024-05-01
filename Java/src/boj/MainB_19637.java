package boj;

import java.io.*;
import java.util.*;

public class MainB_19637 {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input/boj/input_19637.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		TreeMap<Integer, String> map = new TreeMap<Integer, String>();
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			String title = st.nextToken();
			int limit = Integer.parseInt(st.nextToken());
			
			if(map.get(limit+1) == null)
				map.put(limit+1, title);
		}
		
		int[] stats = new int[M];
		for(int i=0; i<M; i++) {
			stats[i] = Integer.parseInt(br.readLine());
		}
		
		solve(sb, map, stats);
		
		System.out.println(sb.toString());
		br.close();
	}
	
	public static void solve(StringBuilder sb, TreeMap<Integer, String> map, int[] stats) {
		int M = stats.length;
		
		for(int i=0; i<M; i++) {
			String title = map.higherEntry(stats[i]).getValue();
			sb.append(title).append("\n");
		}
		
	}
}
