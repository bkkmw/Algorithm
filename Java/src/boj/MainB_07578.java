package boj;

import java.util.*;
import java.io.*;

public class MainB_07578 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input/boj/input_07578.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			int id = Integer.parseInt(st.nextToken());
			map.put(id, i);
		}
		
		int[] index = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			int idx = map.get(Integer.parseInt(st.nextToken()));
			index[idx] = i;
		}
		
		long ans = solve(index);
		System.out.println(ans);
		br.close();
	}
	
	public static long solve(int[] index) {
		long ret = 0;
		int N = index.length;
		
		SegmentTree counts = new SegmentTree(N);
		for(int i=0; i<N; i++) {
			ret += counts.getCount(index[i]);
			counts.update(index[i], 1);
		}
		
		return ret;
	}
	
	public static class SegmentTree {
		int size;
		int leaf_size;
		long[] nodes;
		
		SegmentTree(int N) {
			// mistake was : typo : log base 10 / log base 2
			this.leaf_size = (int)Math.pow(2, Math.ceil(Math.log10(N)/Math.log10(2)) + 1);
			this.size = 2*leaf_size - 1;
			this.nodes = new long[size+1]; 
		}
		
		public void update(int node, int add) {
			node += leaf_size;
			while(node > 0) {
				nodes[node] += add;
				node /= 2;
			}
		}
		
		public long getCount(int src) {
			src += leaf_size;
			return sum(leaf_size, size, 1, src+1, size);
		}
		
		
		private long sum(int st, int end, int cur, int l, int r) {
			if(end < l || r < st) return 0;
			if(l <= st && end <= r) return nodes[cur];
			int m = (st+end)/2;
			return sum(st, m, cur*2, l, r) + sum(m+1, end, cur*2+1, l, r);
		}
	}
}
