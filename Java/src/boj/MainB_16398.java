package boj;

import java.io.*;
import java.util.*;

public class MainB_16398 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input/boj/input_16398.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int[][] map = new int[N][N];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine()," ");
			for(int j=0; j<N;j ++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}
		
		long ans = solve(map);
		System.out.println(ans);
	}
	
	public static long solve(int[][] map) {
		long ret = 0;
		int N = map.length;
		// dst, cost
		PriorityQueue<int[]> pq = new PriorityQueue<int[]>((int[] o1, int[] o2)
				-> o1[1]-o2[1]);
		int cnt = 0;
		boolean[] check = new boolean[N];
		
		check[0] = true;
		cnt++;
		for(int j=1; j<N; j++)
			pq.add(new int[] {j, map[0][j]});
		
		while(cnt < N) {
			int[] edge = pq.poll();
			if(check[edge[0]])
				continue;
			
			check[edge[0]] = true;
			cnt++;
			ret += edge[1];
			
			for(int j=0; j<N; j++) {
				if(!check[j])
					pq.add(new int[] {j, map[edge[0]][j]});
			}
			
		}
		return ret;
	}
}
