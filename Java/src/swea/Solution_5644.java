package swea;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_5644 {
	static int[][] dir_yx = new int[][] {
			{0,0}, {-1,0}, {0,+1}, {+1,0}, {0,-1}
	};
	
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input/swea/input_5644.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int TC = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=TC; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int M = Integer.parseInt(st.nextToken());
			int A = Integer.parseInt(st.nextToken());
			
			int[][] cmd = new int[2][M];
			
			for(int i=0; i<2; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int j=0; j<M; j++) {
					cmd[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			int[][] bc = new int[A][4];
			
			for(int i=0; i<A; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int j=0; j<4; j++) {
					bc[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			int ans = solve(cmd, M, bc, A);
			sb.append("#").append(tc).append(" ");
			sb.append(ans).append("\n");
		}
		System.out.println(sb.toString());
	}
		
	static int solve(int[][] cmd, int M, int[][] bc, int A) {
		int ret = 0;
		int[] apos = new int[] {1,1};
		int[] bpos = new int[] {10,10};
		int[][] check = new int[2][A];
		
		check = update(apos, bpos, bc, A);
		ret += calc(check, bc, A);

		for(int i=0; i<M; i++) {
			apos[0] += dir_yx[cmd[0][i]][0];
			apos[1] += dir_yx[cmd[0][i]][1];
			bpos[0] += dir_yx[cmd[1][i]][0];
			bpos[1] += dir_yx[cmd[1][i]][1];
			check = update(apos, bpos, bc, A);
			ret += calc(check, bc, A);
		}
		
		return ret;
	}
	
	static int[][] update(int[] a, int[] b, int[][] bc, int A){
		int[][] ret = new int[2][A];
		for(int i=0; i<A; i++) {
			int row = bc[i][1];
			int col = bc[i][0];
			if(Math.abs(row - a[0]) + Math.abs(col - a[1]) <= bc[i][2]) ret[0][i] = 1;
			if(Math.abs(row - b[0]) + Math.abs(col - b[1]) <= bc[i][2]) ret[1][i] = 1;
		}
		return ret;
	}
	
	static int calc(int[][] check, int[][] bc, int A) {
		int ret = 0;
		int temp = 0;
		for(int i=0; i<A; i++) {	
			for(int j=0; j<A; j++) {
				if(i == j && check[0][i] == 1 && check[1][i] == 1) temp = bc[i][3];
				else {
					temp = check[0][i]*bc[i][3] + check[1][j]*bc[j][3];
				}

				if(temp > ret) ret = temp;
			}
		}
		return ret;
	}
}
