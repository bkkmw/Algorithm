package boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class MainB_03025 {
	
	public static Stack<int[]>[] mem;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input/boj/input_03025.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		
		char[][] map = new char[R][C];
		
		for(int i=0; i<R; i++) {
			String line = br.readLine();
			for(int j=0; j<C; j++) {
				map[i][j] = line.charAt(j);
			}
		}
		
		int N = Integer.parseInt(br.readLine());
		int[] cols = new int[N];
		
		for(int i=0; i<N; i++) {
			cols[i] = Integer.parseInt(br.readLine()) - 1;
		}
		
		solve(map, cols);
		
		print_ans(sb, map, R, C);
		System.out.println(sb);
	}
	
	static void solve(char[][] map, int[] cols) {
		int R = map.length;
		int C = map[0].length;
		int N = cols.length;
		
		mem = new Stack[C];
		
		for(int i=0; i<C; i++) {
			mem[i] = new Stack<int[]>();
		}
		
		
		for(int i=0; i<N; i++) {
			int[] target = check_mem(map, cols[i]);
			
			int[] res = fall(map, target[0], target[1]);
			while(res[0] > -1) {
				mem[cols[i]].add(res);
				res = fall(map, res[0], res[1]);
			}
		}
		
	}
	
	public static int[] fall(char[][] map, int col, int i) {
		
		int[] ret = new int[2];
		
		while(i < map.length-1 && map[i+1][col] == '.') i++;
		
		if(i == map.length - 1 || map[i+1][col] == 'X') {
			map[i][col] = 'O';
			i--;
			ret[0] = -1;
		}

		else if(map[i+1][col] == 'O') {
			if(col-1>=0 && map[i][col-1] == '.'			
					&& i+1<map.length && map[i+1][col-1] == '.') {
				ret[0] = col-1;
				ret[1] = i+1;
				
			}
			else if(col+1<map[0].length && map[i][col+1] == '.'	
					&& i+1<map.length && map[i+1][col+1] == '.') {
				ret[0] = col+1;
				ret[1] = i+1;
			}
			else {
				map[i][col] = 'O';
				i--;
				ret[0] = -1;
			}
		}
		
		return ret;
	}
	
	public static int[] check_mem(char[][] map, int col) {

		while((!mem[col].isEmpty())) {
			int[] c_i = mem[col].peek();
			if(map[c_i[1]][c_i[0]] == '.')
				return c_i;
			mem[col].pop();
		}
		return new int[] {col, 0};
	}
	
	public static void print_ans(StringBuilder sb, char[][] map, int R, int C) {
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				sb.append(map[i][j]);
			}
			sb.append("\n");
		}
	}
}
