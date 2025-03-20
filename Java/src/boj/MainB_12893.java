package boj;

import java.io.*;
import java.util.*;

public class MainB_12893 {
	
	public static int UNCHECKED = 0;
	public static int ALLY = 1;
	public static int ENEMY = 2;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input/boj/input_12893.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][] enemies = new int[M][2];
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			enemies[i][0] = Integer.parseInt(st.nextToken())-1;
			enemies[i][1] = Integer.parseInt(st.nextToken())-1;
		}
		
		int ans = solve(N, enemies);
		System.out.println(ans);
		br.close();
	}
	
	public static int solve(int N, int[][] enemy_list) {
		List<Integer>[] enemies = init_list_arr(N);
		int[] status = new int[N];
		int M = enemy_list.length;
		
		for(int i=0; i<M; i++) {
			int a = enemy_list[i][0];
			int b = enemy_list[i][1];
			
			enemies[a].add(b);
			enemies[b].add(a);
		}
		
		for(int i=0; i<N; i++) {
			if(status[i] != UNCHECKED) continue;
			
			status[i] = ALLY;
			boolean res = update_status(status, enemies, i, ALLY);
			
			if(!res) {
				return 0;
			}
		}
		
		return 1;
	}
	
	public static boolean update_status(int[] status, List<Integer>[] enemies, int idx, int prev) {
		boolean res = true;
		int next_status = prev == ALLY ? ENEMY : ALLY;
				
		Iterator<Integer> it = enemies[idx].iterator();
		
		while(it.hasNext()) {
			int next = it.next();
			if(status[next] == UNCHECKED) {
				status[next] = next_status;
				res = update_status(status, enemies, next, next_status);
				if(!res) return false;
			} else if(status[next] != next_status) {
				return false;
			}
		}
		
		return res;
	}
	
	public static List<Integer>[] init_list_arr(int N) {
		List<Integer>[] ret = new LinkedList[N];
		
		for(int i=0; i<N; i++) {
			ret[i] = new LinkedList<Integer>();
		}
		
		return ret;
	}
}
