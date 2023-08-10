package boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MainB_01987 {
	static int[][] dir_yx = new int[][] {
		{-1, 0}, {0, 1}, {1, 0}, {0, -1}
	};
	static int R;
	static int C;
	static char[][] map;
	static boolean[] check;
	static int ans;
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input/boj/input_01987.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		check = new boolean[26];
		for(int i=0; i<R; i++) {
			String line = br.readLine();
			for(int j=0; j<C; j++) {
				map[i][j] = line.charAt(j);
			}
		}
		
		ans = solve(0, 0, 0);
		System.out.println(ans);
	}
	
	static int solve(int i, int j, int score) {
		if(score == 26) return score;
		if(i<0 || i>R-1 || j<0 || j>C-1) return score;
		if(check[map[i][j]-'A'] == true) return score;
		
		
		check[map[i][j]-'A'] = true;
		score += 1;
		int max = score;
		for(int d=0; d<4; d++) {
			int temp = solve(i+dir_yx[d][0], j+dir_yx[d][1], score);
			if(temp > max) max = temp;
		}
		check[map[i][j]-'A'] = false;
		return max;
	}
}
