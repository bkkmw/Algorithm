package boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class MainB_01697 {
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input/boj/input_01697.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int src = Integer.parseInt(st.nextToken());
		int dst = Integer.parseInt(st.nextToken());
		System.out.println(f(src,dst));
		
	}
	static int f(int src, int dst) {
		boolean[] visit = new boolean[200001]; 
		Deque<int[]> q = new ArrayDeque<>();
		q.add(new int[] {src,0});
		while (!q.isEmpty()) {
			int[] infor = q.poll();
			if(infor[0] == dst) return infor[1];
			if(visit[infor[0]]) continue; 
			visit[infor[0]] = true; 
			
			if(infor[0]<1) {
				q.add(new int[] {infor[0] *2, infor[1] +1});
				q.add(new int[] {infor[0] +1, infor[1] +1});
				
			}
			else if(infor[0] <dst) { 
				q.add(new int[] {infor[0] *2, infor[1] +1});
				q.add(new int[] {infor[0] +1, infor[1] +1});
				q.add(new int[] {infor[0] -1, infor[1] +1});
			}
			else if(infor[0] > dst ) {
				q.add(new int[] {infor[0] -1, infor[1] +1});
			}
		}
		return -1;
	}
}
