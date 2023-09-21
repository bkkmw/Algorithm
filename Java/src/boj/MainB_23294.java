package boj;

import java.io.*;
import java.util.*;

public class MainB_23294 {
	
	public static Map<Character, Integer> char_to_int;
	static {
		char_to_int = new HashMap<>(4);
		char_to_int.put('A', 0);
		char_to_int.put('B', 1);
		char_to_int.put('F', 2);
		char_to_int.put('C', 3);
	}

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input/boj/input_23294.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(), " ");
		
		int N = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		
		int[] capa = new int[N+1];
		st = new StringTokenizer(br.readLine(), " ");
		for(int i=0; i<N; i++) {
			capa[i+1] = Integer.parseInt(st.nextToken());
		}
		
		int[][] task = new int[Q][2];
		for(int i=0; i<Q; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			char job = st.nextToken().charAt(0);
			task[i][0] = char_to_int.get(job);
			if(task[i][0] == 0)
				task[i][1] = Integer.parseInt(st.nextToken());
		}
		
		
		solve(N, Q, C, capa, task);
	}
	
	public static void solve(int N, int Q, int C, int[] capa, int[][] task) {
		int current_page = -1;
		Deque<Integer> back = new LinkedList<>();
		Deque<Integer> forward = new LinkedList<>();
		int forw_c = 0, cache = 0;
		
		for(int i=0; i<Q; i++) {
			int job = task[i][0];
			if(job == 0) {
				forward.clear();
				cache -= forw_c;
				forw_c = 0;
				
				if(current_page > -1) {
					back.addLast(current_page);
				}
				
				current_page = task[i][1];
				cache += capa[current_page];
				while(cache > C) {
					int page = back.pollFirst();
					cache -= capa[page];
				}
				
			} else if(job == 1) {
				if(back.size() < 1) continue;
				forward.addLast(current_page);
				forw_c += capa[current_page];
				
				current_page = back.pollLast();
			} else if(job == 2) {
				if(forward.size() < 1) continue;
				back.addLast(current_page);
				
				current_page = forward.pollLast();
				forw_c -= capa[current_page];
			} else {
				cache -= compress(capa, back);
				
			}
			
		}
		
		print_ans(current_page, back, forward);
	}
	
	public static int compress(int[] capa, Deque<Integer> back) {
		int size = back.size();
		if(size < 1) return 0;
		
		Deque<Integer> compressed = new LinkedList<Integer>();
		int ret = 0;
		
		int prev = back.poll();
		compressed.addLast(prev);
		
		while(!back.isEmpty()) {
			int cur = back.poll();
			if(cur == prev) {
				ret += capa[cur];
			}
			else {
				compressed.addLast(cur);
				prev = cur;
			}
		}
		
		// Call by Value
//		back = compressed;
		
		while(!compressed.isEmpty()) {
			back.addLast(compressed.poll());
		}
		
		return ret;
	}
	
	public static void print_ans(int current_page, Deque<Integer> back, Deque<Integer> forward) {
		StringBuilder sb = new StringBuilder();
		
		sb.append(current_page).append("\n");
		
		if(back.size() > 0) {
			while(!back.isEmpty())
				sb.append(back.pollLast()).append(" ");
		}
		else {
			sb.append(-1);
		}
		sb.append("\n");
		
		if(forward.size() > 0) {
			while(!forward.isEmpty())
				sb.append(forward.pollLast()).append(" ");
		} else {
			sb.append(-1);
		}
		
		System.out.println(sb.toString());
	}
}
