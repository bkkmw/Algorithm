package boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class MainB_01922 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input/boj/input_01922.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		
		List<int[]> edges = new ArrayList<int[]>(M);
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			if(a != b) {
				int low = a < b? a : b;
				int high = a == low ? b : a;
				edges.add(new int[] {low-1, high-1, c});
			}
		}
		
		int ans = solve(edges, N);
		System.out.println(ans);
	}
	
	public static int solve(List<int[]> edges, int N) {
		int ret = 0;
		int cnt = 0;
		int e_size = edges.size();
		
		int[] parent = init_arr(N, -1);
		edges.sort((int[] o1, int[] o2) -> o1[2] - o2[2]); 
		
		for(int i=0; i<e_size; i++) {
			int[] edge = edges.get(i);
			
			// check cycle
			if(check_cycle(edge[0], edge[1], parent))
				continue;
			
			// add new edge
			ret += edge[2];
			update_parent(edge[0], edge[1], parent);
			
			// check count
			if(++cnt == N-1)
				break;
		}
		
		return ret;
	}
	
	public static boolean check_cycle(int a, int b, int[] parent) {
		while(parent[a] != -1) 
			a = parent[a];
		
		while(parent[b] != -1) 
			b = parent[b];
		
		return a == b;
	}
	
	public static void update_parent(int a, int b, int[] parent) {
		int pa = a;
		while(parent[pa] != -1)
			pa = parent[pa];
		
		int pb = parent[b];
		parent[b] = pa;
		
		while(pb != -1) {
			int next = parent[pb];
			parent[pb] = pa;
			pb = next;
		}
	}
	
	public static int[] init_arr(int N, int val) {
		int[] ret = new int[N];
		for(int i=0; i<N; i++)
			ret[i] = val;
		
		return ret;
	}
}
