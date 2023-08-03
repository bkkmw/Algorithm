package swea;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_7465 {
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input/swea/input_7465.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int TC = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=TC; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());

			int[][] list = new int[M][2];
			
			for(int i=0; i<M; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				list[i][0] = Integer.parseInt(st.nextToken())-1;
				list[i][1] = Integer.parseInt(st.nextToken())-1;
				
			}
			
			sb.append("#").append(tc).append(" ");
			int ans = solve(N, list);
			sb.append(ans).append("\n");
		}
		System.out.println(sb);
		br.close();
	}
	
	static int solve(int N, int[][] list) {
		int[] par = new int[N];
		for(int i=0; i<N; i++) par[i] = i;
		
		for(int i=0; i<list.length; i++) {
			union(par, list[i][0], list[i][1]);
		}
		
		return count(par);
	}
	
	static int count(int[] par) {
		int ret = 0;
		int[] check = new int[par.length];
		for(int i=0; i<par.length; i++) {
			if(check[get_par(par,par[i])] == 0) {
				check[get_par(par,par[i])] = 1;
				ret++;
			}
		}
		return ret;
	}
	
	static int get_par(int[] par, int a) {
		if(par[a] == a) return a;
		return get_par(par, par[a]);
	}
	
	static boolean union(int[] par, int a, int b) {
		int rootA = get_par(par, a);
		int rootB = get_par(par, b);
		
		if(rootA == rootB) return true;
		
		par[rootB] = rootA; 
		return false;
	}
}
