package boj;

import java.io.*;
import java.util.*;

public class MainB_02696 {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input/boj/input_02696.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=TC; tc++) {
			
			solve(br, sb);
		}
		System.out.println(sb.toString());
		br.close();
	}
	
	public static void solve(BufferedReader br, StringBuilder sb) throws IOException {
		int M = Integer.parseInt(br.readLine());
		sb.append(M / 2 + (M%2 == 0 ? 0 : 1)).append("\n");
		PriorityQueue<Integer> lt = new PriorityQueue<Integer>((o1, o2) -> {return o2-o1;});
		PriorityQueue<Integer> gt = new PriorityQueue<Integer>((o1, o2) -> {return o1-o2;});
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int mid = Integer.parseInt(st.nextToken());
		sb.append(mid).append(" ");
		
		for(int i=1; i<M; i++) {
			if(i%10 == 0) {
				st = new StringTokenizer(br.readLine());
			}
			int tmp = Integer.parseInt(st.nextToken());
			
			if(tmp > mid) {
				gt.add(tmp);
				lt.add(mid);
			} else {
				lt.add(tmp);
				gt.add(mid);
			}
			
			mid = (lt.size() > gt.size()) ? lt.poll() : gt.poll();
			
			if(i%2 == 0) {
				sb.append(mid).append(" ");
			}
			if(i%20 == 19) {
				sb.append("\n");
			}
		}
		
		sb.append("\n");
	}
}
