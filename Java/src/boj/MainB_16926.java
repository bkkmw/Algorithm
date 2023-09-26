package boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MainB_16926 {
	static int[][] dir_cc = new int[][] {		// counter clockwise
		{+1,0}, {0,+1}, {-1,0}, {0,-1}
	};

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input/boj/input_16926.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		int[][] arr = new int[N][M];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		arr = rotate_T(arr, N, M, R);
		
		print(arr,N,M,sb);
		System.out.println(sb.toString());		
	}
		
	static int[][] rotate_T(int[][] arr, int N, int M, int R) {
		int[][] ret = new int[N][M];
		int[] next = new int[2];
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				int track = track_no(N,M,i,j);
				int length = 2*(M+N-4*track-2);
				next = next_pos(track, N, M, R%length, i, j);
				ret[next[0]][next[1]] = arr[i][j];
			}
		}
		return ret;
	}
	
	static int track_no(int N, int M, int i, int j) {
		int y = (N-1-i <= i) ? N-1-i : i;
		int x = (M-1-j <= j) ? M-1-j : j;
		return Math.min(y, x);
	}
	
	static int[] next_pos(int track, int N, int M, int r, int i, int j) {
		int[] ret = new int[2];
		int width = M - 2*track;
		int height = N - 2*track;
		int y = i - track;
		int x = j - track;
		int cnt = 0, dir = 0;
		if(y==0 && x != 0) dir = 3;
		else if(y!=height-1 && x==0) dir = 0;
		else if(y==height-1 && x!=width-1) dir = 1;
		else if(y!=0 && x==width-1) dir = 2;
		
		while(cnt < r) {
			int ny = y + dir_cc[dir%4][0];
			int nx = x + dir_cc[dir%4][1];
			if(ny <0 || ny >= height || nx<0 || nx>=width) dir = (dir+1)%4;
			else if(ny==0 || ny==height-1 || nx==0 || nx==width-1){
				y = ny;		x = nx;		cnt++;
			}
			else dir = (dir+3)%4;
		}
		ret[0] = y+track;		ret[1] = x+track;
		return ret;
	}
	
	static void print(int[][]arr, int N, int M, StringBuilder sb) {
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				sb.append(arr[i][j]).append(" ");
			}
			sb.append("\n");
		}
	}
}
