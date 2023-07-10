package boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.StringTokenizer;

public class MainB_23300 {
	
	public static Map<Character, Integer> char_to_int;
	static {
		char_to_int = new HashMap<>(4);
		char_to_int.put('A', 0);
		char_to_int.put('B', 1);
		char_to_int.put('F', 2);
		char_to_int.put('C', 3);
	}

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input/boj/input_23300.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(), " ");
		
		int N = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());
		
		int[][] task = new int[Q][2];
		for(int i=0; i<Q; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			char job = st.nextToken().charAt(0);
			task[i][0] = char_to_int.get(job);
			if(task[i][0] == 0)
				task[i][1] = Integer.parseInt(st.nextToken());
		}
		
		
		solve(N, Q, task);
	}
	
	public static void solve(int N, int Q, int[][] task) {
		int current_page = -1;
		Stack<Integer> back = new Stack<>();
		Stack<Integer> forward = new Stack<>();
		
		for(int i=0; i<Q; i++) {
			int job = task[i][0];
			if(job == 0) {
				forward.removeAllElements();
				if(current_page > -1)
					back.add(current_page);
				
				current_page = task[i][1];
			} else if(job == 1) {
				if(back.size() < 1) continue;
				forward.add(current_page);
				
				current_page = back.pop();
			} else if(job == 2) {
				if(forward.size() < 1) continue;
				back.add(current_page);
				
				current_page = forward.pop();
			} else {
				compress(N, back);
				
			}
			
		}
		
		print_ans(current_page, back, forward);
	}
	
	public static void compress(int N, Stack<Integer> back) {
		int size = back.size();
		if(size < 1) return;
		
		int prev = back.get(0);
		
		for(int i=1; i<size; i++) {
			int next = back.get(i);
			if(prev == next) {
				back.remove(i);
				i --;
				size --;
			}
			
			prev = next;
		}
	}
	
	public static void print_ans(int current_page, Stack<Integer> back, Stack<Integer> forward) {
		StringBuilder sb = new StringBuilder();
		
		sb.append(current_page).append("\n");
		
		if(back.size() > 0) {
			while(!back.isEmpty())
				sb.append(back.pop()).append(" ");
		}
		else {
			sb.append(-1);
		}
		sb.append("\n");
		
		if(forward.size() > 0) {
			while(!forward.isEmpty())
				sb.append(forward.pop()).append(" ");
		} else {
			sb.append(-1);
		}
		
		System.out.println(sb.toString());
	}
}
