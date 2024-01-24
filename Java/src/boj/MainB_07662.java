package boj;

import java.io.*;
import java.util.*;

public class MainB_07662 {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input/boj/input_07662.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		
		int TC = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=TC; tc++) {
			int N = Integer.parseInt(br.readLine());
			
			solve(br, sb, N);
		}
		
		System.out.println(sb.toString());
	}
	
	public static void solve(BufferedReader br, StringBuilder sb, int N) throws IOException{
		StringTokenizer st;
		TreeMap<Integer, Integer> map = new TreeMap<Integer, Integer>();
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			String cmd = st.nextToken();
			int num = Integer.parseInt(st.nextToken());
			
			if("I".equals(cmd)) map.put(num, map.get(num) == null ? 1 : map.get(num)+1);
			else {
				if(map.isEmpty()) continue;
				int key = (num>0)? map.lastKey() : map.firstKey();
				int cnt = map.get(key);
				
				if(cnt == 1) map.remove(key);
				else map.put(key, cnt-1);
			}
				
		}
		sb.append(map.isEmpty() ? "EMPTY" : String.format("%d %d", map.lastKey(), map.firstKey())).append("\n");
	}
}
