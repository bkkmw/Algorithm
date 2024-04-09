package boj;

import java.io.*;
import java.util.*;

public class MainB_07569 {
	
	static int[][] dir_xyz = new int[][] {
		{0,0,+1}, {0,-1,0}, {+1,0,0}, {0,+1,0}, {-1,0,0}, {0,0,-1} 
	};

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input/boj/input_07569.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		int H = Integer.parseInt(st.nextToken());
		
		int[][][] vol = new int[M][N][H];
		
		for(int k=0; k<H; k++) {
			for(int j=0; j<N; j++) {
				st = new StringTokenizer(br.readLine());
				for(int i=0; i<M; i++) {
					vol[i][j][k] = Integer.parseInt(st.nextToken());
				}
			}
		}
		
		int ans = solve(vol);
		
		System.out.println(ans);
		
		br.close();
	}
	
	public static int solve(int[][][] vol) {
		int M = vol.length;
		int N = vol[0].length;
		int H = vol[0][0].length;
		int ret = 0;
		int cnt = 0;
		int empty_cnt = 0;
		
		Queue<int[]> q = new LinkedList<int[]>();
		boolean[][][] checked = new boolean[M][N][H];
		
		for(int i=0; i<M; i++) {
			for(int j=0; j<N; j++) {
				for(int k=0; k<H; k++) {
					if(vol[i][j][k] == 1) {
						q.add(new int[] {i, j, k, 0});
						checked[i][j][k] = true;
						cnt++;
					} else if(vol[i][j][k] == -1) {
						empty_cnt ++;
					}
				}
			}
		}
		
		if(cnt+empty_cnt == M*N*H) return 0;
		
		while(!q.isEmpty()) {
			int[] poll = q.poll();
			
			for(int d=0; d<6; d++) {
				int ni = poll[0] + dir_xyz[d][0];
				int nj = poll[1] + dir_xyz[d][1];
				int nk = poll[2] + dir_xyz[d][2];
				
				if(!check_boundary(ni, nj, nk, M, N, H)) continue;
				
				if(vol[ni][nj][nk] == -1 || checked[ni][nj][nk]) continue;
				
				q.add(new int[] {ni, nj, nk, poll[3]+1});
				checked[ni][nj][nk] = true;
				cnt++;
				ret = ret > poll[3]+1 ? ret : poll[3]+1;
			}
		}
		
		
		return (cnt+empty_cnt==M*N*H) ? ret : -1;
	}
	
	public static boolean check_boundary(int i, int j, int k, int M, int N, int H) {
		boolean ret = true;
		
		if(i<0 || i>=M) return false;
		if(j<0 || j>=N) return false;
		if(k<0 || k>=H) return false;
		
		return ret;
	}
}
