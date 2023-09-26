package boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MainB_03109 {
	static int[] dy = new int[] {-1, 0, +1};
	static char[][] map;
	static int ans;
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input/boj/input_03109.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		
		map = new char[R][C];
		
		for(int i=0; i<R; i++) {
			String line = br.readLine();
			for(int j=0; j<C; j++) {
				map[i][j] = line.charAt(j);
			}
		}
		ans = 0;
		solve(R,C);
		System.out.println(ans);
	}
	
	static void solve(int R, int C) {
		for(int i=0; i<R; i++) {
			connect(i, 0, R, C, 0);
		}
	}
	
	static int connect(int i, int j, int R, int C, int done) {
		if(done == 1) return 1;
		if(j == C-1) {
			ans++;
			return 1;
		}
		
		if(i<0 || i>=R || j<0 || j>=C || map[i][j] != '.') return 0;
		map[i][j] = 'P';
		done = connect(i-1, j+1, R, C, done);
		done = connect(i, j+1, R, C, done);
		done = connect(i+1, j+1, R, C, done);
		
//		if(done != 1) map[i][j] = '.';
		
		return done;
	}

}
