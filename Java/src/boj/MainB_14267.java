package boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MainB_14267 {

	public static int N;
	public static int M;
	public static int[] boss;
	public static int[] emp;
	public static boolean[] checked;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input/boj/input_14267.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		boss = new int[N+1];
		emp = new int[N+1];
		checked = new boolean[N+1];
		
		st = new StringTokenizer(br.readLine(), " ");
		for(int i=1; i<N+1; i++) 
			boss[i] = Integer.parseInt(st.nextToken());
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			emp[Integer.parseInt(st.nextToken())] += 
					Integer.parseInt(st.nextToken());
		}
		
		solve();
		
		for(int i=1; i<N+1; i++)
			sb.append(emp[i]).append(" ");
		
		System.out.println(sb.toString());
	}
	
	public static void solve() {
		for(int i=N; i>1; i--) {
			find_score(i);
		}
	}
	
	public static int find_score(int i) {
		int ret = emp[i];
		if(boss[i] == 1) {
			checked[i] = true;
			return emp[i];
		}
		
		if(checked[i])
			return emp[i];
		
		ret += find_score(boss[i]);
		emp[i] = ret;
		checked[i] = true;
		
		return ret;
	}
}
