package boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class MainB_17471 {
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input/boj/input_17471.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int[] pop = new int[N];
		int[][] conn = new int[N][N];
		
		st = new StringTokenizer(br.readLine(), " ");
		for(int i=0; i<N; i++) pop[i] = Integer.parseInt(st.nextToken());
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int K = Integer.parseInt(st.nextToken());
			for(int j=0; j<K; j++) {
				conn[i][Integer.parseInt(st.nextToken())-1] = 1;
			}
		}
		
		int ans = solve(pop, conn);
		System.out.println(ans);
	}
	
	static int solve(int[] pop, int[][] conn) {
		int ret = 1001;
		int N = pop.length;
		boolean[] selection = new boolean[N];
		ret = recur(pop, conn, selection, -1, N, ret, 0, 0);
		return (ret == 1001)? -1 : ret;	
	}
	
	static int recur(int[] pop, int[][] conn, boolean[] sel, int idx, int N, int min, int popA, int popB) {
		if(idx == N-1) {
			if(popA == 0 || popB == 0) return min;
			int diff = Math.abs(popA - popB);
			if(diff >= min) return min;
			if(check_adj_region(conn, sel))
				return diff;
			else return min;
		}
		sel[++idx] = true;
		min = recur(pop, conn, sel, idx, N, min, popA + pop[idx], popB);
		sel[idx] = false;
		min = recur(pop, conn, sel, idx, N, min, popA, popB + pop[idx]);
		
		return min;
	}
	
	static boolean check_adj_region(int[][] conn, boolean[] sel) {
		boolean ret = true;
		int N = conn.length;
		int[] reg = new int[N];
		int regA = -1; int regB = -1;
		Queue<int[]> q = new LinkedList<int[]>();
		
		for(int i=0; i<N; i++) {
			if(regA > -1 && regB > -1) break;
			if(sel[i]) regA = i;
			else regB = i;
		}		
		reg[regA] = 1; reg[regB] = 2;
		
		q.add(new int[] {regA, 1});
		q.add(new int[] {regB, 2});
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			for(int i=0; i<N; i++) {
				if(i == cur[0]) continue;
				if(reg[i] != 0) continue;
				if(conn[cur[0]][i] == 0) continue;
				int division = (sel[i]) ? 1 : 2;
				if(division != cur[1]) continue;
				q.add(new int[] {i, division});
				reg[i] = division;
			}
		}

		for(int i=0; i<N; i++) {
			if(sel[i] && reg[i] != 1) return false;
			else if(sel[i] == false && reg[i] != 2) return false;
		}
		return ret;
	}
}
