package boj;

import java.util.*;
import java.io.*;

public class MainB_02637 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input/boj/input_02637.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		
		List<int[]>[] materials = init_lists(N);
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken())-1;
			int y = Integer.parseInt(st.nextToken())-1;
			int k = Integer.parseInt(st.nextToken());
			
			materials[x].add(new int[] {y, k});
		}
		
		int[] ans = solve(N, materials);
		print_ans(ans, sb);
		System.out.println(sb.toString());
	}
	
	public static int[] solve(int N, List<int[]>[] materials) {
		List<int[]> ans = new LinkedList<int[]>();
		List<int[]>[] mem = init_lists(N);
		int[] ret = new int[N];
		
		ans = find_req(N, materials, mem, N-1, 1);
		
		Iterator<int[]> it = ans.iterator();
		while(it.hasNext()) {
			int[] material = it.next();
			ret[material[0]] += material[1];
		}
		return ret;
	}

	public static List<int[]> find_req(int N, List<int[]>[] materials, List<int[]>[] mem, int idx, int cnt) {
		List<int[]> ret = new LinkedList<int[]>();
		int size = materials[idx].size();
		if(size < 1) {
			ret.add(new int[] {idx, cnt});
			return ret;
		}
		if(mem[idx].size() > 0) {
			for(int i=0; i<mem[idx].size(); i++) {
				int[] material = mem[idx].get(i);
				ret.add(new int[] {material[0], material[1]*cnt});
			}
			return ret;
		}
		List<int[]> temp;
		for(int i=0; i<size; i++) {
			int[] material = materials[idx].get(i);
			temp = find_req(N, materials, mem, material[0], material[1]);
			
			for(int j=0; j<temp.size(); j++) {
				int[] mat = temp.get(j);
				mem[idx].add(mat);
				ret.add(new int[] {mat[0], mat[1]*cnt});
			}
			
		}
		return ret;
	}
	
	public static void print_ans(int[] ans, StringBuilder sb) {
		int N = ans.length;
		for(int i=0; i<N; i++) {
			if(ans[i] > 0) {
				sb.append(String.format("%d %d\n", i+1, ans[i]));
			}
		}
	}
	
	public static List<int[]>[] init_lists(int N) {
		List<int[]>[] ret = new LinkedList[N];
		for(int i=0; i<N; i++) {
			ret[i] = new LinkedList<int[]>();
		}
		return ret;
	}
}
