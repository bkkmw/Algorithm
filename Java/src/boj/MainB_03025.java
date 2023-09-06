package boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MainB_03025 {
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
		
		solve(map, cols, sb);
		System.out.println(sb);
	}
	
	static void solve(char[][] map, int[] cols, StringBuilder sb) {
		int R = map.length;
		int C = map[0].length;
		int N = cols.length;
		
		for(int i=0; i<N; i++) {
			fall(map, cols[i], 0);
		}

		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				sb.append(map[i][j]);
			}
			sb.append("\n");
		}
	}
	
	static void fall(char[][] map, int col, int i) {
		
		while(i < map.length-1 && map[i+1][col] == '.') i++;


		if(i == map.length - 1 || map[i+1][col] == 'X') {

			map[i][col] = 'O';
		}

		else if(map[i+1][col] == 'O') {
			if(col-1>=0 && map[i][col-1] == '.'			
					&& i+1<map.length && map[i+1][col-1] == '.') 
				fall(map, col-1, i);	
			
			else if(col+1<map[0].length && map[i][col+1] == '.'	
					&& i+1<map.length && map[i+1][col+1] == '.')
				fall(map, col+1, i);	
			else map[i][col] = 'O';		
		}
	}
	
}
