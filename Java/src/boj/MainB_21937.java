package boj;

import java.io.*;
import java.util.*;

public class MainB_21937 {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input/boj/input_21937.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		List<Integer>[] prereq = init_list_arr(N);
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int src = Integer.parseInt(st.nextToken())-1;
			int dst = Integer.parseInt(st.nextToken())-1;
			
			prereq[dst].add(src);
		}
		
		int dst = Integer.parseInt(br.readLine())-1;
		
		int ans = solve(prereq, dst);
		
		System.out.println(ans);
		br.close();
	}
	
	public static int solve(List<Integer>[] prereq, int dst) {
		int ret = 0;
		int N = prereq.length;
		
		boolean[] checked = new boolean[N];
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(dst);
		checked[dst] = true;
		
		while(!q.isEmpty()) {
			int poll = q.poll();
			Iterator<Integer> it = prereq[poll].iterator();
			
			while(it.hasNext()) {
				int next = it.next();

				if(checked[next]) continue;
				
				q.add(next);
				checked[next] = true;
				ret++;
			}
		}
		
		return ret;
	}
	
	public static List<Integer>[] init_list_arr(int N) {
		List<Integer>[] ret = new LinkedList[N];
		
		for(int i=0; i<N; i++) {
			ret[i] = new LinkedList<Integer>();
		}
		
		return ret;
	}
}
