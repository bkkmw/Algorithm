package boj;

import java.io.*;
import java.util.*;

public class MainB_02606 {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input/boj/input_02606.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		List<Integer>[] edges = new List[N];
		
		for(int i=0; i<N; i++) {
			edges[i] = new ArrayList<Integer>();
		}
		StringTokenizer st;
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int src = Integer.parseInt(st.nextToken())-1;
			int dst = Integer.parseInt(st.nextToken())-1;
			
			edges[src].add(dst);
			edges[dst].add(src);
		}
		
		int ans = solve(edges);
		
		System.out.println(ans);
		br.close();
	}
	
	public static int solve(List<Integer>[] edges) {
		int ret = 0, N = edges.length;
		boolean[] infected = new boolean[N];
		
		Queue<Integer> q = new LinkedList<Integer>();
		infected[0] = true;
		q.add(0);
		
		while(!q.isEmpty()) {
			int cur = q.poll();
			Iterator<Integer> it = edges[cur].iterator();
			
			while(it.hasNext()) {
				int next = it.next();
				if(!infected[next]) {
					infected[next] = true;
					ret++;
					q.add(next);
				}
			}
		}
		
		
		return ret;
	}
}
