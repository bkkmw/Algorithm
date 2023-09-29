package boj;

import java.util.*;
import java.io.*;

public class MainB_13913 {
	
	public static final int END = 100000;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input/boj/input_13913.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		Record ans = solve(N, K, sb);
		System.out.println(ans.sec);
		
		System.out.println(sb.toString());
	}
	
	public static Record solve(int N, int K, StringBuilder sb) {
		if(N == K) {
			sb.append(N);
			return new Record(N, 0);
		}
		Record ret = null;
		int[] check = init_arr(END+1, -2);
		Queue<Record> q = new LinkedList<Record>();
		q.add(new Record(N, 0));
		check[N] = -1;
		
		int[] next = new int[3];
		
		while(!q.isEmpty()) {
			Record poll = q.poll();
			if(poll.pos == K) return poll;
			
			update_next(next, poll.pos);
			
			for(int i=0; i<3; i++) {
				if(next[i] < 0 || next[i] > END) continue;
				if(check[next[i]] != -2) continue;
				if(next[i] == K) {
					ret = new Record(next[i], poll.sec+1);
					check[next[i]] = poll.pos;
					break;
				}
				q.add(new Record(next[i], poll.sec+1));
				check[next[i]] = poll.pos;
			}
		}
		
		Stack<Integer> stack = new Stack<>();
		int temp = ret.pos;
		while(check[temp] > -1) {
			stack.add(temp);
			temp = check[temp];
		}
		stack.add(temp);
		
		while(!stack.isEmpty()) {
			int pos = stack.pop();
			sb.append(pos).append(" ");
		}
		return ret;
	}
	
	public static void update_next(int[] next, int pos) {
		next[0] = pos-1;
		next[1] = pos+1;
		next[2] = 2*pos;
	}
	
	public static int[] init_arr(int size, int val) {
		int[] ret = new int[size];
		for(int i=0; i<size; i++) {
			ret[i] = val;
		}
		return ret;
	}
	
	public static class Record {
		int pos;
		int sec;
		
		Record(int pos, int sec) {
			this.pos = pos;
			this.sec = sec;
		}
	}
}
