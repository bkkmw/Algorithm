package boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MainB_15683_rev {
	static int[][] dir_yx = new int[][] {
		{-1,0}, {0,1}, {1,0}, {0,-1}
	};
	static int[] cc_map = new int[] {
		0, 1, 2, 2, 3, 4
	};
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input/boj/input_15683.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[N][M];
		int[][] cctv = new int[8][2];
		int cc_cnt = 0;
		for(int i=0;i <N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<M; j++) {
				int temp = Integer.parseInt(st.nextToken());
				map[i][j] = temp;
				if(temp != 0 && temp != 6) {
					cctv[cc_cnt][0] = i;
					cctv[cc_cnt++][1] = j;
				}
			}
		}
		
		int ans = solve(map, cctv, cc_cnt);
		System.out.println(ans);
				
	}
	
	static int solve(int[][] map, int[][] cctv, int cc_cnt) {
		int ret = 64;
		int c = 0;
		for(int i=0; i<cc_cnt; i++) {
			if(map[cctv[i][0]][cctv[i][1]] == 5) {
				mark(map, cctv[i][0], cctv[i][1], 5, 0);
			}
			else if(map[cctv[i][0]][cctv[i][1]] == 2) c++;
			else c+=2;
		}
		
		for(int i=0; i<(1<<c); i++) {
			int temp = calc(map, cctv, cc_cnt, i);
			if(temp < ret) ret = temp;
		}
		
		
		return ret;
	}
	
	static int calc(int[][] src, int[][] cctv, int cc_cnt, int idx) {
		int[][] map = copy(src);
		int cidx = 0;
		while(cidx < cc_cnt) {
			int y = cctv[cidx][0];
			int x = cctv[cidx][1];
			if(map[y][x] == 5) {
				cidx ++;
				continue;
			}
			else if(map[y][x] == 2) {
				mark(map,y,x,2,(idx&1));
				idx = idx>>1;
				cidx++;
			}
			else {
				mark(map,y,x,map[y][x],(idx&3));
				idx = idx>>2;
				cidx++;
			}
		}
		return count(map);
	}
	
	static void mark(int[][] map, int i, int j, int num, int dir) {
		int N = map.length, M = map[0].length;
		for(int d=0; d<cc_map[num]; d++) {
			int ndir;
			if(num == 2) ndir = (dir+2*d)%4;
			else ndir = (dir+d)%4;
			
			int ny = i + dir_yx[ndir][0];
			int nx = j + dir_yx[ndir][1];
			while(ny<N && ny >=0 && nx<M && nx>=0) {
				if(map[ny][nx] == 6) break;
				else {
					if(map[ny][nx] == 0) map[ny][nx] = '#';
					ny += dir_yx[ndir][0];
					nx += dir_yx[ndir][1];
				}
			}	
		}
	}

	static int count(int[][] map) {
		int ret = 0;
		for(int i=0; i<map.length; i++) {
			for(int j=0; j<map[0].length; j++) {
				if(map[i][j] == 0) ret++;
			}
		}
		return ret;
	}
	
	static int[][] copy(int[][] src) {
		int[][] ret = new int[src.length][src[0].length];
		for(int i=0; i<src.length; i++) {
			for(int j=0; j<src[0].length; j++) {
				ret[i][j] = src[i][j];
			}
		}
		return ret;
	}
}
