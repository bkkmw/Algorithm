package boj;

import java.io.*;
import java.util.*;

public class MainB_12100 {
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream(new File("input/boj/input_12100.txt")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[][] map = new int[N][N];
		
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int ans = solve(map);
		System.out.println(ans);
		
		br.close();
	}
	
	public static int solve(int[][] map) {
		int ret = 0;
		
		ret = recur(map, 0, ret);
		return ret;
	}
	
	public static int recur(int[][] map, int cnt, int max) {
		if(cnt == 5) return Math.max(find_max(map), max);
		
		for(int d=0; d<4; d++) {
			int[][] src = copy_map(map);
			update_map(map, d);
			max = recur(map, cnt+1, max);
			
			map = src;
		}
		
		return max;
	}
	
	public static int find_max(int[][] map) {
		int N = map.length, ret = 0;
		
		for(int i=0; i<N; i++)
			for(int j=0; j<N; j++)
				ret = Math.max(ret, map[i][j]);
		
		return ret;
	}
	
	public static void update_map(int[][] map, int dir) {
		int N = map.length;
		
		if(dir == 0) {
			for(int j=0; j<N; j++) {
				List<Integer> col = new ArrayList<Integer>(N);
				
				for(int i=0; i<N; i++) {
					if(map[i][j] != 0) col.add(map[i][j]);
					
				}
				
				merge(col);
				
				for(int i=0; i<N; i++) {
					if(!col.isEmpty()) {
						map[i][j] = col.remove(0);
					} else {
						map[i][j] = 0;
					}
				}
				
			}
		} else if(dir == 1) {
			for(int i=0; i<N; i++) {
				List<Integer> row = new ArrayList<Integer>(N);
				
				for(int j=0; j<N; j++) {
					if(map[i][j] != 0) row.add(map[i][j]);
				}
				
				merge(row);
				
				for(int j=0; j<N; j++) {
					if(!row.isEmpty()) {
						map[i][j] = row.remove(0);
					} else {
						map[i][j] = 0;
					}
				}
				
			}
		} else if(dir == 2) {
			for(int j=0; j<N; j++) {
				List<Integer> col = new ArrayList<Integer>(N);
				
				for(int i=N-1; i>-1; i--) {
					if(map[i][j] != 0) col.add(map[i][j]);
				}
				
				merge(col);
				
				for(int i=N-1; i>-1; i--) {
					if(!col.isEmpty()) {
						map[i][j] = col.remove(0);
					} else {
						map[i][j] = 0;
					}
				}
				
			}
			
		} else {
			for(int i=0; i<N; i++) {
				List<Integer> row = new ArrayList<Integer>(N);
				
				for(int j=N-1; j>-1; j--) {
					if(map[i][j] != 0) row.add(map[i][j]);
				}
				
				merge(row);
				
				for(int j=N-1; j>-1; j--) {
					if(!row.isEmpty()) {
						map[i][j] = row.remove(0);
					} else {
						map[i][j] = 0;
					}
				}
				
			}
		}
		
	}
	
	public static void merge(List<Integer> list) {
		
		for(int i=0; i<list.size()-1; i++) {
//			if(list.get(i) == 128) {
//				System.out.println(list.get(i) + list.get(i+1));
//				System.out.println(list.get(i) == list.get(i+1));
//			}
			if(list.get(i+1).equals(list.get(i))) {
				list.set(i, 2*list.get(i));
				list.remove(i+1);
			}
		}
	}
	
	public static int[][] copy_map(int[][] src) {
		int N = src.length;
		int[][] ret = new int[N][N];
		
		for(int i=0; i<N; i++)
			for(int j=0; j<N; j++)
				ret[i][j] = src[i][j];
		
		return ret;
	}
}
