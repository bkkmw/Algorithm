package boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MainB_16935 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input/boj/input_16935.txt"));
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
		
		int[] cmds = new int[R];
		st = new StringTokenizer(br.readLine(), " ");
		for(int i=0; i<R; i++) {
			cmds[i] = Integer.parseInt(st.nextToken());
		}
		
		int[][] ans = solve(arr, cmds);
		print(arr, ans, sb);
		System.out.println(sb.toString());
		
	}
	
	static int[][] solve(int[][] arr, int[] cmds){
		int N = arr.length;
		int M = arr[0].length;
		int[][] ret = new int[N*M+1][2];
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				ret[i*M+j][0] = i;		ret[i*M+j][1] = j;
			}
		}
		ret[N*M][0] = N;
		ret[N*M][1] = M;
		
		for(int r=0; r<cmds.length; r++) {
			if(cmds[r] == 1 || cmds[r] == 2) ret = reverse(ret, cmds[r]);
			else if(cmds[r] == 3 || cmds[r] == 4) ret = rotate(ret, cmds[r]);
			else ret = divide(ret, cmds[r]);
		}
		return ret;
	}
	
	static int[][] reverse(int[][] ret, int cmd){
		int N = ret[ret.length-1][0];
		int M = ret[ret.length-1][1];
		double center;
		if(cmd == 1) center = (N-1)/2.;
		else center = (M-1)/2.;

		for(int idx=0; idx<ret.length-1; idx++) {
			double diff = center - ret[idx][cmd-1];
			ret[idx][cmd-1] = (int)(center + diff);
		}
		return ret;
	}
	
	static int[][] rotate(int[][] ret, int cmd){
		int N = ret[ret.length-1][0];
		int M = ret[ret.length-1][1];
		
		if(cmd == 3) {
			for(int idx=0; idx<ret.length-1; idx++) {
				int i = ret[idx][0], j = ret[idx][1];
				ret[idx][0] = j;
				ret[idx][1] = N-1-i;
			}			
		}
		if(cmd == 4) {
			for(int idx=0; idx<ret.length-1; idx++) {
				int i = ret[idx][0], j = ret[idx][1];
				ret[idx][0] = M-1-j;
				ret[idx][1] = i;
			}			
		}
		
		ret[N*M][0] = M;
		ret[N*M][1] = N;
		return ret;
	}
	
	static int[][] divide(int[][] ret, int cmd){
		int N = ret[ret.length-1][0];
		int M = ret[ret.length-1][1];
		
		if(cmd == 5) {
			for(int idx=0; idx<ret.length-1; idx++) {
				int i = ret[idx][0], j = ret[idx][1];
				if(i < N/2 && j < M/2) {
					ret[idx][1] = j + M/2; 
				}
				else if(i < N/2 && M/2 <= j) {
					ret[idx][0] = i + N/2;
				}
				else if(N/2 <= i && M/2 <= j) {
					ret[idx][1] = j - M/2;
				}
				else {
					ret[idx][0] = i - N/2;					
				}
			}
		}
		else {
			for(int idx=0; idx<ret.length-1; idx++) {
				int i = ret[idx][0], j = ret[idx][1];
				if(i < N/2 && j < M/2) {
					ret[idx][0] = i + N/2; 
				}
				else if(i < N/2 && M/2 <= j) {
					ret[idx][1] = j - M/2;
				}
				else if(N/2 <= i && M/2 <= j) {
					ret[idx][0] = i - N/2;
				}
				else {
					ret[idx][1] = j + M/2;					
				}
			}
			
		}
		return ret;
	}
	
	static void print(int[][] src, int[][] ans, StringBuilder sb) {
		int N = ans[ans.length-1][0];
		int M = ans[ans.length-1][1];
		int[][] prt = new int[N][M];
		
		for(int i=0; i<src.length; i++) {
			for(int j=0; j<src[0].length; j++) {
				prt[ans[i*src[0].length+j][0]][ans[i*src[0].length+j][1]] = src[i][j];
			}
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				sb.append(prt[i][j]).append(" ");
			}
			sb.append("\n");
		}
		
	}
}
