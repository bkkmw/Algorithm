package boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class MainB_11286_PQ {
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input/boj/input_11286.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
//		System.out.println(Math.abs(-2147483648));
		PriorityQueue<Integer[]> heap_ = new PriorityQueue<Integer[]>(new Comparator<Integer[]>() {

			@Override
			public int compare(Integer[] o1, Integer[] o2) {
				return o1[0] == o2[0] ? o1[1] - o2[1] : o1[0] - o2[0];
			}
		});
		
//		PriorityQueue<Integer> heap = new PriorityQueue<>(new Comparator<Integer>() {
//
//			@Override
//			public int compare(Integer o1, Integer o2) {
//				if(Math.abs(o1) == Math.abs(o2)) return o1-o2;
//				
//				return Math.abs(o1) - Math.abs(o2);
//			}
//		});
		
		int N = Integer.parseInt(br.readLine());
		for(int i=0; i<N; i++) {
			int temp = Integer.parseInt(br.readLine());
			if(temp == 0) {
				if(!heap_.isEmpty()) {
					sb.append((int)heap_.poll()[1]).append("\n");
				}
				else sb.append(temp).append("\n");
			}
			else {
				heap_.add(new Integer[] {Math.abs(temp), temp});
			}
		}
		System.out.print(sb);
		br.close();
	}
	
}
