package boj;

import java.io.*;
import java.util.*;

public class MainB_01655 {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input/boj/input_01655.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		int[] num = new int[N];
		
		for(int i=0; i<N; i++) {
			num[i] = Integer.parseInt(br.readLine());
		}
		
		solve(num, sb);
		System.out.println(sb.toString());
		br.close();
		
	}
	
	public static void solve(int[] numbers, StringBuilder sb) {
		int N = numbers.length, num = 0;
		PriorityQueue<Integer> lt = new PriorityQueue<Integer>((o1, o2) -> {
			return o2-o1;
		});
		PriorityQueue<Integer> gt = new PriorityQueue<Integer>((o1, o2) -> {
			return o1-o2;
		});
		
		num = numbers[0];
		sb.append(num).append("\n");
		
		for(int i=1; i<N; i++) {
			if(numbers[i] > num) {
				if(i%2 == 1) {
					gt.add(numbers[i]);
				} else {
					lt.add(num);
					gt.add(numbers[i]);
					num = gt.poll();
				}
			} else {
				if(i%2 == 1) {
					gt.add(num);
					lt.add(numbers[i]);
					num = lt.poll();					
				} else {
					lt.add(numbers[i]);
				}
			}
			
			sb.append(num).append("\n");
		}
	}
}
