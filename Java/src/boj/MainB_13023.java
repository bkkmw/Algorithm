package boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;
import java.util.StringTokenizer;

public class MainB_13023 {
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input/boj/input_13023.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		LinkedList<Integer>[] list = new LinkedList[N];
		int[] check = new int[N];
				
		for(int i=0; i<N; i++) list[i] = new LinkedList<Integer>();
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int src = Integer.parseInt(st.nextToken());
			int dst = Integer.parseInt(st.nextToken());
			list[src].add(dst);
			list[dst].add(src);
		}
		
		int ans = solve(list, check);
		System.out.println(ans);
	}
	
	static int solve(LinkedList<Integer>[] list, int[] check) {
		int ret = 0;
		for(int i=0; i<list.length; i++) {
			check[i] = 1;
			ret = find(list, check, i, 1, 0);
			if(ret == 1) return ret;
			check[i] = 0;
		}
		return ret;
	}
	
	static int find(LinkedList<Integer>[] list, int[] check, int src, int cnt, int done) {
		if(done == 1) return done;
		if(cnt == 5) return 1;

		check[src] = 1;
		
		Iterator<Integer> iter = list[src].iterator();
		if(iter == null) return done;
		while(iter.hasNext() && done != 1) {
			int next = iter.next();
			if(check[next] == 0) done = find(list,check,next,cnt+1,done);
		}
		
		check[src] = 0;
		return done;
	}
}