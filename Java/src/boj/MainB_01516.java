package boj;

import java.io.*;
import java.util.*;

public class MainB_01516 {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream(new File("input/boj/input_01516.txt")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		List<Integer>[] post = new List[N];
		for(int i=0; i<N; i++)
			post[i] = new LinkedList<Integer>();
		int[] req_cnt = new int[N];
		int[] cost = new int[N];
		
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			cost[i] = Integer.parseInt(st.nextToken());
			
			while(st.hasMoreTokens()) {
				int num = Integer.parseInt(st.nextToken());
				if(num == -1) break;
				
				post[num-1].add(i);
				req_cnt[i] ++;
			}
		}
		
		int[] res = solve(post, cost, req_cnt);
		
		print_ans(res);
		br.close();
	}
	
	public static int[] solve(List<Integer>[] post, int[] cost, int[] req_cnt) {
		int N = cost.length;
		int[] res = new int[N];
//		Queue<int[]> q = new LinkedList<int[]>();
		PriorityQueue<int[]> q = new PriorityQueue<>((o1, o2) -> o1[1]-o2[1]);
		
		for(int i=0; i<N; i++) {
			if(req_cnt[i] == 0)
				q.add(new int[] {i, 0+cost[i]});
		}
		
		while(!q.isEmpty()) {
			int[] temp = q.poll();
			res[temp[0]] = temp[1];
			
			post[temp[0]].stream().forEach((elem) -> {
				
				if(res[elem] == 0 && --req_cnt[elem] == 0) {
					q.add(new int[] {elem, res[temp[0]] + cost[elem]});
				}
			});
			
			post[temp[0]].clear();
		}
		
		return res;
	}
	
	public static void print_ans(int[] res) {
		StringBuilder sb = new StringBuilder();
		int N = res.length;
		
		for(int i=0; i<N; i++) {
			sb.append(res[i]).append("\n");
		}
		
		System.out.println(sb.toString());
	}
}
