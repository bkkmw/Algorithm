package boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class MainB_02563 {

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input/boj/input_02563.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		int[][] coor = new int[N][2];
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			coor[i][0] = Integer.parseInt(st.nextToken());
			coor[i][1] = Integer.parseInt(st.nextToken());
		}
		
		int ans = solve(coor, N);
		System.out.println(ans);
		
	}
	static int solve(int[][] coor, int N) {
		int ret = 0;
		for(int i=0; i<N; i++) {
			ret += attach(coor, i);
		}
		return ret;
	}
	static int attach(int[][] coor, int idx) {
		int ret = 0;
		int y = coor[idx][0];
		int x = coor[idx][1];
		int[][] paper = new int[10][10];
		for(int i=0; i<10; i++) {
			for(int j=0; j<10; j++)
				paper[i][j] = 1;
		}
		
		for(int n=idx-1; n>=0; n--) {
			int yy = coor[n][0] - y;
			int xx = coor[n][1] - x;
			for(int i=0; i<10; i++) {
				for(int j=0; j<10; j++) {
					int ny = yy + i;	int nx = xx +j;
					if(ny<0 || ny>9 || nx<0 || nx>9) continue;
					paper[ny][nx] = 0;
				}
			}	
		}
		
		for(int i=0; i<10; i++) {
			for(int j=0; j<10; j++)
				ret += paper[i][j];
		}
		return ret;
	}
}
