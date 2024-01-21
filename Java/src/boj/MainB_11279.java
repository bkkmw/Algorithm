package boj;

import java.io.*;
import java.util.*;

public class MainB_11279 {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input/boj/input_11279.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>((o1, o2) -> {
			return o2-o1;
		});
		
		int N = Integer.parseInt(br.readLine());
		for(int i=0; i<N; i++) {
			int temp = Integer.parseInt(br.readLine());
			if(temp == 0) {
				sb.append(pq.size() > 0 ?  pq.poll() : 0).append("\n");
			}
			else {
				pq.add(temp);
			}
		}
		
		System.out.println(sb.toString());
	}
}
